package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.tuning.roboConstants.TransferConstants.fullPowerTransfer;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.TransferConstants.gateClosed;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.TransferConstants.gateOpen;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.TransferConstants.halfPowerTransfer;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.TransferConstants.offPowerTransfer;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

public class TransferSubsystem {

    /** Enum to represent the gate state */
    public enum GateState {
        OPEN,
        CLOSED,
        ACTION // Servo moving or in-between positions
    }

    private DcMotorEx motor;
    private Servo servo;

    public TransferSubsystem (DcMotorEx motorX, Servo servoX) {
        this.motor = motorX;
        this.servo = servoX;
    }

    // Servo positions for open and closed gate
    private static double GATE_OPEN_POSITION = gateOpen;
    private static double GATE_CLOSED_POSITION = gateClosed;

    // Tolerance to detect if servo is moving
    private static final double POSITION_TOLERANCE = 0.02;

    // ======== Motor Power Control ========

    /**
     * Get Transfer's current power
     *
     * @return Current motor power (0.0 to 1.0)
     */
    public double getTransferPower() {return motor.getPower();}

    /**
     * Set transfer motor power level.
     * 0 = Off, 1 = 60%, 2 = Full Power
     *
     * @param lvl Power level (0, 1, or 2)
     */
    public void setTransferLevel(int lvl) {
        switch (lvl) {
            case 0:
                motor.setPower(offPowerTransfer);
                break;
            case 1:
                motor.setPower(halfPowerTransfer);
                break;
            case 2:
                motor.setPower(fullPowerTransfer);
                break;
        }
    }


    // ======== Gate Control ========

    /**
     * Set the gate to OPEN or CLOSED.
     * ACTION is determined automatically and should not be commanded.
     *
     * @param state Desired gate state (OPEN or CLOSED)
     */
    public void setGateState(@NonNull GateState state) {
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
     * @return GateState: OPEN, CLOSED, or ACTION (moving/in-between)
     */
    public GateState getGateState() {
        double position = servo.getPosition();

        if (Math.abs(position - GATE_OPEN_POSITION) <= POSITION_TOLERANCE) {
            return GateState.OPEN;
        } else if (Math.abs(position - GATE_CLOSED_POSITION) <= POSITION_TOLERANCE) {
            return GateState.CLOSED;
        } else {
            return GateState.ACTION;
        }
    }

    public String gatestateToString(GateState state) {
        switch (state) {
            default:
                return "NULL";
            case OPEN:
                return "OPEN";
            case CLOSED:
                return "Closed";
            case ACTION:
                // ACTION should not be commanded directly
                return "ACTION";
        }
    }
}
