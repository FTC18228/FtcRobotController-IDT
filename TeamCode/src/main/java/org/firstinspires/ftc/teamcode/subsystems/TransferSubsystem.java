package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TransferSubsystem extends SubsystemBase {
    Servo transfer;

    public TransferSubsystem(HardwareMap hmap) {
        transfer = hmap.get(Servo.class, "transfer");
    }

    /**
     * Move the transfer arm
     *
     * @param distance The distance to extend the slides to in the range [0.0, 1.0]
     */
    public void extend(double distance) {
        transfer.setPosition(distance);
    }
}
