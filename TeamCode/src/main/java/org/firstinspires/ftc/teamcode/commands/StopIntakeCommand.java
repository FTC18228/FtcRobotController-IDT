package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class StopIntakeCommand extends CommandBase {
    IntakeSubsystem subsystem;

    /**
     * Stop the intake
     *
     * @param subsystem The subsystem to execute the command on
     */
    public StopIntakeCommand(IntakeSubsystem subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public void execute() {
        subsystem.stop();
    }
}
