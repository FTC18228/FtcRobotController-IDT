package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.auto.mechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.auto.mechanisms.IntakeSystem;
import org.firstinspires.ftc.teamcode.auto.mechanisms.VerticalSlides;

@Config
@Autonomous(name = "Standard Auto", group = "FTC")
public class StandardAuto extends LinearOpMode {
    Pose2d start = new Pose2d(-46, -70, Math.toRadians(90));
    MecanumDrive drive = new MecanumDrive(hardwareMap, start);

    HorizontalSlides hslides = new HorizontalSlides(hardwareMap);
    VerticalSlides vslides = new VerticalSlides(hardwareMap);
    IntakeSystem intake = new IntakeSystem(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {
        TrajectoryActionBuilder tab1 = drive.actionBuilder(start)
                .setReversed(true)
                .setTangent(Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-48, -40), Math.toRadians(90))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-58, -40), Math.toRadians(90))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-68, -40), Math.toRadians(90))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-25, 0), Math.toRadians(0))
                .waitSeconds(0.25);
    }
}
