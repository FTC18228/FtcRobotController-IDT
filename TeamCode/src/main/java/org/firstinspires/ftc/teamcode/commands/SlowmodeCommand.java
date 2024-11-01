package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.SlowmodeSubsystem;

public class SlowmodeCommand extends CommandBase {

    SlowmodeSubsystem slowmodeSubsystem;

    public SlowmodeCommand(SlowmodeSubsystem slowmodeSubsystem) {
        this.slowmodeSubsystem = slowmodeSubsystem;
    }
    @Override
    public void initialize() {
        slowmodeSubsystem.slow();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
