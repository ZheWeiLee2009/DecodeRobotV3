package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.tuning.roboConstants.DriveConstants.BL_Offset;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.DriveConstants.BR_Offset;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.DriveConstants.FL_Offset;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.DriveConstants.FR_Offset;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


import java.util.List;

public class DriveSubsystem {

    /**
     * Calculates robot-centric mecanum motor powers.
     *
     * @param axial   Forward/backward input (+forward, -backward)
     * @param lateral Left/right strafe input (+right, -left)
     * @param yaw     Rotation input (+clockwise, -counterclockwise)
     * @return Array of motor powers in order:
     *         [0] Front Left, [1] Back Left, [2] Front Right, [3] Back Right
     */
   public double[] calculateMotorPowers(double axial, double lateral, double yaw) {
        double[] motorPowers = new double[4];
        motorPowers[0] = (axial + lateral + yaw);
        motorPowers[1] = (axial - lateral + yaw);
        motorPowers[2] = (axial - lateral - yaw);
        motorPowers[3] = (axial + lateral - yaw);
        return motorPowers;
   }

    /**
     * Calculates field-oriented mecanum motor powers.
     * Converts field-centric joystick inputs into robot-centric motion
     * using the robot's current heading.
     *
     * @param axial   Forward/backward field input (+forward, -backward)
     * @param lateral Left/right field input (+right, -left)
     * @param yaw     Rotation input (+clockwise, -counterclockwise)
     * @param heading Robot current heading in RADIANS (from IMU)
     * @return Array of motor powers in order:
     *         [0] Front Left, [1] Back Left, [2] Front Right, [3] Back Right
     */
   public double[] calculateFODMotorPowers(double axial, double lateral, double yaw, double heading) {

        double[] motorPowers = new double[4];
        // rotation matrix
        double rotX = lateral * Math.cos(-heading) - axial * Math.sin(-heading);
        double rotY = lateral * Math.sin(-heading) + axial * Math.cos(-heading);

        motorPowers[0] = (rotY + rotX + yaw);
        motorPowers[1] = (rotY - rotX + yaw);
        motorPowers[2] = (rotY - rotX - yaw);
        motorPowers[3] = (rotY + rotX - yaw);

        return motorPowers;
   }

    /**
     * Sets power to all drive motors using precomputed motor velocities.
     *
     * Expected motor order in list:
     * [0] Front Left
     * [1] Back Left
     * [2] Front Right
     * [3] Back Right
     *
     * @param driveMotors     List of 4 drive motors (must not be null)
     * @param velocity        Array of motor powers (length must be 4)
     * @param speedMultiplier Global speed scaling factor (0.0 to 1.0 recommended)
     *                        Useful for slow mode or precision driving
     */
    public void setDriveMotorPowers(@NonNull List<DcMotorEx> driveMotors, @NonNull double[] velocity, double speedMultiplier) {
        driveMotors.get(0).setPower(velocity[0] * speedMultiplier * FL_Offset); // Front left
        driveMotors.get(1).setPower(velocity[1] * speedMultiplier * BL_Offset); // Back left
        driveMotors.get(2).setPower(velocity[2] * speedMultiplier * FR_Offset); // Front Right
        driveMotors.get(3).setPower(velocity[3] * speedMultiplier * BR_Offset); // Back Right
    }

    public String calculatedMotorPowersToString(double[] power) {
        if (power == null || power.length < 4) {
            return "Motor powers invalid";
        }

        return String.format(
                "FL: %.2f | BL: %.2f | FR: %.2f | BR: %.2f",
                power[0], power[1], power[2], power[3]
        );
    }

    /**
     * Sets the run mode of a single motor.
     *
     * @param motor Motor to configure (must not be null)
     * @param mode  Desired RunMode (e.g. RUN_WITHOUT_ENCODER, RUN_USING_ENCODER)
     */
    public void setMotorsMode(@NonNull DcMotorEx motor, @NonNull DcMotorEx.RunMode mode) {
        motor.setMode(mode);
    }

    /**
     * Sets the run mode for a list of motors.
     *
     * @param list List of motors to configure (must not be null)
     * @param mode Desired RunMode for all motors
     */
    public void setMotorsMode(@NonNull List<DcMotorEx> list, @NonNull DcMotorEx.RunMode mode) {
        for (DcMotorEx motor : list) {
            motor.setMode(mode);
        }
    }

    /**
     * Sets the ZeroPowerBehavior for a single motor.
     *
     * @param motor Motor to configure (must not be null)
     * @param zpb   ZeroPowerBehavior (BRAKE or FLOAT)
     */
    public void setZeroPowerBehavior(
            @NonNull DcMotorEx motor,
            @NonNull DcMotor.ZeroPowerBehavior zpb
    ) {
        motor.setZeroPowerBehavior(zpb);
    }

    /**
     * Sets the ZeroPowerBehavior for a list of motors.
     *
     * @param type List of motors (must not be null)
     * @param zpb  ZeroPowerBehavior applied to all motors (BRAKE or FLOAT)
     */
    public void setZeroPowerBehavior(
            @NonNull List<DcMotorEx> type,
            @NonNull DcMotor.ZeroPowerBehavior zpb
    ) {
        for (DcMotorEx motor : type) {
            motor.setZeroPowerBehavior(zpb);
        }
    }
}
