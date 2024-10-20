package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeHingeSubsytem;

public class IntakeHingeLowerCommand extends CommandBase {
    IntakeHingeSubsytem hingeSubsytem;

    /**
     * Lower the intake hinge
     * @param subsystem The hinge subsystem
     */
    public IntakeHingeLowerCommand(IntakeHingeSubsytem subsystem) {
        hingeSubsytem = subsystem;
    }

    @Override
    public void execute() {
        hingeSubsytem.move(0);
    }
}
