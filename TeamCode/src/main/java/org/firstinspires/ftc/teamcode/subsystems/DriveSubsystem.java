package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem extends SubsystemBase {
    MecanumDrive drive;
    RevIMU imu;

    public DriveSubsystem(HardwareMap hmap) {
        imu = new RevIMU(hmap);

        Motor frontLeft = new Motor(hmap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor frontRight = new Motor(hmap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor backLeft = new Motor(hmap, "frontLeft", Motor.GoBILDA.RPM_435);
        Motor backRight = new Motor(hmap, "frontLeft", Motor.GoBILDA.RPM_435);
        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

        imu.init();
    }

    public void drive(double leftX, double leftY, double rightX) {
        drive.driveFieldCentric(leftX, leftY, rightX, imu.getRotation2d().getDegrees(), false);
    }
}
