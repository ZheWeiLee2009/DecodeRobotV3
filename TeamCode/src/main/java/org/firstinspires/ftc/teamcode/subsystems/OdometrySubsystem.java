package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

public class OdometrySubsystem {

    private GoBildaPinpointDriver odometry;

    public OdometrySubsystem(GoBildaPinpointDriver odometryX) {
        this.odometry = odometryX;
    }
    /**
     * Returns the position of the robot relative to its starting point.
     *
     * @return Pose2D object of Position
     */
    public Pose2D robotPos() { return odometry.getPosition();}

}
