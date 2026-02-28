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
    DriveSubsystem drivetrain = new DriveSubsystem();
    LauncherSubsystem launcher = new LauncherSubsystem();
    TransferSubsystem transfer = new TransferSubsystem();
    IntakeSubsystem intake = new IntakeSubsystem();

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
    }

    @Override
    public void start() {
        opmodeTimer.reset();
    }

    @Override
    public void loop(){

        // ENABLE BULK READS
        // This is the #1 way to lower loop times.
        // It reads all sensors on a hub in one go instead of separate slow I2C calls.
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }


        // Directional Movements
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double[] powers = drivetrain.calculateMotorPowers(y, x, rx);
        drivetrain.setDriveMotorPowers(bot.driveMotors, powers, DriveSpeed);

        if (gamepad1.dpad_right){
            launcher.setFlywheelsVelocity(bot.flyWheels, userHalfShootingVelocity);
        } else if (gamepad1.dpad_down) {
            launcher.setFlywheelsVelocity(bot.flyWheels, userCenterShootingVelocity);
        }  else if (gamepad1.dpad_left) {
            launcher.setFlywheelsVelocity(bot.flyWheels, userFarShootingVelocity);
        }  else if (gamepad1.dpad_up) {
            launcher.setFlywheelsVelocity(bot.flyWheels, 0);
        }

        if (gamepad1.triangle){
            intake.setIntakeLevel(bot.Intake, 0);
        } else if (gamepad1.cross) {
            intake.setIntakeLevel(bot.Intake, 1);
        }  else if (gamepad1.square) {
            intake.setIntakeLevel(bot.Intake, 2);
        }  else if (gamepad1.circle ) {
            intake.setIntakeLevel(bot.Intake, -1);
        }

        if (gamepad1.leftBumperWasPressed()) {
            transfer.setTransferLevel(bot.Transfer, 2);
        } else if (gamepad1.left_stick_button) {
            transfer.setTransferLevel(bot.Transfer, 0);
        }

        if (gamepad1.right_trigger > .5) {
            transfer.setGateState(bot.Gate, TransferSubsystem.GateState.OPEN);
//            bot.Gate.setPosition(.2);
        } else {
            transfer.setGateState(bot.Gate, TransferSubsystem.GateState.CLOSED);
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
        telemetry.addLine(launcher.getFlywheelsVelocity(bot.flyWheels));
        telemetry.addLine(transfer.gatestateToString(transfer.getGateState(bot.Gate)));
        telemetry.addData("transfer motor power", transfer.getTransferPower(bot.Transfer));
        telemetry.addData("intake motor power", intake.getIntakePower(bot.Intake));

        telemetry.addData("\nLTrigger", (gamepad1.left_trigger > .5));
        telemetry.addData("rTrigger", (gamepad1.right_trigger > .5));

        telemetry.addData("\ny", y);
        telemetry.addData("x", x);
        telemetry.addData("rx", rx);

        telemetry.update();
    }
}
