package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class OuttakeCommand extends CommandBase {
    IntakeSubsystem subsystem;

    /**
     * Reverse the intake
     *
     * @param subsystem The subsystem to execute the command on
     */
    public OuttakeCommand(IntakeSubsystem subsystem) {
        this.subsystem = subsystem;
        this.addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.reverse();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
