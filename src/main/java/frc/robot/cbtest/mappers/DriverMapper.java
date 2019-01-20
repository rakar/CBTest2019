/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest.mappers;

import static frc.robot.cbtest.CBTest2019.ha;
import static frc.robot.cbtest.CBTest2019.hal;
import static frc.robot.cbtest.CBTest2019.requestData;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.mappers.CBTeleOpMapper;
import org.montclairrobotics.cyborg.devices.CBButton;

public class DriverMapper extends CBTeleOpMapper {
    CBButton twoHatch;
    CBButton driverShiftHi, driverShiftLow;

    public DriverMapper(Cyborg robot) {
        super(robot);
        twoHatch = ha.getButton(hal.twoHatch01Id);
        driverShiftHi = ha.getButton(hal.driverShiftHi);
        driverShiftLow = ha.getButton(hal.driverShiftLow);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        requestData.initTwoHatch1 = twoHatch.getRisingEdge();
        requestData.doTwoHatch1 = twoHatch.getState();
        //requestData.shiftToHi = driverShiftHi.getRisingEdge();
        //requestData.shiftToLow = driverShiftLow.getFallingEdge();
        
    }
}
