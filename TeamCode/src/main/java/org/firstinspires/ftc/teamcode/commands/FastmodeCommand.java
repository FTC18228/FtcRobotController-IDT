package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.SlowmodeSubsystem;

public class FastmodeCommand extends CommandBase {

    SlowmodeSubsystem slowmodeSubsystem;

    public FastmodeCommand(SlowmodeSubsystem slowmodeSubsystem) {
        this.slowmodeSubsystem = slowmodeSubsystem;
    }
    @Override
    public void initialize() {
        slowmodeSubsystem.fast();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
