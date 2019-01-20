/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest.mappers;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.mappers.CBSensorMapper;
import org.montclairrobotics.cyborg.devices.CBNavX;
import static frc.robot.cbtest.CBTest2019.*;

/**
 * Add your docs here.
 */
public class SensorMapper extends CBSensorMapper {
    //RequestData rd;
    CBNavX navx;

    public SensorMapper(Cyborg robot) {
        super(robot);
        navx = ha.getNavX(navxId);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        requestData.yaw = navx.getYaw();
    }

}
