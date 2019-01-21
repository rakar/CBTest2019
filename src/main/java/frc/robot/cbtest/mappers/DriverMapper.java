package frc.robot.cbtest.mappers;

import static frc.robot.cbtest.CBTest2019.ha;
import static frc.robot.cbtest.CBTest2019.hal;
import static frc.robot.cbtest.CBTest2019.requestData;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.mappers.CBTeleOpMapper;

public class DriverMapper extends CBTeleOpMapper {

    public DriverMapper(Cyborg robot) {
        super(robot);
    }

    @Override
    public void init() {
        requestData.doTwoHatch1 = ha.getButton(hal.twoHatch01Id);
        requestData.alignToVisionTarget = ha.getButton(hal.alignToVision);
    }

    @Override
    public void update() {
    }
}
