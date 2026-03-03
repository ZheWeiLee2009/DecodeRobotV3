package org.firstinspires.ftc.teamcode.opmodes.teleop;

import static org.firstinspires.ftc.teamcode.tuning.roboConstants.DriveConstants.DriveSpeed;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.LauncherConstants.userCenterShootingVelocity;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.LauncherConstants.userFarShootingVelocity;
import static org.firstinspires.ftc.teamcode.tuning.roboConstants.LauncherConstants.userHalfShootingVelocity;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TransferSubsystem;

import java.util.List;


@TeleOp(name = "Universal TeleOP", group = ".")
public class UniversalTeleOp extends OpMode{

    RobotHardware bot;
    DriveSubsystem drivetrain = new DriveSubsystem(bot.driveMotors);
    LauncherSubsystem launcher = new LauncherSubsystem(bot.flyWheels);
    TransferSubsystem transfer = new TransferSubsystem(bot.Transfer, bot.Gate);
    IntakeSubsystem intake = new IntakeSubsystem(bot.Intake);

    private ElapsedTime opmodeTimer = new ElapsedTime();

    private long lastTime = 0;

    @Override
    public void init(){
        bot = new RobotHardware(hardwareMap, opmodeTimer);

        drivetrain.setMotorsMode(bot.driveMotors, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drivetrain.setMotorsMode(bot.Intake, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drivetrain.setMotorsMode(bot.Transfer, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drivetrain.setMotorsMode(bot.flyWheels, DcMotor.RunMode.RUN_USING_ENCODER);

        drivetrain.setZeroPowerBehavior(bot.driveMotors, DcMotor.ZeroPowerBehavior.BRAKE);
        drivetrain.setZeroPowerBehavior(bot.Intake, DcMotor.ZeroPowerBehavior.BRAKE);
        drivetrain.setZeroPowerBehavior(bot.Transfer, DcMotor.ZeroPowerBehavior.FLOAT);

        drivetrain.enableBuckReads(hardwareMap);
    }

    @Override
    public void start() {
        opmodeTimer.reset();
        launcher.setFlywheelsVelocity(userHalfShootingVelocity);
        intake.setIntakeLevel( 1);
        transfer.setTransferLevel(1);
        transfer.setGateState(TransferSubsystem.GateState.CLOSED);
    }

    @Override
    public void loop(){


        // Directional Movements
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x * .8; // Added multiplier for better contorl

        double[] powers = drivetrain.calculateMotorPowers(y, x, rx);
        drivetrain.setDriveMotorPowers(powers, DriveSpeed);

        if (gamepad1.dpad_right){
            launcher.setFlywheelsVelocity(userHalfShootingVelocity);
        } else if (gamepad1.dpad_down) {
            launcher.setFlywheelsVelocity(userCenterShootingVelocity);
        }  else if (gamepad1.dpad_left) {
            launcher.setFlywheelsVelocity(userFarShootingVelocity);
        }  else if (gamepad1.dpad_up) {
            launcher.setFlywheelsVelocity(0);
        }

        if (gamepad1.triangle){
            intake.setIntakeLevel( 0);
        } else if (gamepad1.cross) {
            intake.setIntakeLevel( 1);
        }  else if (gamepad1.square) {
            intake.setIntakeLevel( 2);
        }  else if (gamepad1.circle ) {
            intake.setIntakeLevel( -1);
        }

        // PREP
        if (gamepad1.left_bumper) {
            intake.setIntakeLevel(2);
            transfer.setTransferLevel(2);
        } else {
            intake.setIntakeLevel(1);
            transfer.setTransferLevel(0);
        }

        // LAUNCH
        if (gamepad1.right_trigger > .5) {
            transfer.setGateState(TransferSubsystem.GateState.OPEN);
//            bot.Gate.setPosition(.2);
        } else {
            transfer.setGateState(TransferSubsystem.GateState.CLOSED);
//            bot.Gate.setPosition(.5);
        }

        // Looptime Calc
        long currentTime = System.currentTimeMillis();
        if (lastTime != 0) {
            long loopTime = currentTime - lastTime;
            telemetry.addData("Loop Time (ms)", loopTime);
            telemetry.addData("Frequency (Hz)", 1000.0 / loopTime);
        }
        lastTime = currentTime;

        telemetry.addLine(drivetrain.calculatedMotorPowersToString(powers));
        telemetry.addLine(launcher.getFlywheelsVelocity());
        telemetry.addLine(transfer.gatestateToString(transfer.getGateState()));
        telemetry.addData("transfer motor power", transfer.getTransferPower());
        telemetry.addData("intake motor power", intake.getIntakePower());

        telemetry.addData("\ny", y);
        telemetry.addData("x", x);
        telemetry.addData("rx", rx);

        telemetry.update();
    }
}
