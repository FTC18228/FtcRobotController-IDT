package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;

public class TransferUpCommand extends CommandBase {
    private TransferSubsystem transferSubsystem;

    /**
     * Move the transfer arm
     *
     * @param subsystem The subsystem to execute the command on
     */
    public TransferUpCommand(TransferSubsystem subsystem) {
        transferSubsystem = subsystem;
    }

    @Override
    public void initialize() {
        transferSubsystem.extend(1);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}