package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.RobotHardware;

import java.util.List;

public class DriveSubsystem {
   RobotHardware bot;

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

    public void setMotorPowers(double v, double v1, double v2, double v3, double speedMultiplier) {
        bot.leftFrontDrive.setPower(v * speedMultiplier);
        bot.leftBackDrive.setPower(v1 * speedMultiplier);
        bot.rightFrontDrive.setPower(v2 * speedMultiplier);
        bot.rightBackDrive.setPower(v3 * speedMultiplier);
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
