package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

import java.util.function.IntSupplier;

public class VerticalSlideCommand extends CommandBase {
    VerticalSlideSubsystem slideSubsystem;
    IntSupplier target;
    boolean setPosition;

    public VerticalSlideCommand(VerticalSlideSubsystem subsystem, IntSupplier target, boolean setPosition) {
        slideSubsystem = subsystem;
        this.target = target;
        this.setPosition = setPosition;
    }

    @Override
    public void execute() {
        slideSubsystem.extend(target.getAsInt(), setPosition);
    }
}
