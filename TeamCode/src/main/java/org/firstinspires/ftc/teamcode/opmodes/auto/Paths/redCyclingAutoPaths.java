package org.firstinspires.ftc.teamcode.opmodes.auto.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class redCyclingAutoPaths {
    public PathChain preload;
    public PathChain line2;
    public PathChain shoot2;
    public PathChain gatecycle;
    public PathChain gateshoot;
    public PathChain line1;
    public PathChain shoot1;
    public PathChain leave;

    public redCyclingAutoPaths(Follower follower) {
        preload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(115.500, 135.000),

                                new Pose(89.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(50))

                .build();

        line2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(89.000, 86.000),
                                new Pose(90.000, 55.000),
                                new Pose(130.000, 59.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(130.000, 59.000),
                                new Pose(96.654, 73.630),
                                new Pose(89.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(50))

                .build();

        gatecycle = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(89.000, 86.000),
                                new Pose(98.307, 71.439),
                                new Pose(134.672, 59.689)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(50), Math.toRadians(39))

                .build();

        gateshoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(134.672, 59.689),
                                new Pose(97.962, 72.094),
                                new Pose(89.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(39), Math.toRadians(50))

                .build();

        line1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(89.000, 86.000),
                                new Pose(100.022, 83.352),
                                new Pose(127.782, 83.222)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(89.000, 86.000),

                                new Pose(128.126, 85.771)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(50), Math.toRadians(0))

                .build();
    }
}







