package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.auto.mechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.auto.mechanisms.IntakeSystem;
import org.firstinspires.ftc.teamcode.auto.mechanisms.VerticalSlides;

@Config
@Autonomous(name = "Test Auto", group = "FTC")
public class TestAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d start = new Pose2d(0, 0, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, start);

        HorizontalSlides hslides = new HorizontalSlides(hardwareMap);
        VerticalSlides vslides = new VerticalSlides(hardwareMap);
        IntakeSystem intake = new IntakeSystem(hardwareMap);

        int vision = 1;

        TrajectoryActionBuilder tab1 = drive.actionBuilder(start)
                .lineToX(5)
                .lineToY(5);

        while (!isStopRequested() && !opModeIsActive()) {
            int position = vision;
            telemetry.addData("Position during Init", position);
            telemetry.update();
        }
        int startPosition = vision;
        telemetry.addData("Starting Position", startPosition);
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        Action trajectoryActionChosen;
        if (startPosition == 1) {
            trajectoryActionChosen = tab1.build();
        }

        Actions.runBlocking(
                new SequentialAction(
                        intake.intake(),
                        intake.stop()
                )
        );
    }
}
