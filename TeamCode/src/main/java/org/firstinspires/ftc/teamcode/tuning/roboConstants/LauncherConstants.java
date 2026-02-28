package org.firstinspires.ftc.teamcode.tuning.roboConstants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.Sorter;

@Configurable
public class LauncherConstants {

    // Driver
    @Sorter(sort = 0)
    public static double userFarShootingVelocity = 5200;

    @Sorter(sort = 0)
    public static double userCenterShootingVelocity = 4200;

    @Sorter(sort = 0)
    public static double userHalfShootingVelocity= 3200;


    // Auto
    @Sorter(sort = 1)
    public static double autoFarShootingVelocity = 1800;

    @Sorter(sort = 1)
    public static double autoCenterShootingVelocity = 1600;

    @Sorter(sort = 1)
    public static double autoHalfShootingVelocity = 1500;
}