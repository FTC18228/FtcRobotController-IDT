package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

public class VerticalSlideHangCommand extends CommandBase {
    VerticalSlideSubsystem slideSubsystem;


    /**
     * The subsystem to execute the command on
     *
     * @param subsystem The subsystem to execute the command on

     */
    public VerticalSlideHangCommand(VerticalSlideSubsystem subsystem) {
        slideSubsystem = subsystem;

        addRequirements(subsystem);

    }

    @Override
    public void initialize() {

        slideSubsystem.AscentLevel();
    }



    @Override
    public boolean isFinished(){

        return slideSubsystem.isAtHalf();
    }
}
