package org.firstinspires.ftc.teamcode.opmodes.auto.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class redOneGateAutoPaths {

    public PathChain preload;
    public PathChain align1;
    public PathChain line1;
    public PathChain gateOpen1;
    public PathChain shoot1;
    public PathChain align2;
    public PathChain line2;
    public PathChain shoot2;
    public PathChain align3;
    public PathChain line3;
    public PathChain shoot3;
    public PathChain leave;

    public redOneGateAutoPaths(Follower follower) {
        preload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(115.500, 135.000),

                                new Pose(94.000, 90.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0.000), Math.toRadians(48.000))

                .build();

        align1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(94.000, 90.000),

                                new Pose(96.000, 82.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(48.000), Math.toRadians(0.000))

                .build();

        line1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(96.000, 82.000),

                                new Pose(133.000, 82.000)
                        )
                ).setConstantHeadingInterpolation(Math.toRadians(0.000))

                .build();

        gateOpen1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(133.000, 82.000),
                                new Pose(117.000, 81.000),
                                new Pose(132.400, 77.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0.000), Math.toRadians(90.000))

                .build();

        shoot1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(132.400, 77.000),

                                new Pose(94.000, 90.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90.000), Math.toRadians(48.000))

                .build();

        align2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(94.000, 90.000),

                                new Pose(96.000, 58.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(48.000), Math.toRadians(0.000))

                .build();

        line2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(96.000, 58.000),

                                new Pose(140.200, 58.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(140.200, 58.000),
                                new Pose(96.000, 58.000),
                                new Pose(94.000, 90.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0.000), Math.toRadians(48.000))

                .build();

        align3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(94.000, 90.000),

                                new Pose(96.000, 34.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(48.000), Math.toRadians(0.000))

                .build();

        line3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(96.000, 34.000),

                                new Pose(140.200, 34.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(140.200, 34.000),

                                new Pose(94.000, 90.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0.000), Math.toRadians(48.000))

                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(94.000, 90.000),

                                new Pose(126.000, 96.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();
    }
}
