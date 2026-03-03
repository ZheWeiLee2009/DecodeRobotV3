package org.firstinspires.ftc.teamcode.opmodes.auto.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

public class blueTwoGateAutoPaths {
    public PathChain preload;
    public PathChain line1;
    public PathChain gateOpen1;
    public PathChain shoot1;
    public PathChain align2;
    public PathChain line2;
    public PathChain gateOpen2;
    public PathChain shoot2;
    public PathChain align3;
    public PathChain line3;
    public PathChain shoot3;
    public PathChain leave;


    public blueTwoGateAutoPaths(Follower follower) {
        preload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(23.919, 128.233),

                                new Pose(56.000, 85.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(134))
                .setTimeoutConstraint(300)
                .build();

        line1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.000, 85.000),

                                new Pose(14.800, 83.924)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateOpen1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(14.800, 83.924),
                                new Pose(28.955, 81.245),
                                new Pose(13.573, 74.605)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))

                .build();

        shoot1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(12.573, 72.605),

                                new Pose(56.000, 85.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(134))
                .setTimeoutConstraint(300)
                .build();

        align2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.000, 85.000),

                                new Pose(45.200, 59.680)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(134), Math.toRadians(180))

                .build();

        line2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(45.200, 59.680),

                                new Pose(12.968, 59.680)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateOpen2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(12.968, 59.680),
                                new Pose(45.860, 62.599),
                                new Pose(13.573, 74.605)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))

                .build();

        shoot2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(12.573, 72.605),

                                new Pose(56.000, 85.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(134))

                .build();

        align3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.000, 85.000),

                                new Pose(45.200, 35.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(134), Math.toRadians(180))

                .build();

        line3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(45.200, 35.000),

                                new Pose(12.968, 35.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(12.968, 35.000),

                                new Pose(56.000, 85.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(134))
                .setTimeoutConstraint(300)
                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(56.000, 85.000),

                                new Pose(18.000, 85.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();
    }
}

