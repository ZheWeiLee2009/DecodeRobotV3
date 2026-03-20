package org.firstinspires.ftc.teamcode.opmodes.auto.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class blueCyclingAutoPaths {
    public PathChain preload;
    public PathChain line2;
    public PathChain shoot2;
    public PathChain gatecycle;
    public PathChain gategrab;
    public PathChain gateshoot;
    public PathChain line1;
    public PathChain shoot1;
    public PathChain leave;

    public blueCyclingAutoPaths(Follower follower) {
        preload = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(28.500, 135.000),

                                new Pose(55.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180.000), Math.toRadians(132.500))

                .build();

        line2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(55.000, 86.000),
                                new Pose(54.344, 57.325),
                                new Pose(9.185, 59.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(9.185, 59.000),
                                new Pose(53.996, 74.088),
                                new Pose(55.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180.000), Math.toRadians(134.500))
                .setTimeoutConstraint(300)
                .build();

        gatecycle = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(55.000, 86.000),
                                new Pose(46.151, 71.897),
                                new Pose(7.600, 61.600)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(134.500), Math.toRadians(141.000))

                .build();

        gategrab = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(7.600, 61.600),

                                new Pose(6.500, 54.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(141.000), Math.toRadians(122.000))

                .build();

        gateshoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(6.500, 54.000),
                                new Pose(46.496, 72.094),
                                new Pose(55.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(122.000), Math.toRadians(132.500))
                .setTimeoutConstraint(300)
                .build();

        line1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(55.000, 86.000),
                                new Pose(45.354, 82.893),
                                new Pose(13.500, 83.451)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(13.500, 83.451),

                                new Pose(55.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180.000), Math.toRadians(132.500))
                .setTimeoutConstraint(300)
                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(55.000, 86.000),

                                new Pose(15.874, 85.771)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(132.500), Math.toRadians(180.000))

                .build();
    }
}