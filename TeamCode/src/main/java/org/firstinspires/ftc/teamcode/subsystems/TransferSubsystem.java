package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class TransferSubsystem {

    /**
     * Get Transfer's current power
     *
     * @param motor Specific motor getting measured
     * @return Power of motor
     */
    public double getTransferPower(@NonNull DcMotorEx motor) {return motor.getPower();}

    /**
     * Set power of the Transfer
     *
     * @param motor DcMotorEx, to add intake motor in here.
     * @param lvl Low to High, 0 => 2. 0 being off, 2 being maxPower
     */
    public void setTransferLevel(@NonNull DcMotorEx motor, int lvl) {
        switch (lvl) {
            case 0:
                motor.setPower(0.0);
                break;
            case 1:
                motor.setPower(0.6);
                break;
            case 2:
                motor.setPower(1.0);
        }
    }
}
