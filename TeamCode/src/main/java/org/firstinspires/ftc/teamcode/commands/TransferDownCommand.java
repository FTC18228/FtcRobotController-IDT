package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;

public class TransferDownCommand extends CommandBase {
    private TransferSubsystem transferSubsystem;

    /**
     * Move the transfer arm
     *
     * @param subsystem The subsystem to execute the command on
     */
    public TransferDownCommand(TransferSubsystem subsystem) {
        transferSubsystem = subsystem;
    }

    @Override
    public void execute() {
        transferSubsystem.extend(0);
    }
}