package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DropSubsystem;

public class ModifiedDropCommand extends CommandBase {
   DropSubsystem sDrop;

   public ModifiedDropCommand(DropSubsystem dropSubsystem) {
      sDrop = dropSubsystem;
      addRequirements(dropSubsystem);
   }

   @Override
   public void execute() {
      sDrop.drop();
   }

   @Override
   public void end(boolean interrupted) {
      sDrop.resetDrop();
   }
}
