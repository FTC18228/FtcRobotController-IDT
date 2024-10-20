package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotMotors;
import org.firstinspires.ftc.teamcode.commands.ClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.ClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.HorizontalSlideCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.OuttakeCommand;
import org.firstinspires.ftc.teamcode.commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.TransferDownCommand;
import org.firstinspires.ftc.teamcode.commands.TransferUpCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HorizontalSlideSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

import java.util.function.DoubleSupplier;

@TeleOp(name = "18228 FTC", group = "FTC")
public class RedBotBuildersFTC extends CommandOpMode {
    final double SPEED = 1;

    public RobotMotors robot;

    DriveSubsystem driveSubsystem;
    HorizontalSlideSubsystem hSlideSubsystem;
    VerticalSlideSubsystem vSlideSubsystem;
    IntakeSubsystem intakeSubsystem;
    TransferSubsystem transferSubsystem;
    ClawSubsystem clawSubsystem;

    DriveCommand driveCommand;
    HorizontalSlideCommand hSlideCommand;
    VerticalSlideCommand vSlideCommandAdd;
    VerticalSlideCommand vSlideCommandSet;
    IntakeCommand intakeCommand;
    StopIntakeCommand stopIntakeCommand;
    OuttakeCommand outtakeCommand;
    TransferUpCommand transferUpCommand;
    TransferDownCommand transferDownCommand;
    ClawOpenCommand clawOpenCommand;
    ClawCloseCommand clawCloseCommand;
    

    @Override
    public void initialize() {
        Motor frontLeft = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor frontRight = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor backLeft = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor backRight = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        robot = new RobotMotors(hardwareMap);

        driveSubsystem = new DriveSubsystem(robot);
        hSlideSubsystem = new HorizontalSlideSubsystem(hardwareMap);
        vSlideSubsystem = new VerticalSlideSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        transferSubsystem = new TransferSubsystem(hardwareMap);
        clawSubsystem = new ClawSubsystem(hardwareMap);

        driveCommand = new DriveCommand(driveSubsystem, () -> gamepad1.left_stick_x, () -> gamepad1.left_stick_y, () -> gamepad1.right_stick_x, invertTrigger());
        hSlideCommand = new HorizontalSlideCommand(hSlideSubsystem, () -> gamepad1.left_trigger);
        vSlideCommandAdd = new VerticalSlideCommand(vSlideSubsystem, () -> Math.round(gamepad1.right_trigger * 100), false);
        vSlideCommandSet = new VerticalSlideCommand(vSlideSubsystem, () -> Math.round(gamepad1.right_trigger * 100), true);
        intakeCommand = new IntakeCommand(intakeSubsystem);
        stopIntakeCommand = new StopIntakeCommand(intakeSubsystem);
        outtakeCommand = new OuttakeCommand(intakeSubsystem);
        transferUpCommand = new TransferUpCommand(transferSubsystem);
        transferDownCommand = new TransferDownCommand(transferSubsystem);
        clawOpenCommand = new ClawOpenCommand(clawSubsystem);
        clawCloseCommand = new ClawCloseCommand(clawSubsystem);

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);
    }
    private DoubleSupplier invertTrigger() {
        if(gamepad1.right_trigger == 0) {
            return () -> 1 * SPEED;
        }
        return () -> (1 - gamepad1.right_trigger) * SPEED;
    }

}
