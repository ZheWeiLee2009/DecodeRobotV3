package org.firstinspires.ftc.teamcode.tuning.roboConstants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.Sorter;

@Configurable
public class IntakeConstants {

    @Sorter(sort = 0)
    public static double fullPowerIntake = 0.9;

    @Sorter(sort = 1)
    public static double halfPowerIntake = 0.6;

    @Sorter(sort = 2)
    public static double offPowerIntake = 0.0;

}
