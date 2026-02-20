package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;


@TeleOp(name = "Universal TeleOP", group = ".")
public class UniversalTeleOp extends OpMode{

    RobotHardware bot;
    DriveSubsystem drivetrain;

    private ElapsedTime opmodeTimer = new ElapsedTime();

    private double SPEED_MULTIPLIER = 1.00;

    @Override
    public void init(){
        bot = new RobotHardware(hardwareMap, opmodeTimer);
    }

    @Override
    public void start() {
        opmodeTimer.reset();
    }

    @Override
    public void loop(){
        // Directional Movements
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double[] powers = drivetrain.calculateMotorPowers(y, x, rx);
        drivetrain.setDriveMotorPowers(bot.driveMotors, powers, SPEED_MULTIPLIER);

    }
}
