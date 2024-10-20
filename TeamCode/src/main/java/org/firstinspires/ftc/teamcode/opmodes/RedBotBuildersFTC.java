package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotMotors;
import org.firstinspires.ftc.teamcode.commands.ClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.ClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.HorizontalSlideCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeHingeLowerCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeHingeRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.OuttakeCommand;
import org.firstinspires.ftc.teamcode.commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.TransferDownCommand;
import org.firstinspires.ftc.teamcode.commands.TransferUpCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HorizontalSlideSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeHingeSubsytem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

@TeleOp(name = "18228 FTC", group = "FTC")
public class RedBotBuildersFTC extends CommandOpMode {
    final double SPEED = 1;
    final int HSLIDEMAX = 100;
    final int VSLIDEMAX = 100;

    public RobotMotors robot;

    DriveSubsystem driveSubsystem;
    HorizontalSlideSubsystem hSlideSubsystem;
    VerticalSlideSubsystem vSlideSubsystem;
    IntakeSubsystem intakeSubsystem;
    TransferSubsystem transferSubsystem;
    ClawSubsystem clawSubsystem;
    IntakeHingeSubsytem hingeSubsytem;

    DriveCommand driveCommand;
    

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

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);

        // Intake
        new Trigger(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return gamepad1.a;
            }
        }).whenActive(
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new HorizontalSlideCommand(hSlideSubsystem, HSLIDEMAX),
                                new IntakeHingeLowerCommand(hingeSubsytem)
                        ),
                        new IntakeCommand(intakeSubsystem),
                        new WaitCommand(500),
                        new StopIntakeCommand(intakeSubsystem),
                        new ParallelCommandGroup(
                                new HorizontalSlideCommand(hSlideSubsystem, 0),
                                new IntakeHingeRaiseCommand(hingeSubsytem)
                        )
                )
        );

        // Specimen
        new Trigger(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return gamepad1.left_bumper;
            }
        }).whenActive(
                new SequentialCommandGroup(
                        new ParallelCommandGroup(
                                new VerticalSlideCommand(vSlideSubsystem, VSLIDEMAX),
                                new TransferDownCommand(transferSubsystem)
                        ),
                        new ClawCloseCommand(clawSubsystem),
                        new ParallelCommandGroup(
                                new VerticalSlideCommand(vSlideSubsystem, VSLIDEMAX),
                                new SequentialCommandGroup(
                                        new WaitCommand(500),
                                        new TransferUpCommand(transferSubsystem)
                                )
                        ),
                        new VerticalSlideCommand(vSlideSubsystem, 100), //TODO: Figure this height out
                        new ClawOpenCommand(clawSubsystem),
                        new ParallelCommandGroup(
                                new TransferDownCommand(transferSubsystem),
                                new SequentialCommandGroup(
                                        new WaitCommand(500),
                                        new VerticalSlideCommand(vSlideSubsystem, 0)
                                )
                        )
                )
        );

        // Sample
        new Trigger(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return gamepad1.right_bumper;
            }
        }).whenActive(
                new SequentialCommandGroup(
                        new ClawCloseCommand(clawSubsystem),
                        new ParallelCommandGroup(
                                new VerticalSlideCommand(vSlideSubsystem, VSLIDEMAX),
                                new SequentialCommandGroup(
                                        new WaitCommand(500),
                                        new TransferUpCommand(transferSubsystem)
                                )
                        ),
                        new ClawOpenCommand(clawSubsystem),
                        new ParallelCommandGroup(
                                new TransferDownCommand(transferSubsystem),
                                new SequentialCommandGroup(
                                        new WaitCommand(500),
                                        new VerticalSlideCommand(vSlideSubsystem, 0)
                                )
                        )
                )
        );

        // Hanging
        //     _______
        //    |/      |
        //    |      (_)
        //    |      \|/
        //    |       |
        //    |      / \
        //    |
        // ___|___
        //
        // Not that type
        // https://youtu.be/gy6nh_1mA18?si=iQktB4GEbSwi5Crf&t=897
        // That type

        new Trigger(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return gamepad1.dpad_up;
            }
        }).whenActive(
                new SequentialCommandGroup(
                        new ClawOpenCommand(clawSubsystem),
                        new ParallelCommandGroup(
                                new VerticalSlideCommand(vSlideSubsystem, 100), //TODO: Figure this out
                                new SequentialCommandGroup(
                                        new WaitCommand(500),
                                        new TransferUpCommand(transferSubsystem)
                                )
                        ),
                        new ClawCloseCommand(clawSubsystem),
                        new VerticalSlideCommand(vSlideSubsystem, 0)
                )
        );
    }
    private DoubleSupplier invertTrigger() {
        if(gamepad1.left_trigger == 1) {
            return () -> 0.1 * SPEED;
        }
        return () -> (1 - gamepad1.left_trigger) * SPEED;
    }

}
