package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class TransferSubsystem {

    /** Enum to represent the gate state */
    public enum GateState {
        OPEN,
        CLOSED,
        ACTION // Servo moving or in-between positions
    }

    // Servo positions for open and closed gate
    private static final double GATE_OPEN_POSITION = 0.2;
    private static final double GATE_CLOSED_POSITION = 0.8;

    // Tolerance to detect if servo is moving
    private static final double POSITION_TOLERANCE = 0.02;

    // ======== Motor Power Control ========

    /**
     * Get Transfer's current power
     *
     * @param motor Specific transfer motor
     * @return Current motor power (0.0 to 1.0)
     */
    public double getTransferPower(@NonNull DcMotorEx motor) {return motor.getPower();}

    /**
     * Set transfer motor power level.
     * 0 = Off, 1 = 60%, 2 = Full Power
     *
     * @param motor DcMotorEx controlling transfer
     * @param lvl Power level (0, 1, or 2)
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
                break;
        }
    }


    // ======== Gate Control ========

    /**
     * Set the gate to OPEN or CLOSED.
     * ACTION is determined automatically and should not be commanded.
     *
     * @param servo The gate servo
     * @param state Desired gate state (OPEN or CLOSED)
     */
    public void setGateState(@NonNull Servo servo, @NonNull GateState state) {
        switch (state) {
            case OPEN:
                servo.setPosition(GATE_OPEN_POSITION);
                break;
            case CLOSED:
                servo.setPosition(GATE_CLOSED_POSITION);
                break;
            case ACTION:
                // ACTION should not be commanded directly
                break;
        }
    }

    /**
     * Gets the current state of the gate based on servo position.
     *
     * @param servo The gate servo
     * @return GateState: OPEN, CLOSED, or ACTION (moving/in-between)
     */
    public GateState getGateState(@NonNull Servo servo) {
        double position = servo.getPosition();

        if (Math.abs(position - GATE_OPEN_POSITION) <= POSITION_TOLERANCE) {
            return GateState.OPEN;
        } else if (Math.abs(position - GATE_CLOSED_POSITION) <= POSITION_TOLERANCE) {
            return GateState.CLOSED;
        } else {
            return GateState.ACTION;
        }
    }
}
