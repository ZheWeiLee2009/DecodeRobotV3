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
    public PathChain gategrab;
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
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(47.5))

                .build();

        line2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(89.000, 86.000),
                                new Pose(89.656, 57.325),
                                new Pose(134.815, 59.000)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(134.815, 59.000),
                                new Pose(90.004, 74.088),
                                new Pose(89.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45.5))
                .setTimeoutConstraint(300)
                .build();

        gatecycle = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(89.000, 86.000),
                                new Pose(97.849, 71.897),
                                new Pose(136.400, 61.600)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(45.5), Math.toRadians(39))

                .build();

        gategrab = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(136.400, 61.600),

                                new Pose(137.500, 54.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(39), Math.toRadians(58))

                .build();

        gateshoot = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(137.500, 54.000),
                                new Pose(97.504, 72.094),
                                new Pose(89.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(58), Math.toRadians(47.5))
                .setTimeoutConstraint(300)
                .build();

        line1 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(89.000, 86.000),
                                new Pose(98.646, 82.893),
                                new Pose(130.500, 83.451)
                        )
                ).setTangentHeadingInterpolation()

                .build();

        shoot1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(130.500, 83.451),

                                new Pose(89.000, 86.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(47.5))
                .setTimeoutConstraint(300)
                .build();

        leave = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(89.000, 86.000),

                                new Pose(128.126, 85.771)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(47.5), Math.toRadians(0))

                .build();
    }
}