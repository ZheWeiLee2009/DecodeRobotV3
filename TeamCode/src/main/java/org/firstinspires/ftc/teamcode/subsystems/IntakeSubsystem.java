package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.tuning.roboConstants.IntakeConstants.fullPowerIntake;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.IntakeConstants.halfPowerIntake;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class IntakeSubsystem {

    private DcMotorEx motor;

    public IntakeSubsystem (DcMotorEx motorX) { this.motor = motorX; }
    /**
     * Get intake's current power
     *
     * @return Power of motor
     */
    public double getIntakePower() {return motor.getPower();}

    /**
     * Set power of the intake
     *
     * @param lvl Low to High, -1 => 2. 0 being off, 2 being maxPower, -1 is inverse
     */
    public void setIntakeLevel(int lvl) {
        switch (lvl) {
            case -1:
                motor.setPower(-1.0);
            case 0:
                motor.setPower(0.0);
                break;
            case 1:
                motor.setPower(halfPowerIntake);
                break;
            case 2:
                motor.setPower(fullPowerIntake);
                break;
        }
    }
}
