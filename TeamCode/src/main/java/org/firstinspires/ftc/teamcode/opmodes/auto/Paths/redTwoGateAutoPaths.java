package org.firstinspires.ftc.teamcode.opmodes.auto.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

public class    redTwoGateAutoPaths {
    public PathChain preload;
    public PathChain align1;
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

    public redTwoGateAutoPaths(Follower follower) {
        preload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(116, 127.000),

                                new Pose(78.000, 100.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(36.000), Math.toRadians(40.000))

                .build();

        align1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(78.000, 100.000),

                                new Pose(86.800, 83.924)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(40.000), Math.toRadians(0.000))

                .build();

        line1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(86.800, 83.924),

                                new Pose(119.200, 83.924)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateOpen1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(119.200, 83.924),
                                new Pose(105.045, 81.245),
                                new Pose(121.427, 72.605)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0.000), Math.toRadians(90.000))

                .build();

        shoot1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(121.427, 72.605),

                                new Pose(78.000, 100.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90.000), Math.toRadians(40.000))

                .build();

        align2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(78.000, 100.000),

                                new Pose(86.800, 59.680)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(40.000), Math.toRadians(0.000))

                .build();

        line2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(86.800, 59.680),

                                new Pose(123.032, 59.680)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateOpen2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(123.032, 59.680),
                                new Pose(88.140, 62.599),
                                new Pose(121.427, 72.605)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0.000), Math.toRadians(90.000))

                .build();

        shoot2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(121.427, 72.605),

                                new Pose(78.000, 100.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90.000), Math.toRadians(40.000))

                .build();

        align3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(78.000, 100.000),

                                new Pose(86.800, 36.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(40.000), Math.toRadians(0.000))

                .build();

        line3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(86.800, 36.000),

                                new Pose(123.032, 36.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(123.032, 36.000),

                                new Pose(78.000, 100.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0.000), Math.toRadians(40.000))

                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(78.000, 100.000),

                                new Pose(116.229, 96.006)
                        )
                ).setTangentHeadingInterpolation()

                .build();
    }
}
