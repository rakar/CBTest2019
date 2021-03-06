package frc.robot.cbtest.behaviors;

import static frc.robot.cbtest.CBTest2019.ha;
import static frc.robot.cbtest.CBTest2019.hal;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.behaviors.CBStdDriveBehavior;
import org.montclairrobotics.cyborg.core.data.CBStdDriveControlData;
import org.montclairrobotics.cyborg.core.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.core.utils.CBPIDErrorCorrection;
import org.montclairrobotics.cyborg.devices.CBNetworkTable;
import org.montclairrobotics.cyborg.devices.CBNetworkTable.CBEntrySource;

/**
 * Add your docs here.
 */
public class DriveTeleOp extends CBStdDriveBehavior {
    CBNetworkTable limelight = ha.getNetworkTable(hal.limelight);
    CBEntrySource xPos = limelight.getEntrySource("tX");
    CBEntrySource yPos = limelight.getEntrySource("tY");
    CBEntrySource validTarget = limelight.getEntrySource("tV");
    CBPIDErrorCorrection visionXCorrection = 
        new CBPIDErrorCorrection()
            .setTarget()
            .setConstants(0.3, 0.0, 0.0)
            .setSource(xPos);

    public DriveTeleOp(Cyborg robot, CBStdDriveRequestData requestData, CBStdDriveControlData controlData) {
        super(robot, requestData, controlData);
    }

    @Override
    public void update() {
        super.update();

        /*
        if (requestData.alignToVisionTarget.getState() && validTarget.get() > .5) {
            if (requestData.alignToVisionTarget.getRisingEdge()) {
                visionXCorrection.reset();
            }
            dcd.active = true;
            dcd.direction = new CB2DVector(0, 0);
            dcd.rotation = visionXCorrection.update();
        } else {
            if (requestData.chaseVisionTarget.getState() && validTarget.get() > .5) {
                if (requestData.chaseVisionTarget.getRisingEdge()) {
                    visionXCorrection.reset();
                }
                dcd.active = true;
                dcd.direction = new CB2DVector(0, 0.3);
                dcd.rotation = visionXCorrection.update();
            }
        }
        */
    }
}
