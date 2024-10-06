package org.firstinspires.ftc.teamcode.auto.mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HorizontalSlides {
    Servo leftSlide;
    Servo rightSlide;

    public HorizontalSlides(HardwareMap hmap) {
        leftSlide = hmap.get(Servo.class, "leftSlide");
        rightSlide = hmap.get(Servo.class, "rightSlide");
    }
    public class ExtendTo implements Action {
        boolean init = false;
        double target;
        double start;

        public ExtendTo(double target) {
            this.target = target;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(!init) {
                start = leftSlide.getPosition();
                leftSlide.setPosition(target);
                rightSlide.setPosition(target);
                init = true;
            }
            double pos = leftSlide.getPosition();
            if(start > target && pos <= target) {
                return false;
            }
            else if(start < target && pos >= target) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    /**
     * Extend the horizontal slides
     *
     * @param target The distance to extend the slides to in the range [0.0, 1.0]
     * @return The action
     */
    public Action extendTo(double target) {
        return new ExtendTo(target);
    }
}
