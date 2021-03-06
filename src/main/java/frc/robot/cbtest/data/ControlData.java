/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest.data;

import org.montclairrobotics.cyborg.core.data.CBLiftControlData;
import org.montclairrobotics.cyborg.core.data.CBStdDriveControlData;

/**
 * Add your docs here.
 */
public class ControlData {

    /**
     * Initialize the driveData field with standard drive control data.
     * 
     */
    public CBStdDriveControlData drivetrain = new CBStdDriveControlData();
    public CBLiftControlData lift = new CBLiftControlData();
}
