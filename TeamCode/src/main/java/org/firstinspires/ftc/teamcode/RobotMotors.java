// _____    ___  ___  __    __  ___          __       __  ___
// | __ \   | |  | |  \ \  / /  | |         /  \     |  \ | |
// |    /   | |__| |   \ \/ /   | |        / /\ \    | \ \| |
// | |\ \   | ____ |    |  |    | |____   / ____ \   | |\ \ |
// |_| \_\  |_|  |_|    |__|    |_____|  /_/    \_\  |_| \ _|

// Rhy stop making it look like you did all this work :3
// jk rhy youre my bestie
// beat sonic wave x3000


package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;

// create the class RobotMotors
public class RobotMotors {
    public MecanumDrive drive;

    // create the mecanum on instance creation
    public RobotMotors(HardwareMap hmap) {
        drive = drive = new MecanumDrive(hmap, new Pose2d(0, 0, 0));
    }
    public void drive(double leftX, double leftY, double rightX, double speed) {
        Pose2d estimate = drive.pose;
        double heading = drive.pose.heading.toDouble();
        Vector2d controller = new Vector2d(leftY * speed, -leftX * speed);

        double x = controller.x * Math.cos(-heading) - controller.y * Math.sin(heading);
        double y = controller.x * Math.sin(heading) + controller.y * Math.cos(-heading);

        Vector2d newPos = new Vector2d(x, y);
        drive.setDrivePowers(new PoseVelocity2d(newPos, rightX * speed));
        drive.updatePoseEstimate();
    }
}
