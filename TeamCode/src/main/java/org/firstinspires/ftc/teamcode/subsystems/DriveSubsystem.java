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

   public double[] calculateMotorPowers(double axial, double lateral, double yaw) {
        double[] motorPowers = new double[4];
        motorPowers[0] = (axial + lateral + yaw);
        motorPowers[1] = (axial - lateral + yaw);
        motorPowers[2] = (axial - lateral - yaw);
        motorPowers[3] = (axial + lateral - yaw);
        return motorPowers;
   }

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

    public void setDriveMotorPowers(@NonNull List<DcMotorEx> driveMotors, double[] velocity, double speedMultiplier) {
        driveMotors.get(0).setPower(velocity[0] * speedMultiplier * FL_Offset); // Front left
        driveMotors.get(1).setPower(velocity[1] * speedMultiplier * BL_Offset); // Back left
        driveMotors.get(2).setPower(velocity[2] * speedMultiplier * FR_Offset); // Front Right
        driveMotors.get(3).setPower(velocity[3] * speedMultiplier * BR_Offset); // Back Right
    }

    public void setMotorsMode(@NonNull DcMotorEx motor, DcMotorEx.RunMode mode) {
        motor.setMode(mode);
    }

    public void setMotorMode(@NonNull List<DcMotorEx> type, DcMotorEx.RunMode mode) {
        for (DcMotorEx motor : type) {
            motor.setMode(mode);
        }
    }

    public void setZeroPowerBehavior(@NonNull DcMotorEx motor, DcMotor.ZeroPowerBehavior zpb) {
        motor.setZeroPowerBehavior(zpb);
    }

    public void setZeroPowerBehavior(@NonNull List<DcMotorEx> type, DcMotor.ZeroPowerBehavior zpb) {
        for (DcMotorEx motor : type) {
            motor.setZeroPowerBehavior(zpb);
        }
    }
}
