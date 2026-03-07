package org.firstinspires.ftc.teamcode.opmodes.auto.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

public class    blueTwoGateAutoPaths {
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

    public blueTwoGateAutoPaths(Follower follower) {
        preload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(28.500, 135.000),

                                new Pose(50.000, 90.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(131))

                .build();

        align1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(50.000, 90.000),

                                new Pose(48.000, 82.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(131), Math.toRadians(180))

                .build();

        line1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(48.000, 82.000),

                                new Pose(11.000, 82.000)
                        )
                ).setConstantHeadingInterpolation(Math.toRadians(180))

                .build();

        gateOpen1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(11.000, 82.000),
                                new Pose(27.000, 81.000),
                                new Pose(11.600, 77.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))

                .build();

        shoot1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(11.600, 77.000),

                                new Pose(50.000, 90.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(131))

                .build();

        align2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(50.000, 90.000),

                                new Pose(48.000, 58.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(131), Math.toRadians(180))

                .build();

        line2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(48.000, 58.000),

                                new Pose(3.800, 58.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateOpen2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(3.800, 58.000),
                                new Pose(48.000, 58.000),
                                new Pose(11.600, 77.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))

                .build();

        shoot2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(11.600, 77.000),

                                new Pose(50.000, 90.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(131))

                .build();

        align3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(50.000, 90.000),

                                new Pose(48.000, 34.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(131), Math.toRadians(180))

                .build();

        line3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(48.000, 34.000),

                                new Pose(3.800, 34.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(3.800, 34.000),

                                new Pose(50.000, 90.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(131))

                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(50.000, 90.000),

                                new Pose(18.000, 96.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();
    }
}
