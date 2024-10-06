package org.firstinspires.ftc.teamcode.auto.mechanisms;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSystem {
    DcMotor intake;
    public IntakeSystem(HardwareMap hmap) {
        intake = hmap.get(DcMotor.class, "intake");
    }

    public class Intake implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intake.setPower(1);
            return false;
        }
    }
    public class Stop implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intake.setPower(0);
            return false;
        }
    }
    public class Outtake implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intake.setPower(-1);
            return false;
        }
    }

    /**
     * Start the intake
     *
     * @return The action
     */
    public Action intake() {
        return new Intake();
    }

    /**
     * Stop the intake
     *
     * @return The action
     */
    public Action stop() {
        return new Stop();
    }

    /**
     * Reverse the intake
     *
     * @return The action
     */
    public Action outtake() {
        return new Outtake();
    }
}
