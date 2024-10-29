package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;

public class ClawOpenCommand extends CommandBase {
    private ClawSubsystem clawSubsystem;

    /**
     * Move the claw
     *
     * @param subsystem The subsystem to execute the command on
     */
    public ClawOpenCommand(ClawSubsystem subsystem) {
        clawSubsystem = subsystem;
    }

    @Override
    public void initialize() {
        clawSubsystem.extend(0.6);
    }


    @Override
    public boolean isFinished(){
        return true;
    }
}