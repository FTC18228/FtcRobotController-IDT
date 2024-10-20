package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeHingeSubsytem extends SubsystemBase {
    Servo left;
    Servo right;

    public IntakeHingeSubsytem(HardwareMap hmap) {
        left = hmap.get(Servo.class, "leftHinge");
        right = hmap.get(Servo.class, "rightHinge");
    }

    /**
     * Move the intake hinge
     * @param pos The position to move the hinge to
     */
    public void move(double pos) {
        left.setPosition(pos);
        right.setPosition(pos);
    }
}
