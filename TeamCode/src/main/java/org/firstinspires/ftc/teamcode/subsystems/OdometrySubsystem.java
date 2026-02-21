package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.utils.GoBildaPinpointDriver;

public class OdometrySubsystem {

    /**
     * Returns the position of the robot relative to its starting point.
     *
     * @param odometry Odometry object
     * @return Pose2D object of Position
     */
    public Pose2D robotPos(GoBildaPinpointDriver odometry) { return odometry.getPosition();}

}
