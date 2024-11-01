package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotMotors;

public class DriveSubsystem extends SubsystemBase {

    RobotMotors robot;
    Gamepad gamepad;
    public DriveSubsystem(RobotMotors robot, Gamepad gamepad) {
        this.robot = robot;
        this.gamepad = gamepad;
    }
    /**
     * Update drive position
     *
     * @param leftX The x amount on the left joystick, or the value affecting horizontal movement
     * @param leftY The y amount on the left joystick, or the value affecting vertical movement
     * @param rightX The x amount on the right joystick, or the value affecting turning movement
     */
    public void drive(double leftX, double leftY, double rightX) {
        robot.drive(leftX, leftY, rightX, gamepad);
    }
}
