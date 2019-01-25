/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.cbtest.mappers;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.mappers.CBSensorMapper;
import org.montclairrobotics.cyborg.devices.CBCANSparkMax;
import org.montclairrobotics.cyborg.devices.CBEncoder;
import org.montclairrobotics.cyborg.devices.CBNavX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.cbtest.CBTest2019.*;

/**
 * Add your docs here.
 */
public class SensorMapper extends CBSensorMapper {
    CBNavX navx = ha.getNavX(hal.navxId);
    CBEncoder encLeft = ha.getEncoder(hal.encoderLeft);
    CBEncoder encRight = ha.getEncoder(hal.encoderRight);
    CBCANSparkMax smLeft = ha.getCBCANSparkMax(hal.dtLeftMaster);

    public SensorMapper(Cyborg robot) {
        super(robot);
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
        requestData.yaw = navx.getYaw();
        
        SmartDashboard.putNumber("NavX Angle", navx.getAngle());
        SmartDashboard.putNumber("Navx DisplaceX", navx.getDisplacementX());
        SmartDashboard.putNumber("Navx DisplaceY", navx.getDisplacementY());
        SmartDashboard.putNumber("Navx DisplaceZ", navx.getDisplacementZ());
        SmartDashboard.putNumber("Navx Accel X", navx.getRawAccelX());
        SmartDashboard.putNumber("Navx Accel Y", navx.getRawAccelY());
        SmartDashboard.putNumber("Navx Accel Z", navx.getRawAccelZ());
        SmartDashboard.putNumber("Navx World X", navx.getWorldLinearAccelX());
        SmartDashboard.putNumber("Navx World Y", navx.getWorldLinearAccelY());
        SmartDashboard.putNumber("Navx World Z", navx.getWorldLinearAccelZ());

        SmartDashboard.putString("ControlType", requestData.drivetrain.motorControlMode.toString());
        SmartDashboard.putNumber("Encoder Left ", encLeft.getDistance());
        SmartDashboard.putNumber("Encoder Right", encRight.getDistance());
    }

}
