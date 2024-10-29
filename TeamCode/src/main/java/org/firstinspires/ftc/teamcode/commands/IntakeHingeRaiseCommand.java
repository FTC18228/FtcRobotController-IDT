package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeHingeSubsytem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeHingeRaiseCommand extends CommandBase {
    IntakeHingeSubsytem hingeSubsytem;

    /**
     * Raise the intake hinge
     * @param subsystem The hinge subsystem
     */

    public IntakeHingeRaiseCommand(IntakeHingeSubsytem subsystem) {
        hingeSubsytem = subsystem;
    }

    @Override
    public void initialize() {
        hingeSubsytem.move(0);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
