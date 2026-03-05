package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.LauncherSubsystem;

public class setFlywheelPIDOn extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final LauncherSubsystem launcher;
    private final double targetRPM;

    /**
     * @param subsystem The launcher subsystem instance
     * @param rpm The desired velocity in RPM
     */
    public setFlywheelPIDOn(LauncherSubsystem subsystem, double rpm) {
        this.launcher = subsystem;
        this.targetRPM = rpm;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        // Repeatedly calls your PID function to adjust motor power
        while (true) {
            launcher.setFlywheelRPMPID(targetRPM);
            launcher.setFlywheelVelocityPID();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}