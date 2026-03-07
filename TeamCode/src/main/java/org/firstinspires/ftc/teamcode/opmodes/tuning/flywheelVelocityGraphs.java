package org.firstinspires.ftc.teamcode.opmodes.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LauncherSubsystem;

@TeleOp(name = "Flywheel Velocity Graphs", group = "tuning")
public class flywheelVelocityGraphs extends LinearOpMode {

    private ElapsedTime opmodeTimer = new ElapsedTime();

    RobotHardware bot;
    LauncherSubsystem launcher;
    DriveSubsystem drivetrain;

    double shootingVal = 0000;

    private volatile boolean autoRunning = true;

    @Override
    public void runOpMode() {
        bot = new RobotHardware(hardwareMap, opmodeTimer);
        launcher = new LauncherSubsystem(bot.flyWheels);
        drivetrain = new DriveSubsystem(bot.driveMotors);


        drivetrain.setMotorsMode(bot.flyWheels, DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addLine("This is going to turn on ONLY the flywheel's PID thread to change its velocity over time");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        telemetry.addLine("2800");
        telemetry.update();
        sleep(1000);
        shootingVal = 2800;
        launcher.setFlywheelsVelocity(shootingVal);
        sleep(5000);

        telemetry.addLine("3200");
        telemetry.update();
        sleep(1000);
        shootingVal = 3200;
        launcher.setFlywheelsVelocity(shootingVal);
        sleep(5000);

        telemetry.addLine("3800");
        telemetry.update();
        sleep(1000);
        shootingVal = 3800;
        launcher.setFlywheelsVelocity(shootingVal);
        sleep(5000);

        telemetry.addLine("2800");
        telemetry.update();
        sleep(1000);
        shootingVal = 3200;
        launcher.setFlywheelsVelocity(shootingVal);
        sleep(5000);

        shootingVal = 0;
        launcher.setFlywheelsVelocity(shootingVal);
        telemetry.addLine("STOP");
        sleep(5000);

    }
}