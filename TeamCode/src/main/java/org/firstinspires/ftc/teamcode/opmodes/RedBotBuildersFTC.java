package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.HorizontalSlideCommand;
import org.firstinspires.ftc.teamcode.commands.VerticalSlideCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.HorizontalSlideSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VerticalSlideSubsystem;

@TeleOp(name = "18228 FTC", group = "FTC")
public class RedBotBuildersFTC extends CommandOpMode {
    DriveSubsystem driveSubsystem;
    HorizontalSlideSubsystem hSlideSubsystem;
    VerticalSlideSubsystem vSlideSubsystem;
    
    DriveCommand driveCommand;
    HorizontalSlideCommand hSlideCommand;
    VerticalSlideCommand vSlideCommandAdd;
    VerticalSlideCommand vSlideCommandSet;
    

    @Override
    public void initialize() {
        driveSubsystem = new DriveSubsystem(hardwareMap);
        hSlideSubsystem = new HorizontalSlideSubsystem(hardwareMap);
        vSlideSubsystem = new VerticalSlideSubsystem(hardwareMap);

        driveCommand = new DriveCommand(driveSubsystem, () -> gamepad1.left_stick_x, () -> gamepad1.left_stick_y, () -> gamepad1.right_stick_x);
        hSlideCommand = new HorizontalSlideCommand(hSlideSubsystem, () -> gamepad1.left_trigger);
        vSlideCommandAdd = new VerticalSlideCommand(vSlideSubsystem, () -> Math.round(gamepad1.right_trigger * 100), false);
        vSlideCommandSet = new VerticalSlideCommand(vSlideSubsystem, () -> Math.round(gamepad1.right_trigger * 100), true);

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);
    }

    public void runOpMode() throws InterruptedException {
        initialize();

        waitForStart();

        while (!isStopRequested()) {

        }
    }
}
