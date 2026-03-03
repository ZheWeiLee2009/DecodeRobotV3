package org.firstinspires.ftc.teamcode.opmodes.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDFController;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.bylazar.telemetry.PanelsTelemetry;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;

//@Disabled
@Config
@TeleOp(name = "Flywheel PID tuning", group = "tuning")
public class flywheelPID extends OpMode {

    private RobotHardware bot;
    private LauncherSubsystem launcher;
    private DriveSubsystem drivetrain;

    private PIDFController controller;

    public static double kP = 0.0058;
    public static double kI = 0.0;
    public static double kD = 0.000002;
    public static double kF = 1.0 / 5400.0;   // CHANGE after measuring

    public static double targetRPM = 0;

    @IgnoreConfigurable
    private static TelemetryManager telemetryM;

    @Override
    public void init() {

        bot = new RobotHardware(hardwareMap, new ElapsedTime());
        launcher = new LauncherSubsystem(bot.flyWheels);
        drivetrain = new DriveSubsystem(bot.driveMotors);


        drivetrain.setMotorsMode(bot.flyWheels, DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        controller = new PIDFController(kP, kI, kD, kF);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
    }

    @Override
    public void loop() {

        controller.setPIDF(kP, kI, kD, kF);

        double currentRPM = launcher.getFlywheelsResultantVelocity();

        // This now returns MOTOR POWER because we scaled gains correctly
        double output = controller.calculate(currentRPM, targetRPM);

        // Clamp output
        output = Math.max(-1.0, Math.min(1.0, output));

        launcher.setFlywheelsPower(output);

        telemetry.addData("Target RPM", targetRPM);
        telemetry.addData("Current RPM", currentRPM);
        telemetry.addData("ERR", ((targetRPM - currentRPM)/targetRPM)*100);
        telemetry.addData("Power Output", output);
        telemetry.update();
    }
}
