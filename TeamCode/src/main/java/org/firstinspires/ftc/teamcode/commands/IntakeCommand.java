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
    public void execute(){
        if(subsystem.IsSampleLoaded()){
            subsystem.stop();
        }
    }

    @Override
    public void initialize() {

        subsystem.start();
    }

    @Override
    public boolean isFinished(){
        return true;//subsystem.IsSampleLoaded();
    }

}
