package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class VerticalSlideSubsystem extends SubsystemBase {
    DcMotor slide;

    int HIGH_BASKET = 3200;

    public VerticalSlideSubsystem(HardwareMap hmap) {
        slide = hmap.get(DcMotor.class, "verticalSlide");
        slide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Extend the vertical slides
     *
     * @param target The target position of the slides
     */
    public void extend(int target) {

        slide.setTargetPosition(target);
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide.setPower(1);

    }

    public int getPosition(){
        return slide.getCurrentPosition();
    }

    public void HighBasket() {
        extend(HIGH_BASKET);
    }
    public void AscentLevel() {
        extend(HIGH_BASKET / 2);
    }
    public boolean isAtHighBasket() {
        return slide.getCurrentPosition() >= (HIGH_BASKET - 150);
    }
    public boolean isAtHalf(){
        return slide.getCurrentPosition() >= ((HIGH_BASKET / 2) - 150);
    }
}
