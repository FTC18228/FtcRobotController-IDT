package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    IntakeSubsystem subsystem;

    /**
     * Start the intake
     *
     * @param subsystem The subsystem to execute the command on
     */
    public IntakeCommand(IntakeSubsystem subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public void execute() {
        subsystem.start();
    }
}
