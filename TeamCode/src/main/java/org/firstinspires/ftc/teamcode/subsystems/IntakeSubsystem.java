package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class IntakeSubsystem {

    /**
     * Get intake's current power
     *
     * @param motor Specific motor getting measured
     * @return Power of motor
     */
    public double getIntakePower(@NonNull DcMotorEx motor) {return motor.getPower();}

    /**
     * Set power of the intake
     *
     * @param motor DcMotorEx, to add intake motor in here.
     * @param lvl Low to High, -1 => 2. 0 being off, 2 being maxPower, -1 is inverse
     */
    public void setIntakeLevel(@NonNull DcMotorEx motor, int lvl) {
        switch (lvl) {
            case -1:
                motor.setPower(-1.0);
            case 0:
                motor.setPower(0.0);
                break;
            case 1:
                motor.setPower(0.6);
                break;
            case 2:
                motor.setPower(1.0);
                break;
        }
    }
}
