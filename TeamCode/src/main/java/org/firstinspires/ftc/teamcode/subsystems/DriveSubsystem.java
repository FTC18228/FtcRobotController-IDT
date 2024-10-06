package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotMotors;

public class DriveSubsystem extends SubsystemBase {
    RobotMotors robot;
    RevIMU imu;

    public DriveSubsystem(HardwareMap hmap, RobotMotors robot) {
        imu = new RevIMU(hmap);
        imu.init();
    }

    /**
     * Update drive position
     *
     * @param leftX The x amount on the left joystick, or the value affecting horizontal movement
     * @param leftY The y amount on the left joystick, or the value affecting vertical movement
     * @param rightX The x amount on the right joystick, or the value affecting turning movement
     */
    public void drive(double leftX, double leftY, double rightX) {
        robot.drive.driveFieldCentric(leftX, leftY, rightX, imu.getRotation2d().getDegrees(), false);
    }
}
