package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotMotors;

public class DriveSubsystem extends SubsystemBase {

    RobotMotors robot;
    public DriveSubsystem(RobotMotors robot) {
        this.robot = robot;
    }
    /**
     * Update drive position
     *
     * @param leftX The x amount on the left joystick, or the value affecting horizontal movement
     * @param leftY The y amount on the left joystick, or the value affecting vertical movement
     * @param rightX The x amount on the right joystick, or the value affecting turning movement
     * @param speed The speed multiplier
     */
    public void drive(double leftX, double leftY, double rightX, double speed) {
        robot.drive(leftX, leftY, rightX, speed);
    }
}
