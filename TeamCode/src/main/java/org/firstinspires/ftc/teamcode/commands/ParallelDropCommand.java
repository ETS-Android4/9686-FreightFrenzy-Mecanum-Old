package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.StartEndCommand;

import org.firstinspires.ftc.teamcode.subsystems.DropSubsystem;

public class ParallelDropCommand extends ParallelCommandGroup {

    private DropSubsystem drop;

    public ParallelDropCommand(DropSubsystem dropSubsystem) {
        drop = dropSubsystem;
        addCommands(
                new StartEndCommand(drop::dropLeft, drop::initDropLeft),
                new StartEndCommand(drop::dropRight, drop::initDropRight)
        );

    }

}
