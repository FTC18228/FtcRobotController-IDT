package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

public class VerticalSlideHomeCommand extends CommandBase {
    VerticalSlideSubsystem slideSubsystem;

    /**
     * The subsystem to execute the command on
     *
     * @param subsystem The subsystem to execute the command on

     */
    public VerticalSlideHomeCommand(VerticalSlideSubsystem subsystem) {
        slideSubsystem = subsystem;

    }

    @Override
    public void initialize() {

        slideSubsystem.extend(0);
    }

    @Override
    public boolean isFinished(){
        return slideSubsystem.getPosition() < 0;
    }
}
