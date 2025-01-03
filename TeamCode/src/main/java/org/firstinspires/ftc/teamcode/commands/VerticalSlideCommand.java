package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

import java.util.function.IntSupplier;

public class VerticalSlideCommand extends CommandBase {
    VerticalSlideSubsystem slideSubsystem;
    int target;
    boolean setPosition;

    int ALLOWANCE = 50;

    int HIGH_BASKET = 2500;

    /**
     * The subsystem to execute the command on
     *
     * @param subsystem The subsystem to execute the command on
     * @param target The target position of the slides
     */
    public VerticalSlideCommand(VerticalSlideSubsystem subsystem, int target) {
        slideSubsystem = subsystem;
        this.target = target;
    }

    @Override
    public void initialize() {

        slideSubsystem.extend(target);
    }



    @Override
    public boolean isFinished(){
        return slideSubsystem.getPosition() > (this.target - ALLOWANCE);
    }
}
