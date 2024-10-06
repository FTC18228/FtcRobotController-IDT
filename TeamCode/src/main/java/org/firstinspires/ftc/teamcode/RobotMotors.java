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
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// create the class RobotMotors
public class RobotMotors {
    // define each motor as separate variable
    public Motor lf;
    public Motor rf;
    public Motor lb;
    public Motor rb;
    public MecanumDrive drive;

    // create the mecanum on instance creation
    public RobotMotors(Motor leftFront, Motor rightFront, Motor leftBack, Motor rightBack) {
        lf = leftFront;
        lb = leftBack;
        rf = rightFront;
        rb = rightBack;
        drive = drive = new MecanumDrive(lf, rf, lb, rb);
    }
}
