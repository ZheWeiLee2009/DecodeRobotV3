package org.firstinspires.ftc.teamcode.tuning.roboConstants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.Sorter;

@Configurable
public class TransferConstants {

    @Sorter(sort = 0)
    public static double fullPowerTransfer = 1.0;

    @Sorter(sort = 1)
    public static double halfPowerTransfer = 0.6;

    @Sorter(sort = 2)
    public static double offPowerTransfer = 0.0;

    @Sorter(sort = 3)
    public static double gateClosed = .5;

    @Sorter(sort = 4)
    public static double gateOpen = .2;

    @Sorter(sort = 5)
    public static long allReleaseTimer = 600; // ms

}
