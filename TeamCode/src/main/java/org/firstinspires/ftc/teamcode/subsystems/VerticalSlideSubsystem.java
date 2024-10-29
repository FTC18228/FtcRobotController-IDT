package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class VerticalSlideSubsystem extends SubsystemBase {
    DcMotor slide;

    int HIGH_BASKET = 3150;

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

    public void HighBasket(){
        extend(HIGH_BASKET);
    }
    public boolean isAtHighBasket(){
        return slide.getCurrentPosition() >= HIGH_BASKET;
    }
}
