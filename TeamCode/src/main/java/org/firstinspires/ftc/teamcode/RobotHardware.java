package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utils.GoBildaPinpointDriver;

import java.util.Arrays;
import java.util.List;

public class RobotHardware {
    HardwareMap hwMap;

    // Drive
    public DcMotorEx leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive;
    public final  List<DcMotorEx> driveMotors;

    // Aux
    public DcMotorEx Flywheel_L, Flywheel_R, Intake, Transfer;
    public final  List<DcMotorEx> flyWheels;

    public Servo Gate, kick_L, kick_R;

    // Odometry
    public GoBildaPinpointDriver odo;

    public RobotHardware(HardwareMap hwMapX, ElapsedTime runtime) {
        this.hwMap = hwMapX;

        // Drive
        leftFrontDrive = hwMap.get(DcMotorEx.class,"FL");
        leftBackDrive = hwMap.get(DcMotorEx.class,"BL");
        rightFrontDrive = hwMap.get(DcMotorEx.class,"FR");
        rightBackDrive = hwMap.get(DcMotorEx.class,"BR");

        driveMotors = Arrays.asList(leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive);


        // Aux
        Intake = hwMap.get(DcMotorEx.class,"Intake");
        Transfer = hwMap.get(DcMotorEx.class, "Transfer");
        Flywheel_L = hwMap.get(DcMotorEx.class,"Flywheel_L");
        Flywheel_R = hwMap.get(DcMotorEx.class,"Flywheel_R");

        flyWheels = Arrays.asList(Flywheel_L, Flywheel_R);


        Gate = hwMap.get(Servo.class, "Gate");
        kick_L = hwMap.get(Servo.class, "kick_L");
        kick_R = hwMap.get(Servo.class, "kick_R");

        // Motor Directions
        leftFrontDrive.setDirection(DcMotorEx.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotorEx.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotorEx.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotorEx.Direction.FORWARD);

        Flywheel_L.setDirection(DcMotorSimple.Direction.FORWARD);
        Flywheel_L.setDirection(DcMotorSimple.Direction.REVERSE);

        // Odometry
        // Odometry Computer
        odo = hwMap.get(GoBildaPinpointDriver.class, "POC");
        odo.setOffsets(0, 0, DistanceUnit.MM); // Not Calibrated
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD); // Direction
        odo.resetPosAndIMU();
    }

}
