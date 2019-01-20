/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest.hardware;

import static org.montclairrobotics.cyborg.Cyborg.hardwareAdapter;

import com.revrobotics.CANSparkMaxLowLevel;

import org.montclairrobotics.cyborg.devices.CBAxis;
import org.montclairrobotics.cyborg.devices.CBButton;
import org.montclairrobotics.cyborg.devices.CBCANSparkMax;
import org.montclairrobotics.cyborg.devices.CBDeviceID;
import org.montclairrobotics.cyborg.devices.CBNavX;

import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */
public class HALNEO {
    //final int xbox = 0;
    final int driveStickID = 0;

    public CBDeviceID 

    driveRotAxisId = hardwareAdapter.add(
        new CBAxis(driveStickID, 4)
                .setDeadzone(0.1)
    ),
    driveFwdAxisId = hardwareAdapter.add(
        new CBAxis(driveStickID, 5)
                .setDeadzone(0.1)
    ),

    twoHatch01Id = hardwareAdapter.add(new CBButton(driveStickID, 1)),

    // drivetrain
    dtLeftMaster = hardwareAdapter.add(
        new CBCANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless)),            
    dtLeftFollow1 = hardwareAdapter.add(
        new CBCANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless)
            .follow(dtLeftMaster,true)
        ),        
    dtLeftFollow2 = hardwareAdapter.add(
        new CBCANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless)
            .follow(dtLeftMaster)
        ),          

    dtRightMaster = hardwareAdapter.add(
        new CBCANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless)),          
    dtRightFollow1 = hardwareAdapter.add(
        new CBCANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless)
            .follow(dtRightMaster,true)
    ),       
    dtRightFollow2 = hardwareAdapter.add(
        new CBCANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless)
            .follow(dtRightMaster)
    ),            

    navxId = hardwareAdapter.add(
        new CBNavX(SPI.Port.kMXP)
    );

    //limeLight = hardwareAdapter.add(new CBContourReport(key));
}
