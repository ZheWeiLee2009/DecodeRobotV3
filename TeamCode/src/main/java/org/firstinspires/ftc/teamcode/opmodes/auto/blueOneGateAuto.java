package org.firstinspires.ftc.teamcode.opmodes.auto;

import static org.firstinspires.ftc.teamcode.tuning.roboConstants.LauncherConstants.autoHalfShootingVelocity;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.TransferConstants.allReleaseTimer;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.commands.setFlywheelPIDOn;
import org.firstinspires.ftc.teamcode.opmodes.auto.Paths.blueOneGateAutoPaths;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;
import org.firstinspires.ftc.teamcode.tuning.pedroPathing.Constants;

@Autonomous(name = "blueCloseOneGate", group = ".")
public class blueOneGateAuto extends LinearOpMode {

    private Follower follower;

    RobotHardware bot;
    DriveSubsystem drivetrain;
    LauncherSubsystem launcher;
    TransferSubsystem transfer;
    IntakeSubsystem intake;


    private double shootingVal = autoHalfShootingVelocity;

    private Thread launcherThread;
    private volatile boolean autoRunning = true;

    @Override
    public void runOpMode(){
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


        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(24. , 128, Math.toRadians(144)));

        blueOneGateAutoPaths paths = new blueOneGateAutoPaths(follower);

        // Commands
        setFlywheelPIDOn launcherRun = new setFlywheelPIDOn(launcher, shootingVal);

//        drivetrain.enableBuckReads();

        telemetry.addData("X", 26);
        telemetry.addData("Y", 127);

        telemetry.addLine("Blue Auto Ready!");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        // START

//        launcher.setFlywheelsVelocity(shootingVal);
        startPID();

        try {
            idleAll();

            // P1
//            sleep(1000); // waiting for flywheels PID
            follow(paths.preload);
            releaseALl();

            // C1
            idleAll();
            intakeAll();
            follow(paths.line1);
            idleAll();
            follow(paths.gateOpen1);
            follow(paths.shoot1);
            releaseALl();

            // C2
            idleAll();
            follow(paths.align2);
            intakeAll();
            follow(paths.line2);
            idleAll();
            follow(paths.shoot2);
            releaseALl();

            // C3
            idleAll();
            follow(paths.align3);
            intakeAll();
            follow(paths.line3);
            idleAll();
            follow(paths.shoot3);
            releaseALl();

            offAll();
            follow(paths.leave);

            startPID();

        } finally {
            stopPID();
        }

    }

    private void offAll() {
        transfer.setGateState(TransferSubsystem.GateState.CLOSED);
        intake.setIntakeLevel(0);
        transfer.setTransferLevel(0);

    }


    private void idleAll() {
        transfer.setGateState(TransferSubsystem.GateState.CLOSED);
        intake.setIntakeLevel(1);
        transfer.setTransferLevel(1);
    }

    public void intakeAll() {
        transfer.setGateState(TransferSubsystem.GateState.CLOSED);
        intake.setIntakeLevel( 2);
        transfer.setTransferLevel(2);
    }

    private void releaseALl (){
//        sleep(200);
//        launcher.setFlywheelsVelocity(shootingVal);
        intake.setIntakeLevel(2);
        transfer.setTransferLevel(2);
        sleep(50);
        transfer.setGateState(TransferSubsystem.GateState.OPEN);
        sleep(allReleaseTimer);
    }

    private void follow(com.pedropathing.paths.PathChain path) {
        follower.followPath(path);

        while (opModeIsActive() && follower.isBusy()) {
            follower.update();

            telemetry.addData("Following", path);
            telemetry.addData("X", follower.getPose().getX());
            telemetry.addData("Y", follower.getPose().getY());
            telemetry.addData("Heading", Math.toDegrees(follower.getPose().getHeading()));

            telemetry.addData("\nFL: ", bot.leftFrontDrive.getPower());
            telemetry.addData("BL: ", bot.leftBackDrive.getPower());
            telemetry.addData("FR: ", bot.rightFrontDrive.getPower());
            telemetry.addData("BR: ", bot.rightBackDrive.getPower());

            telemetry.update();
        }

        follower.breakFollowing();
        sleep(50);
    }

    private void follow(com.pedropathing.paths.PathChain path, double power) {
        follower.followPath(path, power, true);

        while (opModeIsActive() && follower.isBusy()) {
            follower.update();

            telemetry.addData("Following", path);
            telemetry.addData("X", follower.getPose().getX());
            telemetry.addData("Y", follower.getPose().getY());
            telemetry.addData("Heading", Math.toDegrees(follower.getPose().getHeading()));

            telemetry.addData("\nFL: ", bot.leftFrontDrive.getPower());
            telemetry.addData("BL: ", bot.leftBackDrive.getPower());
            telemetry.addData("FR: ", bot.rightFrontDrive.getPower());
            telemetry.addData("BR: ", bot.rightBackDrive.getPower());

            telemetry.addData("PID is running:", autoRunning);

            telemetry.update();
        }

        follower.breakFollowing();
        sleep(50);
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

}