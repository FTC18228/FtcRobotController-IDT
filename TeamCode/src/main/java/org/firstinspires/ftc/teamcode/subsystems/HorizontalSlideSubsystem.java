package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HorizontalSlideSubsystem extends SubsystemBase {
    Servo leftSlide;
    Servo rightSlide;

    public HorizontalSlideSubsystem(HardwareMap hmap) {
        leftSlide = hmap.get(Servo.class, "leftSlide");
        rightSlide = hmap.get(Servo.class, "rightSlide");
    }

    /**
     * Extend the horizontal slides
     *
     * @param distance The distance to extend the slides to in the range [0.0, 1.0]
     */
    public void extend(double distance) {
        leftSlide.setPosition(distance);
        rightSlide.setPosition(distance);
    }
}
