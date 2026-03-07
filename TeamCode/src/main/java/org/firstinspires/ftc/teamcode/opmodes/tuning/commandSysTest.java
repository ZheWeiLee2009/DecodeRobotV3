package org.firstinspires.ftc.teamcode.opmodes.tuning;

import static org.firstinspires.ftc.teamcode.tuning.roboConstants.LauncherConstants.autoHalfShootingVelocity;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.commands.setFlywheelPIDOn;
import org.firstinspires.ftc.teamcode.opmodes.auto.Paths.blueTwoGateAutoPaths;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;
import org.firstinspires.ftc.teamcode.tuning.pedroPathing.Constants;

@Disabled
@TeleOp(name = "CommandSysTest", group = "tuning")
public class commandSysTest extends LinearOpMode {

    private Follower follower;

    RobotHardware bot;
    DriveSubsystem drivetrain;
    LauncherSubsystem launcher;
    TransferSubsystem transfer;
    IntakeSubsystem intake;

    private double shootingVal = autoHalfShootingVelocity;

    private ElapsedTime a = new ElapsedTime();

    private Thread launcherThread;
    private volatile boolean autoRunning = true;


    @Override
    public void runOpMode() {
        bot = new RobotHardware(hardwareMap, new ElapsedTime());

        drivetrain = new DriveSubsystem(bot.driveMotors);
        launcher = new LauncherSubsystem(bot.flyWheels);
        transfer = new TransferSubsystem(bot.Transfer, bot.Gate);
        intake = new IntakeSubsystem(bot.Intake);

        drivetrain.setMotorsMode(bot.driveMotors, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drivetrain.setMotorsMode(bot.Intake, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drivetrain.setMotorsMode(bot.Transfer, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drivetrain.setMotorsMode(bot.flyWheels, DcMotor.RunMode.RUN_USING_ENCODER);

        drivetrain.setZeroPowerBehavior(bot.driveMotors, DcMotor.ZeroPowerBehavior.BRAKE);
        drivetrain.setZeroPowerBehavior(bot.Intake, DcMotor.ZeroPowerBehavior.BRAKE);
        drivetrain.setZeroPowerBehavior(bot.Transfer, DcMotor.ZeroPowerBehavior.FLOAT);

        transfer.setGateState(TransferSubsystem.GateState.CLOSED);
        transfer.setTransferLevel(0);
        intake.setIntakeLevel(0);
        launcher.setFlywheelsVelocity(0);

        // Commands
//        setFlywheelPIDOn launcherRun = new setFlywheelPIDOn(launcher, shootingVal);

        a.reset();

        waitForStart();
        if (isStopRequested()) return;

//        while (opModeIsActive() && !isStopRequested()) {
//            launcher.setFlywheelRPMPID(shootingVal);
//            // START
//            launcherRun.schedule();
////         launcher.setFlywheelsVelocity(shootingVal);
////            CommandScheduler.getInstance().run();
//            sleep(30000);
//        }

        startPID();

        try {
            testCase();
            sleep(25000);
        } finally {
            stopPID();
        }

    }

    private void startPID() {
        autoRunning = true;
        launcher.setFlywheelRPMPID(shootingVal);
        launcherThread = new Thread(() -> {
            while (autoRunning && !Thread.currentThread().isInterrupted()) {
                // Execute PID logic
                launcher.setFlywheelVelocityPID();

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

    private void testCase(){
        while (opModeIsActive() && !isStopRequested()) {
            if (a.time() <= 20000) {
                telemetry.addData("timer", a.time());
                telemetry.update();
                sleep(100);
            }   else {
                break;
            }
        }
    }
}
