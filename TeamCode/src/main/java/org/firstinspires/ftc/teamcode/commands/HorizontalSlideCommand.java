package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HorizontalSlideSubsystem;

import java.util.function.DoubleSupplier;

public class HorizontalSlideCommand extends CommandBase {
    private HorizontalSlideSubsystem slideSubsystem;
    private DoubleSupplier distance;

    /**
     * Extend the horizontal slides
     *
     * @param subsystem The subsystem to execute the command on
     * @param distance The distance to extend the slides to in the range [0.0, 1.0]
     */
    public HorizontalSlideCommand(HorizontalSlideSubsystem subsystem, DoubleSupplier distance) {
        slideSubsystem = subsystem;
        this.distance = distance;
    }

    @Override
    public void execute() {
        slideSubsystem.extend(distance.getAsDouble());
    }
}
