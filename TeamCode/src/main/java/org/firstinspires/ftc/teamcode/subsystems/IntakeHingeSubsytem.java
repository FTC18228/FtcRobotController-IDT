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

        //This is reversed at the servo level
        //it makes the servos spin at the same speed
        //left.setDirection(Servo.Direction.REVERSE);

        left.setPosition(0);
        right.setPosition(0);
    }

    /**
     * Move the intake hinge
     * @param pos The position to move the hinge to
     */
    public void move(double pos) {
        right.setPosition(pos);
        left.setPosition(pos);

    }
}
