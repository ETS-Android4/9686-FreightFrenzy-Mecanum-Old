package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.commands.DropCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommandNoPID;
import org.firstinspires.ftc.teamcode.subsystems.DropSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystemNoPID;

@TeleOp(name = "SubsystemsOnly")
public class SubsystemsOnly extends CommandOpMode {

    private Motor mLeftLift, mRightLift, mIntake;
    private SimpleServo sLeftDrop, sRightDrop;
    // private SimpleServoEmpty sLeftDrop, sRightDrop;

    private LiftSubsystemNoPID s_LiftNoPID;
    private DropSubsystem s_Drop;

    private LiftCommandNoPID c_LiftNoPID;
    private DropCommand c_Drop;
    // private ModifiedDropCommand c_Drop;

    private GamepadEx gPad;
    private ElapsedTime time;

    private final double INTAKE_MULT = 0.5;
    private final double OUTTAKE_MULT = -0.5;

    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        mIntake = new Motor(hardwareMap, "intake");
        mLeftLift = new Motor(hardwareMap, "leftLift");
        mRightLift = new Motor(hardwareMap, "rightLift");

        mLeftLift.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mRightLift.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sLeftDrop = new SimpleServo(hardwareMap, "leftDrop", 0, 180);
        sRightDrop = new SimpleServo(hardwareMap, "rightDrop", 0, 180);
        // sLeftDrop = new SimpleServoEmpty(hardwareMap, "leftDrop", 0, 180);
        // sRightDrop = new SimpleServoEmpty(hardwareMap, "rightDrop", 0, 180);

        gPad = new GamepadEx(gamepad1);
        time = new ElapsedTime();

        s_LiftNoPID = new LiftSubsystemNoPID(mLeftLift, mRightLift);
        s_Drop = new DropSubsystem(sLeftDrop, sRightDrop);

        c_LiftNoPID = new LiftCommandNoPID(s_LiftNoPID, time, sLeftDrop, sRightDrop);
        c_Drop = new DropCommand(s_Drop);
        // c_Drop = new ModifiedDropCommand(s_Drop);

        gPad.getGamepadButton(GamepadKeys.Button.Y).whenHeld(new StartEndCommand(() -> mIntake.set(INTAKE_MULT), () -> mIntake.stopMotor()));
        gPad.getGamepadButton(GamepadKeys.Button.A).whenHeld(new StartEndCommand(() -> mIntake.set(OUTTAKE_MULT), () -> mIntake.stopMotor()));

        gPad.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(c_LiftNoPID);

        gPad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed(c_Drop);

        schedule(new RunCommand(() -> {
            telemetry.addData("Left Lift RPM", mLeftLift.getMaxRPM());
            telemetry.addData("Right Lift RPM", mRightLift.getMaxRPM());
            telemetry.update();
        }));
    }

}
