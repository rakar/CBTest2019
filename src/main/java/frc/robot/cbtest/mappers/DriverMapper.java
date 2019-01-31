package frc.robot.cbtest.mappers;

import static frc.robot.cbtest.CBTest2019.ha;
import static frc.robot.cbtest.CBTest2019.hal;
import static frc.robot.cbtest.CBTest2019.requestData;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.mappers.CBArcadeDriveMapper;
import org.montclairrobotics.cyborg.core.mappers.CBTeleOpMapper;
import org.montclairrobotics.cyborg.core.utils.CBEnums.CBMotorControlMode;
import org.montclairrobotics.cyborg.devices.CBEncoder;

public class DriverMapper extends CBTeleOpMapper {
    int tick =0;
    CBEncoder encLeft  = ha.getEncoder(hal.encoderLeft);
    CBEncoder encRight = ha.getEncoder(hal.encoderRight);
    CBArcadeDriveMapper driveMapper;

    public DriverMapper(Cyborg robot, CBArcadeDriveMapper driveMapper) {
        super(robot);
        this.driveMapper = driveMapper;
    }

    @Override
    public void init() {
        requestData.doTwoHatch1 = ha.getButton(hal.twoHatch01Id);
        requestData.alignToVisionTarget = ha.getButton(hal.alignToVision);
        requestData.chaseVisionTarget = ha.getButton(hal.chaseVision);
    }

    @Override
    public void update() {
        double fullscale = 10*42*60; // 100 revs/sec * 42 ticks * 60 sec/min

        if (requestData.alignToVisionTarget.getState()) {
            requestData.drivetrain.motorControlMode = CBMotorControlMode.VELOCITY;
            driveMapper.setAxisScales(-fullscale, fullscale, -fullscale);
        }

        if (requestData.chaseVisionTarget.getState()) {
            requestData.drivetrain.motorControlMode = CBMotorControlMode.DUTYCYCLE;
            driveMapper.setAxisScales(-1, 0, -1);
        }
    }
}
