/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest.hardware;

import static org.montclairrobotics.cyborg.Cyborg.hardwareAdapter;

import org.montclairrobotics.cyborg.devices.CBAxis;
import org.montclairrobotics.cyborg.devices.CBButton;
import org.montclairrobotics.cyborg.devices.CBDeviceID;
import org.montclairrobotics.cyborg.devices.CBNavX;
import org.montclairrobotics.cyborg.devices.CBTalonSRX;

import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */
public class HALCIM {
    final int driveStickID = 0;

    public CBDeviceID 
    driveRotAxisId = hardwareAdapter.add(
        new CBAxis(driveStickID, 0)
                .setDeadzone(0.1)
    ),
    driveFwdAxisId = hardwareAdapter.add(
        new CBAxis(driveStickID, 1)
                .setDeadzone(0.1)
    ),

    twoHatch01Id = hardwareAdapter.add(new CBButton(driveStickID, 0)),

    // drivetrain
    dtLeftMaster = hardwareAdapter.add(
        new CBTalonSRX(1)),            
    dtLeftFollow1 = hardwareAdapter.add(
        new CBTalonSRX(2)
            .follow(dtLeftMaster,true)
        ),        
    dtLeftFollow2 = hardwareAdapter.add(
        new CBTalonSRX(3)
            .follow(dtLeftMaster)
        ),          

    dtRightMaster = hardwareAdapter.add(
        new CBTalonSRX(7)),          
    dtRightFollow1 = hardwareAdapter.add(
        new CBTalonSRX(8)
            .follow(dtRightMaster,true)
    ),       
    dtRightFollow2 = hardwareAdapter.add(
        new CBTalonSRX(9)
            .follow(dtRightMaster)
    ),            

    navxId = hardwareAdapter.add(
        new CBNavX(SPI.Port.kMXP)
    );

    //limeLight = hardwareAdapter.add(new CBContourReport(key));
}
