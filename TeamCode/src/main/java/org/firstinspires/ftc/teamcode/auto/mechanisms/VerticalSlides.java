package org.firstinspires.ftc.teamcode.auto.mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class VerticalSlides {
    Motor slide;

    public VerticalSlides(HardwareMap hmap) {
        slide = new Motor(hmap, "verticalSlide", Motor.GoBILDA.BARE); //TODO: Change GoBILDA.BARE to something else with 6000rpm if it matters
    }

    public class ExtendTo implements Action {
        boolean init = false;
        int target;
        double start;

        public ExtendTo(int target) {
            this.target = target;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(!init) {
                start = slide.getCurrentPosition();
                slide.setTargetPosition(target);
                init = true;
            }
            double pos = slide.getCurrentPosition();
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
     * Extend the vertical slides
     *
     * @param target The target position of the slides
     * @return The action
     */
    public Action extendTo(int target) {
        return new ExtendTo(target);
    }
}
