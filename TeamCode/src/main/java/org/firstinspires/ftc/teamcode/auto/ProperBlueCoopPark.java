package org.firstinspires.ftc.teamcode.auto;

import androidx.collection.ArraySet;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OTOSDrive;
import org.firstinspires.ftc.teamcode.commands.ActionCommand;
import org.firstinspires.ftc.teamcode.commands.TransferDownCommand;
import org.firstinspires.ftc.teamcode.commands.TransferUpCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HorizontalSlideSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;

@Autonomous(name = "Blue Co-op", group = "Autonomous")
public class ProperBlueCoopPark extends CommandOpMode {

    Action deliverPreload;

    Action moveToHumanPlayer;

    Action park;

    ClawSubsystem clawSubsystem;

    TransferSubsystem transferSubsystem;

    HorizontalSlideSubsystem horizontalSlideSubsystem;

    @Override
    public void initialize() {

        horizontalSlideSubsystem = new HorizontalSlideSubsystem(hardwareMap);
        transferSubsystem = new TransferSubsystem(hardwareMap);
        clawSubsystem = new ClawSubsystem(hardwareMap);

        //lock the claw
        clawSubsystem.extend(1);

        // instantiate your MecanumDrive at a particular pose.
        OTOSDrive drive = new OTOSDrive(hardwareMap,
                new Pose2d(14.8, -64, Math.toRadians(-90)));

        //pose to the submersible wall
        Pose2d dropOffPose = new Pose2d(3.4, -32.5, Math.toRadians(-90));

        deliverPreload = drive.actionBuilder(drive.pose)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(dropOffPose, Math.toRadians(90))
                .build();

        moveToHumanPlayer = drive.actionBuilder(dropOffPose)
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(50,-55,Math.toRadians(-260)),Math.toRadians(0))
                .build();


        CommandScheduler.getInstance().schedule(
                new WaitUntilCommand(this::isStarted).andThen(
                        new SequentialCommandGroup(
                                new WaitCommand(15000), //delay 15 secs
                                new ActionCommand(deliverPreload, new ArraySet<>()),
                                new TransferUpCommand(transferSubsystem),
                                new WaitCommand(1000),
                                new ActionCommand(moveToHumanPlayer, new ArraySet<>()),
                                new TransferDownCommand(transferSubsystem)

                        )
                )
        );

    }
}
