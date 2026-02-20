package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.utils.GoBildaPinpointDriver;

public class OdometrySubsystem {

    public Pose2D robotPos(GoBildaPinpointDriver odometry) { return odometry.getPosition();}

}
