package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotMotors;
import org.firstinspires.ftc.teamcode.commands.ClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.ClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.FastmodeCommand;
import org.firstinspires.ftc.teamcode.commands.HorizontalSlideCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeHingeLowerCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeHingeRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.OuttakeCommand;
import org.firstinspires.ftc.teamcode.commands.SlowmodeCommand;
import org.firstinspires.ftc.teamcode.commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.TransferDownCommand;
import org.firstinspires.ftc.teamcode.commands.TransferUpCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideHBCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideHangCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideHomeCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HorizontalSlideSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeHingeSubsytem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SlowmodeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

import java.util.function.DoubleSupplier;

@TeleOp(name = "18228 FTC", group = "FTC")
public class RedBotBuildersFTC extends CommandOpMode {
    double SPEED = 1;
    final int HSLIDEMAX = 100;

    public RobotMotors robot;

    DriveSubsystem driveSubsystem;
    HorizontalSlideSubsystem hSlideSubsystem;
    VerticalSlideSubsystem vSlideSubsystem;
    IntakeSubsystem intakeSubsystem;
    TransferSubsystem transferSubsystem;
    ClawSubsystem clawSubsystem;
    IntakeHingeSubsytem hingeSubsytem;
    SlowmodeSubsystem slowmodeSubsystem;

    DriveCommand driveCommand;
    

    @Override
    public void initialize() {
        robot = new RobotMotors(hardwareMap);

        GamepadEx driver = new GamepadEx(gamepad1);
        GamepadEx operator = new GamepadEx(gamepad2);

        driveSubsystem = new DriveSubsystem(robot, gamepad1);
        hSlideSubsystem = new HorizontalSlideSubsystem(hardwareMap);
        vSlideSubsystem = new VerticalSlideSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        transferSubsystem = new TransferSubsystem(hardwareMap);
        clawSubsystem = new ClawSubsystem(hardwareMap);
        hingeSubsytem = new IntakeHingeSubsytem(hardwareMap);
        slowmodeSubsystem = new SlowmodeSubsystem(robot);

        driveCommand = new DriveCommand(driveSubsystem, driver::getLeftX, driver::getLeftY, driver::getRightX);

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);

        // Slowmode
        new Trigger(() -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER) || driver.getButton(GamepadKeys.Button.RIGHT_BUMPER)).whenActive(
                new SlowmodeCommand(slowmodeSubsystem)
        ).whenInactive(
                new FastmodeCommand(slowmodeSubsystem)
        );

        // H slides
        new Trigger(() -> operator.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5).whenActive(
                new SequentialCommandGroup(
                        new HorizontalSlideCommand(hSlideSubsystem, 0),
                        new ClawOpenCommand(clawSubsystem),
                        new WaitCommand(750),
                        new IntakeHingeLowerCommand(hingeSubsytem)
                )
        );

        new Trigger(() -> operator.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5).whenActive(
                new SequentialCommandGroup(
                        new IntakeHingeRaiseCommand(hingeSubsytem),
                        new WaitCommand(200),
                        new HorizontalSlideCommand(hSlideSubsystem, HSLIDEMAX)

                )
        );

        // Intake
        operator.getGamepadButton(GamepadKeys.Button.A).whenActive(
                new SequentialCommandGroup(

                        new IntakeCommand(intakeSubsystem)

                )
        ).whenInactive(
                new SequentialCommandGroup(
                        new StopIntakeCommand(intakeSubsystem)
                )
        );

        // Outtake
        operator.getGamepadButton(GamepadKeys.Button.B).whenActive(
                new SequentialCommandGroup(

                        new OuttakeCommand(intakeSubsystem)

                )
        ).whenInactive(
                new StopIntakeCommand(intakeSubsystem)
        );

        // Transfer
        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).toggleWhenPressed(
                new SequentialCommandGroup(

                        new TransferUpCommand(transferSubsystem)

                ),
                new SequentialCommandGroup(
                        new TransferDownCommand(transferSubsystem)
                )
        );

        // Claw + hinge
        operator.getGamepadButton(GamepadKeys.Button.Y).toggleWhenPressed(
                new SequentialCommandGroup(

                        new ClawCloseCommand(clawSubsystem),
                        new WaitCommand(450),
                        new IntakeHingeLowerCommand(hingeSubsytem)

                ),
                new SequentialCommandGroup(

                        new ClawOpenCommand(clawSubsystem),
                        new WaitCommand(450),
                        new IntakeHingeRaiseCommand(hingeSubsytem)

                )
        );

        // Sample
        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenActive(
                new SequentialCommandGroup(
                        new ClawOpenCommand(clawSubsystem),
                        new WaitCommand(500),
                        new ParallelCommandGroup(
                                new TransferDownCommand(transferSubsystem),
                                new VerticalSlideHomeCommand(vSlideSubsystem)
                        )
                )
        );

        // Sample
        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenActive(
                new SequentialCommandGroup(
                        new ClawCloseCommand(clawSubsystem),
                        new ParallelCommandGroup(
                                new VerticalSlideHBCommand(vSlideSubsystem),
                                new TransferUpCommand(transferSubsystem)

                        )

                )
        );

        // Solo claw
        operator.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawCloseCommand(clawSubsystem),
                new ClawOpenCommand(clawSubsystem)
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

        //TODO: Enable this at nationals
       /*operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenActive(
                new SequentialCommandGroup(
                        new VerticalSlideHangCommand(vSlideSubsystem),
                        new WaitCommand(1000),
                        new VerticalSlideCommand(vSlideSubsystem, 0)
                )
        );*/

        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenActive(
                new SequentialCommandGroup(
                        new VerticalSlideHangCommand(vSlideSubsystem)
                )
        );
    }

}
