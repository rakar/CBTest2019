/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest;
//#region imports

import com.revrobotics.CANSparkMaxLowLevel;
import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.assemblies.CBDriveModule;
import org.montclairrobotics.cyborg.core.assemblies.CBSimpleSpeedControllerArray;
import org.montclairrobotics.cyborg.core.behaviors.CBStdDriveBehavior;
import org.montclairrobotics.cyborg.core.controllers.CBDifferentialDriveController;
import org.montclairrobotics.cyborg.core.mappers.CBArcadeDriveMapper;
import org.montclairrobotics.cyborg.core.utils.CB2DVector;
import org.montclairrobotics.cyborg.core.utils.CBEnums;
import org.montclairrobotics.cyborg.devices.CBAxis;
import org.montclairrobotics.cyborg.devices.CBButton;
import org.montclairrobotics.cyborg.devices.CBCANSparkMax;
import org.montclairrobotics.cyborg.devices.CBDeviceID;
import org.montclairrobotics.cyborg.devices.CBHardwareAdapter;
import org.montclairrobotics.cyborg.devices.CBNavX;

import edu.wpi.first.wpilibj.SPI;
import frc.robot.cbtest.behaviors.TwoHatch01;
import frc.robot.cbtest.data.ControlData;
import frc.robot.cbtest.data.RequestData;
import frc.robot.cbtest.mappers.DriverMapper;
import frc.robot.cbtest.mappers.SensorMapper;

//#endregion

public class CBTest2019 extends Cyborg {

    // constants
    // joystick ports
    private final int driveStickID = 0;
    //private final int operatorStickID = 1;

    //
    // the devices have been changed to "public static" from "private" to allow
    // for direct access in cases of NON-reusable mappers/behaviors
    // that will always be robot specific anyway in order to avoid lengthy init code. 
    // Reusable code, should of course use setter functions to
    // attach to devices.
    //


    public static CBHardwareAdapter ha;
    //#region Device List...
    public static CBDeviceID 
    // driver controls
    driveRotAxisId, driveFwdAxisId, twoHatch01Id,

    // drivetrain Motors
    dtLeftMotor0Id, dtLeftMotor1Id, dtLeftMotor2Id,
    dtRightMotor0Id, dtRightMotor1Id, dtRightMotor2Id,

    // sensors
    navxId

    ;
    //#endregion

    public static RequestData requestData;
    public static ControlData controlData;

    @Override
    public void cyborgInit() {
        // data init
        requestData = new RequestData();
        controlData = new ControlData();

        defineDevices();
        defineMappers();
        defineControllers();
        defineBehaviors();

    }

    private void defineDevices() {
        // Configure Hardware Adapter and Devices
        hardwareAdapter = new CBHardwareAdapter(this);
        ha = hardwareAdapter;

        // driver controls
        driveRotAxisId = hardwareAdapter.add(
            new CBAxis(driveStickID, 0)
                    .setDeadzone(0.1)
        );

        driveFwdAxisId = hardwareAdapter.add(
            new CBAxis(driveStickID, 1)
                    .setDeadzone(0.1)
        );

        twoHatch01Id = hardwareAdapter.add(new CBButton(driveStickID, 0));

        // drivetrain
        dtLeftMotor0Id = hardwareAdapter.add(new CBCANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless));            
        dtLeftMotor1Id = hardwareAdapter.add(
            new CBCANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless)
                .follow(dtLeftMotor0Id)
            );            
        dtLeftMotor2Id = hardwareAdapter.add(
            new CBCANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless)
                .follow(dtLeftMotor0Id)
            );            

        dtRightMotor0Id = hardwareAdapter.add(new CBCANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless));            
        dtRightMotor1Id = hardwareAdapter.add(
            new CBCANSparkMax(8, CANSparkMaxLowLevel.MotorType.kBrushless)
                .follow(dtRightMotor0Id)
        );            
        dtRightMotor2Id = hardwareAdapter.add(
            new CBCANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushless)
                .follow(dtRightMotor0Id)
        );            

        navxId = hardwareAdapter.add(
            new CBNavX(SPI.Port.kMXP)
        );
    }

    private void defineMappers() {
        this.addTeleOpMapper(
                new CBArcadeDriveMapper(this, requestData.drivetrain)
                        .setAxes(driveFwdAxisId, null, driveRotAxisId)
                        //.setGyroLockButton(gyroLockButton)
                        //.setDebug(true)
                ,
                new DriverMapper(this)
            );
        this.addSensorMapper(new SensorMapper(this));
    }

    private void defineControllers() {
        this.addRobotController(
            new CBDifferentialDriveController(this, controlData.drivetrain)
                .addLeftDriveModule(
                    new CBDriveModule(new CB2DVector(-1, 0), 0) 
                        .addSpeedControllerArray(
                            new CBSimpleSpeedControllerArray()
                                .setDriveMode(CBEnums.CBDriveMode.Power)
                                .addSpeedController(dtLeftMotor0Id)
                                //.addSpeedController(dtLeftMotor1)
                                //.addSpeedController(dtLeftMotor2)
                        )
                )
                .addRightDriveModule(
                    new CBDriveModule(new CB2DVector(1, 0), 180) 
                        .addSpeedControllerArray(
                            new CBSimpleSpeedControllerArray()
                                .setDriveMode(CBEnums.CBDriveMode.Power)
                                .addSpeedController(dtRightMotor0Id)
                                //.addSpeedController(dtRightMotor1)
                                //.addSpeedController(dtRightMotor2)
                        )
                )
            );
    }

    private void defineBehaviors() {
        this.addBehavior(
            new CBStdDriveBehavior(this, requestData.drivetrain, controlData.drivetrain),    
            new TwoHatch01(this)
        );
    }
    
    @Override
    public void cyborgAutonomousInit() {

    }

    @Override
    public void cyborgDisabledInit() {

    }


    @Override
    public void cyborgTeleopInit() {

    }

    @Override
    public void cyborgTestInit() {

    }
}
