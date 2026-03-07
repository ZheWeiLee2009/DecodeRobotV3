package org.firstinspires.ftc.teamcode.opmodes.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.bylazar.field.Line;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LauncherSubsystem;

@TeleOp(name = "Flywheel PID Graphs", group = "tuning")
public class flywheelPIDGraphs extends LinearOpMode {

    private ElapsedTime opmodeTimer = new ElapsedTime();

    RobotHardware bot;
    LauncherSubsystem launcher;
    DriveSubsystem drivetrain;

    private Thread launcherThread;
    double shootingVal = 0000;

    private volatile boolean autoRunning = true;

    @Override
    public void runOpMode(){
        bot = new RobotHardware(hardwareMap, opmodeTimer);
        launcher = new LauncherSubsystem(bot.flyWheels);
        drivetrain = new DriveSubsystem(bot.driveMotors);


        drivetrain.setMotorsMode(bot.flyWheels, DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addLine("This is going to turn on ONLY the flywheel's PID thread to change its velocity over time");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        startPID();
        try {
            telemetry.addLine("2800");
            telemetry.update();
            sleep(1000);
            shootingVal = 2800;
            sleep(5000);

            telemetry.addLine("3200");
            telemetry.update();
            sleep(1000);
            shootingVal = 3200;
            sleep(5000);

            telemetry.addLine("3800");
            telemetry.update();
            sleep(1000);
            shootingVal = 3800;
            sleep(5000);

            telemetry.addLine("2800");
            telemetry.update();
            sleep(1000);
            shootingVal = 3200;
            sleep(5000);

            shootingVal = 0;
            telemetry.addLine("STOP");
            sleep(5000);
        } finally {
            stopPID();
        }
    }

    private void startPID() {
        autoRunning = true;
        launcherThread = new Thread(() -> {
            while (autoRunning && !Thread.currentThread().isInterrupted()) {
                // Execute PID logic
                launcher.setFlywheelRPMPID(shootingVal);
                launcher.setFlywheelVelocityPID();

                telemetry.addData("Target", shootingVal);
                telemetry.addData("Current", launcher.getFlywheelsResultantVelocity());
                telemetry.update();
                try {
                    Thread.sleep(20); // 50Hz frequency
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    break;
                }
            }
            // Final safety: turn off motors when thread exits
            launcher.setFlywheelsPower(0);
        });
        launcherThread.start();
    }

    private void stopPID() {
        autoRunning = false;
        if (launcherThread != null) {
            launcherThread.interrupt();
        }
    }
}
