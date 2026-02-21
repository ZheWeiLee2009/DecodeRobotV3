package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.List;


public class LauncherSubsystem {

    private static final double ENCODER_CPR = 28.0;     // goBILDA internal encoder
    private static final double GEAR_RATIO = 1.0;       // motor to output shaft (1:1 for 6000 RPM motor)
    private static final double EXTERNAL_RATIO = 1.0;   // belts/gears
    private static final double SECONDS_PER_MINUTE = 60.0;

    /**
     * Converts RPM (output shaft) to motor ticks per second (TPS).
     * @param rpm Revolution per minute as input
     * @return Ticks per second
     */
    private double rpmToTicksPerSecond(double rpm) {
        double motorRPM = rpm * GEAR_RATIO * EXTERNAL_RATIO;
        return (motorRPM * ENCODER_CPR) / SECONDS_PER_MINUTE;
    }

    /**
     * Converts motor ticks per second (TPS) to output shaft RPM.
     * @param ticksPerSecond Ticks per second
     * @return Revolution per minute
     */
    private double ticksPerSecondToRPM(double ticksPerSecond) {
        double motorRPM = (ticksPerSecond / ENCODER_CPR) * SECONDS_PER_MINUTE;
        return motorRPM / (GEAR_RATIO * EXTERNAL_RATIO);
    }

    /**
     * Returns formatted flywheel RPM string (output shaft RPM).
     *
     * @param flywheelMotors List of flywheel motors
     * @return "Motor1: 4500 RPM, Motor2: 4475 RPM"
     */
    public String getFlywheelsVelocity(@NonNull List<DcMotorEx> flywheelMotors) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < flywheelMotors.size(); i++) {
            DcMotorEx motor = flywheelMotors.get(i);

            double tps = motor.getVelocity();
            double rpm = ticksPerSecondToRPM(tps);

            result.append("Motor")
                    .append(i + 1)
                    .append(": ")
                    .append(String.format("%.2f RPM", rpm));

            if (i < flywheelMotors.size() - 1) {
                result.append(", ");
            }
        }

        return result.toString();
    }

    /**
     * Sets flywheel velocity using RPM (output shaft RPM)
     *
     * @param flywheelMotors List of flywheel motors
     * @param rpm Desired flywheel RPM (not TPS)
     */
    public void setFlywheelsVelocity(@NonNull List<DcMotorEx> flywheelMotors, double rpm) {
        double ticksPerSecond = rpmToTicksPerSecond(rpm);

        for (DcMotorEx motor : flywheelMotors) {
            motor.setVelocity(ticksPerSecond);
        }
    }
}
