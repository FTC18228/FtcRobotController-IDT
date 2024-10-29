package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
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
import org.firstinspires.ftc.teamcode.commands.VerticalSlideHBCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideHomeCommand;
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
    final int VSLIDEMAX = 2500;

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
        robot = new RobotMotors(hardwareMap);

        GamepadEx driver = new GamepadEx(gamepad1);
        GamepadEx operator = new GamepadEx(gamepad2);

        driveSubsystem = new DriveSubsystem(robot);
        hSlideSubsystem = new HorizontalSlideSubsystem(hardwareMap);
        vSlideSubsystem = new VerticalSlideSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        transferSubsystem = new TransferSubsystem(hardwareMap);
        clawSubsystem = new ClawSubsystem(hardwareMap);
        hingeSubsytem = new IntakeHingeSubsytem(hardwareMap);

        driveCommand = new DriveCommand(driveSubsystem, driver::getLeftX, driver::getLeftY, driver::getRightX, invertTrigger());

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);

       /* schedule(new RunCommand(()->{
            telemetry.addData("slide", vSlideSubsystem.getPosition());
            telemetry.update();

        }));*/

        new Trigger(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5;
            }
        }).whenActive(
                new SequentialCommandGroup(
                    new HorizontalSlideCommand(hSlideSubsystem, 0),
                    new ClawOpenCommand(clawSubsystem),
                    new WaitCommand(300),
                    new IntakeHingeLowerCommand(hingeSubsytem)
                )
        );
        new Trigger(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5;
            }
        }).whenActive(
                new SequentialCommandGroup(
                        new IntakeHingeRaiseCommand(hingeSubsytem),
                        new WaitCommand(200),
                        new HorizontalSlideCommand(hSlideSubsystem, HSLIDEMAX)

                )
        );

        new Trigger(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return operator.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5;
            }
        }).whenActive(
                new SequentialCommandGroup(
                        new HorizontalSlideCommand(hSlideSubsystem, 0),
                        new ClawOpenCommand(clawSubsystem),
                        new WaitCommand(300),
                        new IntakeHingeLowerCommand(hingeSubsytem)
                )
        );
        new Trigger(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return operator.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5;
            }
        }).whenActive(
                new SequentialCommandGroup(
                        new IntakeHingeRaiseCommand(hingeSubsytem),
                        new WaitCommand(200),
                        new HorizontalSlideCommand(hSlideSubsystem, HSLIDEMAX)

                )
        );

        // Intake
        driver.getGamepadButton(GamepadKeys.Button.A).whenActive(
                new SequentialCommandGroup(

                        new IntakeCommand(intakeSubsystem)

                )
        ).whenInactive(
                new SequentialCommandGroup(
                    new StopIntakeCommand(intakeSubsystem)
                )
        );

        driver.getGamepadButton(GamepadKeys.Button.B).whenActive(
                new SequentialCommandGroup(

                        new OuttakeCommand(intakeSubsystem)

                )
        ).whenInactive(
                new StopIntakeCommand(intakeSubsystem)
        );

        driver.getGamepadButton(GamepadKeys.Button.X).whenActive(
                new SequentialCommandGroup(
                        new TransferUpCommand(transferSubsystem)

                )
        ).whenInactive(
                new TransferDownCommand(transferSubsystem)
        );

        driver.getGamepadButton(GamepadKeys.Button.Y).toggleWhenPressed(
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

        operator.getGamepadButton(GamepadKeys.Button.A).whenActive(
                new SequentialCommandGroup(

                        new IntakeCommand(intakeSubsystem)

                )
        ).whenInactive(
                new SequentialCommandGroup(
                        new StopIntakeCommand(intakeSubsystem)
                )
        );

        operator.getGamepadButton(GamepadKeys.Button.B).whenActive(
                new SequentialCommandGroup(

                        new OuttakeCommand(intakeSubsystem)

                )
        ).whenInactive(
                new StopIntakeCommand(intakeSubsystem)
        );

        operator.getGamepadButton(GamepadKeys.Button.X).whenActive(
                new SequentialCommandGroup(
                        new TransferUpCommand(transferSubsystem)

                )
        ).whenInactive(
                new TransferDownCommand(transferSubsystem)
        );

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

        // Specimen
        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenActive(
                new SequentialCommandGroup(
                        new ClawOpenCommand(clawSubsystem),
                        new WaitCommand(250),
                        new ParallelCommandGroup(
                                new TransferDownCommand(transferSubsystem),
                                new VerticalSlideHomeCommand(vSlideSubsystem)
                        )
                )
        );

        // Sample
        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenActive(
                new SequentialCommandGroup(
                        new ClawCloseCommand(clawSubsystem),
                        new ParallelCommandGroup(
                                new VerticalSlideHBCommand(vSlideSubsystem),
                                new TransferUpCommand(transferSubsystem)

                        )

                )
        );

        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenActive(
                new SequentialCommandGroup(
                        new ClawOpenCommand(clawSubsystem),
                        new WaitCommand(250),
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

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenActive(
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
        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenActive(
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
