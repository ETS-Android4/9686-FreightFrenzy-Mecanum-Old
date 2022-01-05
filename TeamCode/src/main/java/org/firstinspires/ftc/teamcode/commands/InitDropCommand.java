package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.hardware.SimpleServo;

import org.firstinspires.ftc.teamcode.subsystems.DropSubsystem;

public class InitDropCommand extends ParallelCommandGroup {

    private DropSubsystem drop;
    private SimpleServo leftDrop, rightDrop;

    // TODO: Make legit solution
    public InitDropCommand(SimpleServo sLeftDrop, SimpleServo sRightDrop) {
        leftDrop = sLeftDrop;
        rightDrop = sRightDrop;

        addCommands(
                new InstantCommand(() -> sLeftDrop.setPosition(0.02)),
                new InstantCommand(() -> sRightDrop.setPosition(0.02))
        );
    }

}
