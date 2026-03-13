package org.firstinspires.ftc.teamcode.opmodes.auto.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class redOneGateCycleAutoPaths {
    public PathChain preload;
    public PathChain line2;
    public PathChain shoot2;
    public PathChain gateOpen1;
    public PathChain shootn1;

    public redOneGateCycleAutoPaths(Follower follower) {
        preload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(115.500, 135.000),

                                new Pose(89.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(46.5))

                .build();

        line2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(89.000, 86.000),
                                new Pose(92.921, 55.103),
                                new Pose(129.589, 59.106)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(129.589, 59.106),
                                new Pose(94.341, 65.983),
                                new Pose(89.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(46.5))

                .build();

        gateOpen1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(89.000, 86.000),

                                new Pose(134.000, 61.003)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(46.5), Math.toRadians(36.3))

                .build();

        shootn1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(134.000, 61.003),

                                new Pose(89.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36.3), Math.toRadians(46.5))

                .build();
    }
}







