package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem extends SubsystemBase {
    DcMotor intake;
    public IntakeSubsystem(HardwareMap hmap) {
        intake = hmap.get(DcMotor.class, "intake");
    }

    /**
     * Move the intake forwards
     */
    public void start() {
        intake.setPower(1);
    }

    /**
     * Stop the intake
     */
    public void stop() {
        intake.setPower(0);
    }

    /**
     * Move the intake backwards
     */
    public void reverse() {
        intake.setPower(-1);
    }
}
