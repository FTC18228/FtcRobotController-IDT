package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private DriveSubsystem drive;
    private DoubleSupplier leftX;
    private DoubleSupplier leftY;
    private DoubleSupplier rightX;
    private Telemetry tele;

    /**
     * Update drive position
     *
     * @param driveSubsystem The subsystem to execute the command on
     * @param leftx The x amount on the left joystick, or the value affecting horizontal movement
     * @param lefty The y amount on the left joystick, or the value affecting vertical movement
     * @param rightx The x amount on the right joystick, or the value affecting turning movement
     */
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
        /*tele.addData("leftX", leftX.getAsDouble());
        tele.addData("leftY", leftY.getAsDouble());
        tele.addData("rightX", -rightX.getAsDouble());
        tele.addData("speed", speed.getAsDouble());*/
    }
}
