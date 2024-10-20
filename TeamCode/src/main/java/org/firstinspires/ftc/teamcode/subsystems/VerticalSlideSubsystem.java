package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class VerticalSlideSubsystem extends SubsystemBase {
    Motor slide;

    public VerticalSlideSubsystem(HardwareMap hmap) {
        slide = new Motor(hmap, "verticalSlide", Motor.GoBILDA.BARE); //TODO: Change GoBILDA.BARE to something else with 6000rpm if it matters
    }

    /**
     * Extend the vertical slides
     *
     * @param target The target position of the slides
     */
    public void extend(int target) {
        slide.setTargetPosition(target);
    }
}
