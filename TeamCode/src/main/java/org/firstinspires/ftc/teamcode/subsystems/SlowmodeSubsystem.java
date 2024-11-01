package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.RobotMotors;

import java.util.function.DoubleSupplier;

public class SlowmodeSubsystem extends SubsystemBase {
    RobotMotors robot;
    public SlowmodeSubsystem(RobotMotors robot) {
        this.robot = robot;
    }
    public void slow() {
        robot.speed = 0.25;
    }
    public void fast() {
        robot.speed = 1;
    }
}
