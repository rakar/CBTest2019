/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest.data;

import org.montclairrobotics.cyborg.core.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.core.utils.CBEdgeTrigger;

/**
 * Add your docs here.
 */
public class RequestData {
    /**
     * Drivetrain
     */
    public CBStdDriveRequestData drivetrain = new CBStdDriveRequestData();
    public CBEdgeTrigger doTwoHatch1;

    public double yaw;
}
