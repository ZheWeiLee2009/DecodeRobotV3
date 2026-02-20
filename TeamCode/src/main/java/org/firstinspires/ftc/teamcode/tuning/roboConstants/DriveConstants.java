package org.firstinspires.ftc.teamcode.tuning.roboConstants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.Sorter;

@Configurable
public class DriveConstants {

    @Sorter(sort = 0)
    public static double DriveSpeed = 0.95;

    @Sorter(sort = 1)
    public static double FL_Offset = 1;

    @Sorter(sort = 2)
    public static double BL_Offset = 1;

    @Sorter(sort = 3)
    public static double FR_Offset = 1;

    @Sorter(sort = 4)
    public static double BR_Offset = 1;
}
