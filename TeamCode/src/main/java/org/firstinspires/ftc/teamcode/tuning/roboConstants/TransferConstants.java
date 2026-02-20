package org.firstinspires.ftc.teamcode.tuning.roboConstants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.Sorter;

@Configurable
public class TransferConstants {

    @Sorter(sort = 0)
    public static double fullPowerTransfer = 0.9;

    @Sorter(sort = 1)
    public static double halfPowerTransfer = 0.6;

    @Sorter(sort = 2)
    public static double offPowerTransfer = 0.0;

}
