package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private DriveSubsystem drive;
    private DoubleSupplier leftX;
    private DoubleSupplier leftY;
    private DoubleSupplier rightX;

    public DriveCommand(DriveSubsystem driveSubsystem, DoubleSupplier leftx, DoubleSupplier lefty, DoubleSupplier rightx) {
        drive = driveSubsystem;
        this.leftX = leftx;
        this.leftY = lefty;
        this.rightX = rightx;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.drive(leftX.getAsDouble(), leftY.getAsDouble(), rightX.getAsDouble());
    }
}
