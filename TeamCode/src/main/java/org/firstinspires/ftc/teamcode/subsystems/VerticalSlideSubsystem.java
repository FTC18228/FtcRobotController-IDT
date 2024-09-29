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

    public void extend(int target, boolean setPosition) {
        if(setPosition) {
            slide.setTargetPosition(target);
        }
        else {
            slide.setTargetPosition(slide.getCurrentPosition() + target);
        }
    }
}
