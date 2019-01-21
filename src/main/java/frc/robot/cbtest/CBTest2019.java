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
import org.montclairrobotics.cyborg.devices.CBHardwareAdapter;

import frc.robot.cbtest.behaviors.TwoHatch01;
import frc.robot.cbtest.data.ControlData;
import frc.robot.cbtest.data.RequestData;
import frc.robot.cbtest.hardware.HALNEO;
import frc.robot.cbtest.mappers.DriverMapper;
import frc.robot.cbtest.mappers.SensorMapper;

//#endregion

public class CBTest2019 extends Cyborg {

    // ha is a shortcut for typing later - like in mappers...
    public static CBHardwareAdapter ha; 
    public static HALNEO hal;
    //public static HALCIM hal;

    public static RequestData requestData;
    public static ControlData controlData;

    @Override
    public void cyborgInit() {
        // data init
        requestData = new RequestData();
        controlData = new ControlData();

        hardwareAdapter = new CBHardwareAdapter(this);
        ha = hardwareAdapter; // typing shortcut...

        // HAL... classes are used to 
        // abstract Hardware Configurations
        // between different robots
        hal = new HALNEO();
        //hal = new HALCIM();

        installMappers();
        installControllers();
        installBehaviors();

    }

    private void installMappers() {
        this.addTeleOpMapper(
                new CBArcadeDriveMapper(this, requestData.drivetrain)
                        .setAxes(hal.driveFwdAxisId, null, hal.driveRotAxisId)
                        .setShifterButtons(hal.driverShiftHi, hal.driverShiftLow)
                        //.setGyroLockButton(gyroLockButton)
                        //.setDebug(true)
                ,
                new DriverMapper(this)
            );
        this.addSensorMapper(new SensorMapper(this));
    }

    private void installControllers() {
        this.addRobotController(
            new CBDifferentialDriveController(this, controlData.drivetrain)
                .addHighGearSolenoid(hal.shiftHighCoil)
                .addLowGearSolenoid(hal.shiftLowCoil)
                //.setDefaultToHighGear(false)
                .addLeftDriveModule(
                    new CBDriveModule(new CB2DVector(-1, 0), 0) 
                        .addSpeedControllerArray(
                            new CBSimpleSpeedControllerArray()
                                .setDriveMode(CBEnums.CBDriveMode.Power)
                                .addSpeedController(hal.dtLeftMaster)
                                //.addSpeedController(dtLeftFollow1)
                                //.addSpeedController(dtLeftFollow2)
                        )
                )
                .addRightDriveModule(
                    new CBDriveModule(new CB2DVector(1, 0), 180) 
                        .addSpeedControllerArray(
                            new CBSimpleSpeedControllerArray()
                                .setDriveMode(CBEnums.CBDriveMode.Power)
                                .addSpeedController(hal.dtRightMaster)
                                //.addSpeedController(dtRightFollow1)
                                //.addSpeedController(dtRightFollow2)
                        )
                )
            );
    }

    private void installBehaviors() {
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
