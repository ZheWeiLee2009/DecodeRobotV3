package org.firstinspires.ftc.teamcode.tuning.pedroPathing;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Configurable
public class Constants {

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(11)
            .forwardZeroPowerAcceleration(-42.033298543027946)
            .lateralZeroPowerAcceleration(-64.11007899098118)
            .useSecondaryTranslationalPIDF(false)
            .useSecondaryHeadingPIDF(false)
            .useSecondaryDrivePIDF(false)

            .translationalPIDFCoefficients(new PIDFCoefficients(0.09, 0, 0.008, .021))
//            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.055,0,0.004,0.02))

            .headingPIDFCoefficients(new PIDFCoefficients(1, 0, .0008, 0.011))
//            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(.8, 0, 0.04, 0.02))

            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.03,0.0,0.000008,0.5,0.022))
//            .secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(0.018,0.0,0.0002,0.5,0.02))

            .centripetalScaling(.00485)
            ;

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(.1)
            .rightFrontMotorName("FR")
            .rightRearMotorName("BR")
            .leftRearMotorName("BL")
            .leftFrontMotorName("FL")
            .leftFrontMotorDirection(DcMotorEx.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorEx.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorEx.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorEx.Direction.FORWARD)
            .xVelocity(72.48)
            .yVelocity(60.82)
            ;


    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 0.84, 1.4);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(4.25)
            .strafePodX(2.25)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("POC")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            ;

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();
    }
}