package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Line;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "Blue Co-op Auto", group = "FTC")
public class BlueCoopAuto extends LinearOpMode {
    Pose2d start = new Pose2d(-46, -70, Math.toRadians(90));
    MecanumDrive drive = new MecanumDrive(hardwareMap, start);

    @Override
    public void runOpMode() throws InterruptedException {
        TrajectoryActionBuilder tab1 = drive.actionBuilder(start)
                .setTangent(Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(-55, -53), Math.toRadians(225))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-5, -53), Math.toRadians(0))
                .waitSeconds(2)
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
