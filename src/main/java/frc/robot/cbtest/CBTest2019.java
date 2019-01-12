/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest;
//#region imports
import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.assemblies.CBDriveModule;
import org.montclairrobotics.cyborg.core.assemblies.CBSimpleSpeedControllerArray;
import org.montclairrobotics.cyborg.core.behaviors.CBStdDriveBehavior;
import org.montclairrobotics.cyborg.core.controllers.CBDifferentialDriveController;
import org.montclairrobotics.cyborg.core.mappers.CBArcadeDriveMapper;
import org.montclairrobotics.cyborg.core.utils.CB2DVector;
import org.montclairrobotics.cyborg.core.utils.CBEnums;
import org.montclairrobotics.cyborg.devices.CBAxis;
import org.montclairrobotics.cyborg.devices.CBDeviceID;
import org.montclairrobotics.cyborg.devices.CBHardwareAdapter;
import org.montclairrobotics.cyborg.devices.CBTalonSRX;

import frc.robot.cbtest.data.ControlData;
import frc.robot.cbtest.data.RequestData;
//#endregion

/**
 * Add your docs here.
 */
public class CBTest2019 extends Cyborg {

        // constants
    // joystick ports
    private final int driveStickID = 0;
    private final int operatorStickID = 1;

    //
    // the devices have been changed to "public static" from "private" to allow
    // for direct access in cases of NON-reusable mappers/behaviors
    // that will always be robot specific anyway in order to avoid lengthy init code. 
    // Reusable code, should of course use setter functions to
    // attach to devices.
    //
    //#region Device List...
    public static CBDeviceID 
    // driver controls
    driveRotAxis, driveFwdAxis, 

    // drivetrain Motors
    dtLeftMotor0, dtLeftMotor1, dtLeftMotor2,
    dtRightMotor0, dtRightMotor1, dtRightMotor2

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
        CBHardwareAdapter ha = hardwareAdapter;

        // driver controls
        driveRotAxis = ha.add(
            new CBAxis(driveStickID, 1)
                    .setDeadzone(0.1)
        );

        driveFwdAxis = ha.add(
            new CBAxis(driveStickID, 0)
                    .setDeadzone(0.1)
        );

        // drivetrain
        dtLeftMotor0 = ha.add(new CBTalonSRX(0));            
        dtLeftMotor1 = ha.add(new CBTalonSRX(1));            
        dtLeftMotor2 = ha.add(new CBTalonSRX(2));            
        dtRightMotor0 = ha.add(new CBTalonSRX(7));            
        dtRightMotor1 = ha.add(new CBTalonSRX(8));            
        dtRightMotor2 = ha.add(new CBTalonSRX(9));            
    }

    private void defineMappers() {
        // setup teleop mappers
        this.addTeleOpMapper(
                new CBArcadeDriveMapper(this, requestData.drivetrain)
                        .setAxes(driveFwdAxis, null, driveRotAxis)
                        //.setGyroLockButton(gyroLockButton)
        );
    }

    private void defineControllers() {
        this.addRobotController(
            new CBDifferentialDriveController(this, controlData.drivetrain)
                .addLeftDriveModule(
                    new CBDriveModule(new CB2DVector(-1, 0), 0) 
                        .addSpeedControllerArray(
                            new CBSimpleSpeedControllerArray()
                                .setDriveMode(CBEnums.CBDriveMode.Power)
                                .addSpeedController(dtLeftMotor0)
                                .addSpeedController(dtLeftMotor1)
                                .addSpeedController(dtLeftMotor2)
                        )
                )
                .addRightDriveModule(
                    new CBDriveModule(new CB2DVector(1, 0), 180) 
                        .addSpeedControllerArray(
                            new CBSimpleSpeedControllerArray()
                                .setDriveMode(CBEnums.CBDriveMode.Power)
                                .addSpeedController(dtRightMotor0)
                                .addSpeedController(dtRightMotor1)
                                .addSpeedController(dtRightMotor2)
                        )
                )
            );
    }

    private void defineBehaviors() {
        this.addBehavior(
            new CBStdDriveBehavior(this, requestData.drivetrain, controlData.drivetrain)
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
