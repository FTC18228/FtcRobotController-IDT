package org.firstinspires.ftc.teamcode.subsystems;

import android.graphics.Color;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {
    CRServo left;
    CRServo right;
    CRServo middle;

    private final NormalizedColorSensor colourSensor;
    private final float[] hsvValues = new float[3];

    private final Telemetry telemetry;

    public IntakeSubsystem(HardwareMap hmap, Telemetry tele) {
        left = hmap.get(CRServo.class, "intakeLeft");
        right = hmap.get(CRServo.class, "intakeRight");
        middle = hmap.get(CRServo.class, "intakeMiddle");

        left.setDirection(DcMotorSimple.Direction.REVERSE);

        colourSensor = hmap.get(NormalizedColorSensor.class, "colourSensor");
        telemetry = tele;
    }

    /**
     * Move the intake forwards
     */
    public void start() {
        if(IsSampleLoaded()){
            telemetry.addData("start", "Not Loaded");
            telemetry.update();
            stop();
        }else {
            right.setPower(1);
            left.setPower(1);
            middle.setPower(1);
        }

    }

    /**
     * Stop the intake
     */
    public void stop() {
        left.setPower(0);
        right.setPower(0);
        middle.setPower(0);
    }

    /**
     * Move the intake backwards
     */
    public void reverse() {
        left.setPower(-1);
        right.setPower(-1);
        middle.setPower(-1);
    }

    public boolean IsSampleLoaded(){



        NormalizedRGBA colors = colourSensor.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);

        telemetry.addData("HSV", hsvValues[0]);
        telemetry.update();

        if(hsvValues[0] >= 170 && hsvValues[0] <= 190){
            //this is the default reflection from the 3D print
            return false;
        }
        if(hsvValues[0] > 200) {
            return true; // blue
        }
        if(hsvValues[0] >= 70 && hsvValues[0] <=100) {
            return true; //neutral
        }
        return hsvValues[0] >= 20; //red

    }
}
