package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

import java.util.function.IntSupplier;

public class VerticalSlideCommand extends CommandBase {
    VerticalSlideSubsystem slideSubsystem;
    int target;
    boolean setPosition;

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
    public void execute() {
        slideSubsystem.extend(target);
    }
}
