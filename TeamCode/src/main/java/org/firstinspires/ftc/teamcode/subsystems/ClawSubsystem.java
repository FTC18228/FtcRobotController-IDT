package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystem extends SubsystemBase {
    Servo claw;

    public ClawSubsystem(HardwareMap hmap) {
        claw = hmap.get(Servo.class, "claw");
        extend(0.6);
    }

    /**
     * Open/close the claw
     *
     * @param distance The distance to extend the slides to in the range [0.0, 1.0]
     */
    public void extend(double distance) {
        claw.setPosition(distance);
    }
}
