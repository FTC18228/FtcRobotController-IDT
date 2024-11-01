package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;

public class ClawCloseCommand extends CommandBase {
    private ClawSubsystem clawSubsystem;

    /**
     * Move the claw
     *
     * @param subsystem The subsystem to execute the command on
     */
    public ClawCloseCommand(ClawSubsystem subsystem) {
        clawSubsystem = subsystem;
    }

    @Override
    public void initialize() {
        clawSubsystem.extend(1);
    }


    @Override
    public boolean isFinished(){
        return true;
    }


}