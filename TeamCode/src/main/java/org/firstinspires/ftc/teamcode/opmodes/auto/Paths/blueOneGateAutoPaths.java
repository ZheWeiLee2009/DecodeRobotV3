package org.firstinspires.ftc.teamcode.opmodes.auto.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class blueOneGateAutoPaths {

    public PathChain preload;
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

    public blueOneGateAutoPaths(Follower follower) {
        preload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(24.000, 128),

                                new Pose(55.500, 88.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(144), Math.toRadians(129))

                .build();

        line1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(55.500, 88.000),
                                new Pose(45.435, 83.534),
                                new Pose(17.737, 83.334)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        gateOpen1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(17.737, 83.334),
                                new Pose(32.606, 77.410),
                                new Pose(13.693, 73.348)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))

                .build();

        shoot1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(13.693, 73.348),

                                new Pose(55.500, 88.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(129))

                .build();

        align2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(55.500, 88.000),

                                new Pose(46.500, 59.800)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(129), Math.toRadians(180))

                .build();

        line2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(46.500, 59.800),

                                new Pose(10.000, 59.472)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(10.000, 59.472),
                                new Pose(46.500, 59.800),
                                new Pose(55.500, 88.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(129))

                .build();

        align3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(55.500, 88.000),

                                new Pose(43.551, 35.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(129), Math.toRadians(180))

                .build();

        line3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(43.551, 35.000),

                                new Pose(10.000, 35.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(10.000, 35.000),

                                new Pose(55.500, 88.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(129))

                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(55.500, 88.000),

                                new Pose(22.980, 87.795)
                        )
                ).setConstantHeadingInterpolation(Math.toRadians(129))

                .build();
    }
}
