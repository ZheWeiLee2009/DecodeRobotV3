package org.firstinspires.ftc.teamcode.tuning.roboConstants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.Sorter;

@Configurable
public class LauncherConstants {

    // Driver
    @Sorter(sort = 0)
    public static double userFarShootingVelocity = 4000;

    @Sorter(sort = 0)
    public static double userCenterShootingVelocity = 3350;

    @Sorter(sort = 0)
    public static double userHalfShootingVelocity= 2800;


    // Auto
    @Sorter(sort = 1)
    public static double autoFarShootingVelocity = 3800 ;

    @Sorter(sort = 1)
    public static double autoCenterShootingVelocity = 3350;

    @Sorter(sort = 1)
    public static double autoHalfShootingVelocity = 2800;
}