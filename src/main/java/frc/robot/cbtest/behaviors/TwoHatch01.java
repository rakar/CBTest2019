/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest.behaviors;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.behaviors.CBBehavior;

import static frc.robot.cbtest.CBTest2019.*;

public class TwoHatch01 extends CBBehavior {

    public TwoHatch01(Cyborg robot) {
        super(robot);
    }

    @Override
    public void update() {
        if(requestData.doTwoHatch1.getState()) {
            if(requestData.doTwoHatch1.getRisingEdge()) {
                // init state machine, because it was just pressed
            }
            // process state machine
        }
    }
}
