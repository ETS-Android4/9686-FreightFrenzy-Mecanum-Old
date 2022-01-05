package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;
import org.firstinspires.ftc.teamcode.subsystems.LiftSubsystemNoPID;

public class LiftCommandNoPID extends CommandBase {

    private LiftSubsystemNoPID lift;
    private SimpleServo sLeftDrop, sRightDrop;
    private ElapsedTime time;
    private int level = 0;
    private double timeToLift = 0.4;

    public LiftCommandNoPID(LiftSubsystemNoPID liftSubsystemNoPID, ElapsedTime timer, SimpleServo leftDrop, SimpleServo rightDrop) {
        lift = liftSubsystemNoPID;
        time = timer;

        sLeftDrop = leftDrop;
        sRightDrop = rightDrop;

        // TODO: This might be an issue
        addRequirements(liftSubsystemNoPID);
    }

    @Override
    public void initialize() {
        time.reset();
        lift.resetLevel();
        // if (level == 0) {
        //     // sLeftDrop.setPosition(0.1);
        //     // sRightDrop.setPosition(0.1);
        //     lift.motorUp();
        //     timeToLift = 0.25;
        //     level++;
        // } else if (level == 1) {
        //     lift.motorUp();
        //     timeToLift = 0.3;
        //     level++;
        // } else if (level == 2) {
        //     lift.motorUp();
        //     timeToLift = 0.1;
        //     level++;
        // }
        // else {
        //     // sLeftDrop.setPosition(0);
        //     // sRightDrop.setPosition(0);
        //     lift.motorDown();
        //     timeToLift = 0.65;
        //     level = 0;
        // }
        if (level == 0) {
            // sLeftDrop.setPosition(0.1);
            // sRightDrop.setPosition(0.1);
            lift.motorUp();
            timeToLift = 0.25;
        } else {
            // sLeftDrop.setPosition(0);
            // sRightDrop.setPosition(0);
            lift.motorDown();
            timeToLift = 0.25;
        }
    }

    @Override
    public boolean isFinished() {
        return time.seconds() >= timeToLift;
    }

    @Override
    public void end(boolean interrupted) {
        // if(level != 3)
        //     lift.motorStop();
        if (level == 0) {
            level++;
            lift.motorStop();
        } else {
            level = 0;
            lift.motorStop();
        }
    }
}
