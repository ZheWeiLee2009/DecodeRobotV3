package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.List;


public class LauncherSubsystem  extends SubsystemBase {


    public static final double kP = 0.0048;
    public static final double kI = 0.0;
    public static final double kD = 0.000002;
    public static final double kF = 1.0 / 5400.0;

    private List<DcMotorEx> flywheelMotors;
    private PIDFController controller = new PIDFController(kP, kI, kD, kF);

    private static volatile double target = 0;

    public enum speeds {
        userHalf,
        userCenter,
        userFar,
        autoHalf,
        autoCenter,
        autoFar,
        off
    }


    public LauncherSubsystem (List<DcMotorEx> flywheelMotorsX){
        this.flywheelMotors = flywheelMotorsX;
    }

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
     * @return "Motor1: 4500 RPM, Motor2: 4475 RPM"
     */
    public String getFlywheelsVelocityString() {
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
     * Returns the average velocity between of the Flywheel Motors
     *
     * @return double - motor velocities (RPM)
     */
    public double getFlywheelsResultantVelocity() {
        if (flywheelMotors.isEmpty()) {return 0;}

        double sum = flywheelMotors.get(0).getVelocity() + flywheelMotors.get(1).getVelocity();
        return ticksPerSecondToRPM((sum / 2));
    }

    /**
     * Sets flywheel velocity using RPM (output shaft RPM)
     *
     * @param rpm Desired flywheel RPM (not TPS)
     */
//    @Deprecated
    public void setFlywheelsVelocity(double rpm) {
        double ticksPerSecond = rpmToTicksPerSecond(rpm);

        for (DcMotorEx motor : flywheelMotors) {
            motor.setVelocity(ticksPerSecond);
        }
    }

    public void setFlywheelVelocityPID() {
        controller.setPIDF(kP, kI, kD, kF);

        double currentRPM = getFlywheelsResultantVelocity();
        double output = controller.calculate(currentRPM, target);

        // Clamp output
        output = Math.max(-1.0, Math.min(1.0, output));

        setFlywheelsPower(output);
    }

    public void setFlywheelRPMPID(double rpm) { target = rpm; }

    public double getPIDErr() {
        double error = Math.abs(getFlywheelsResultantVelocity() - target) / target;
        return error * 100;
    }


    /**
     * Sets flywheel Power
     *
     * @param power Desired Power of motors
     */
    public void setFlywheelsPower(double power) {
        for (DcMotorEx motor : flywheelMotors) {
            motor.setPower(power);
        }
    }

    public double getFlywheelLRPM() {return ticksPerSecondToRPM(Math.abs(flywheelMotors.get(0).getVelocity()));}
    public double getFlywheelRRPM() {return ticksPerSecondToRPM(Math.abs(flywheelMotors.get(1).getVelocity()));}

    public double getFlywheelLPower() {return flywheelMotors.get(0).getPower();}
    public double getFlywheelRPower() {return flywheelMotors.get(1).getPower();}
}
