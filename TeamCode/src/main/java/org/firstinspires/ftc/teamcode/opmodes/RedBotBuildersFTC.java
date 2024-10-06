package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotMotors;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.HorizontalSlideCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.OuttakeCommand;
import org.firstinspires.ftc.teamcode.commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HorizontalSlideSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

@TeleOp(name = "18228 FTC", group = "FTC")
public class RedBotBuildersFTC extends CommandOpMode {
    public RobotMotors robot;

    DriveSubsystem driveSubsystem;
    HorizontalSlideSubsystem hSlideSubsystem;
    VerticalSlideSubsystem vSlideSubsystem;
    IntakeSubsystem intakeSubsystem;

    DriveCommand driveCommand;
    HorizontalSlideCommand hSlideCommand;
    VerticalSlideCommand vSlideCommandAdd;
    VerticalSlideCommand vSlideCommandSet;
    IntakeCommand intakeCommand;
    StopIntakeCommand stopIntakeCommand;
    OuttakeCommand outtakeCommand;
    

    @Override
    public void initialize() {
        Motor frontLeft = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor frontRight = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor backLeft = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor backRight = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        robot = new RobotMotors(frontLeft, frontRight, backLeft, backRight);

        driveSubsystem = new DriveSubsystem(hardwareMap, robot);
        hSlideSubsystem = new HorizontalSlideSubsystem(hardwareMap);
        vSlideSubsystem = new VerticalSlideSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);

        driveCommand = new DriveCommand(driveSubsystem, () -> gamepad1.left_stick_x, () -> gamepad1.left_stick_y, () -> gamepad1.right_stick_x);
        hSlideCommand = new HorizontalSlideCommand(hSlideSubsystem, () -> gamepad1.left_trigger);
        vSlideCommandAdd = new VerticalSlideCommand(vSlideSubsystem, () -> Math.round(gamepad1.right_trigger * 100), false);
        vSlideCommandSet = new VerticalSlideCommand(vSlideSubsystem, () -> Math.round(gamepad1.right_trigger * 100), true);
        intakeCommand = new IntakeCommand(intakeSubsystem);
        stopIntakeCommand = new StopIntakeCommand(intakeSubsystem);
        outtakeCommand = new OuttakeCommand(intakeSubsystem);

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);
    }

    public void runOpMode() throws InterruptedException {
        initialize();

        waitForStart();

        while (!isStopRequested()) {

        }
    }
}
