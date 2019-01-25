package frc.robot.cbtest.mappers;

import static frc.robot.cbtest.CBTest2019.ha;
import static frc.robot.cbtest.CBTest2019.hal;
import static frc.robot.cbtest.CBTest2019.requestData;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.mappers.CBTeleOpMapper;
import org.montclairrobotics.cyborg.core.utils.CBEnums.CBMotorControlMode;
import org.montclairrobotics.cyborg.devices.CBEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriverMapper extends CBTeleOpMapper {
    int tick =0;
    CBEncoder encLeft;
    CBEncoder encRight;

    public DriverMapper(Cyborg robot) {
        super(robot);
    }

    @Override
    public void init() {
        requestData.doTwoHatch1 = ha.getButton(hal.twoHatch01Id);
        requestData.alignToVisionTarget = ha.getButton(hal.alignToVision);
        requestData.chaseVisionTarget = ha.getButton(hal.chaseVision);
        encLeft = ha.getEncoder(hal.encoderLeft);
        encRight = ha.getEncoder(hal.encoderRight);
    }

    @Override
    public void update() {
        double fullscale = 6000;
        // if(requestData.chaseVisionTarget.getState()) {
        //     encLeft.reset();
        //     encRight.reset();
        //     SmartDashboard.putNumber("reset", tick++);
        // }

        if (requestData.alignToVisionTarget.getState()) {
            requestData.drivetrain.motorControlMode = CBMotorControlMode.VELOCITY;
        }
        if (requestData.chaseVisionTarget.getState()) {
            requestData.drivetrain.motorControlMode = CBMotorControlMode.DUTYCYCLE;
        }

        if(requestData.drivetrain.motorControlMode==CBMotorControlMode.VELOCITY) {
            double x = fullscale * requestData.drivetrain.direction.getX();
            double y = fullscale * requestData.drivetrain.direction.getY();
            double r = fullscale * requestData.drivetrain.rotation;
            requestData.drivetrain.direction.setXY(x, y);
            requestData.drivetrain.rotation = r;
            SmartDashboard.putNumber("driveY", y);
        }
    }
}
