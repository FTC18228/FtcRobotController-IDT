package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Line;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.commands.ActionCommand;
import org.firstinspires.ftc.teamcode.commands.ClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.ClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.HorizontalSlideCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeHingeLowerCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeHingeRaiseCommand;
import org.firstinspires.ftc.teamcode.commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.TransferDownCommand;
import org.firstinspires.ftc.teamcode.commands.TransferUpCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HorizontalSlideSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeHingeSubsytem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

@Config
@Autonomous(name = "Blue Co-op Auto", group = "FTC")
public class BlueCoopAuto extends CommandOpMode {
    Pose2d start = new Pose2d(-46, -70, Math.toRadians(90));
    MecanumDrive drive = new MecanumDrive(hardwareMap, start);

    public void initialize() {
        drive.pose = start;

        Action standard = drive.actionBuilder(drive.pose)
                .setReversed(true)
                .setTangent(Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                .build();

        Action pos1 = drive.actionBuilder(drive.pose)
                .setReversed(true)
                .setTangent(Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-48, -40), Math.toRadians(90))
                .build();

        Action pos2 = drive.actionBuilder(drive.pose)
                .setReversed(true)
                .setTangent(Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-58, -40), Math.toRadians(90))
                .build();

        Action pos3 = drive.actionBuilder(drive.pose)
                .setReversed(true)
                .setTangent(Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-68, -40), Math.toRadians(90))
                .build();

        Action collectPos = drive.actionBuilder(drive.pose)
                .setReversed(true)
                .setTangent(Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-5, -53), Math.toRadians(0))
                .build();

        Action finalPos = drive.actionBuilder(drive.pose)
                .setReversed(true)
                .setTangent(Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-25, 0), Math.toRadians(0))
                .build();

        ClawSubsystem clawSubsystem = new ClawSubsystem(hardwareMap);
        VerticalSlideSubsystem vSlideSubsystem = new VerticalSlideSubsystem(hardwareMap);
        TransferSubsystem transferSubsystem = new TransferSubsystem(hardwareMap);
        HorizontalSlideSubsystem hSlideSubsystem = new HorizontalSlideSubsystem(hardwareMap);
        IntakeHingeSubsytem hingeSubsytem = new IntakeHingeSubsytem(hardwareMap);
        IntakeSubsystem intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        final int VSLIDEMAX = 100;
        final int HSLIDEMAX = 100;

        SequentialCommandGroup specimen = new SequentialCommandGroup(
                new ClawCloseCommand(clawSubsystem),
                new ParallelCommandGroup(
                        new ActionCommand(standard, null),
                        new VerticalSlideCommand(vSlideSubsystem, VSLIDEMAX),
                        new TransferUpCommand(transferSubsystem)
                ),
                new ClawOpenCommand(clawSubsystem),
                new ParallelCommandGroup(
                        new TransferDownCommand(transferSubsystem),
                        new SequentialCommandGroup(
                                new WaitCommand(500),
                                new VerticalSlideCommand(vSlideSubsystem, 0)
                        )
                )
        );

        SequentialCommandGroup intake = new SequentialCommandGroup(
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
        );

        CommandScheduler.getInstance().schedule(
                new WaitUntilCommand(this::isStarted).andThen(
                        new SequentialCommandGroup(
                                specimen,
                                new ActionCommand(collectPos, null),
                                intake,
                                specimen,
                                new ActionCommand(pos1, null),
                                intake,
                                specimen,
                                new ActionCommand(pos2, null),
                                intake,
                                specimen,
                                new ActionCommand(pos3, null),
                                intake,
                                specimen,
                                new ActionCommand(finalPos, null)
                        )
                )
        );
    }
}
