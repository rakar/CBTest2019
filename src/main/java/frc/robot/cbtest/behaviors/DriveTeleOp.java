package frc.robot.cbtest.behaviors;

import static frc.robot.cbtest.CBTest2019.requestData;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.behaviors.CBStdDriveBehavior;
import org.montclairrobotics.cyborg.core.data.CBStdDriveControlData;
import org.montclairrobotics.cyborg.core.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.core.utils.CB2DVector;
import org.montclairrobotics.cyborg.core.utils.CBNetworkTable;
import org.montclairrobotics.cyborg.core.utils.CBNetworkTable.CBEntrySource;
import org.montclairrobotics.cyborg.core.utils.CBPIDErrorCorrection;

/**
 * Add your docs here.
 */
public class DriveTeleOp extends CBStdDriveBehavior {
    CBNetworkTable limelight = new CBNetworkTable("limelight");
    CBEntrySource xPos = limelight.getEntrySource("tX");
    CBEntrySource yPos = limelight.getEntrySource("tY");
    CBEntrySource validTarget = limelight.getEntrySource("tV");
    CBPIDErrorCorrection visionXCorrection = 
        new CBPIDErrorCorrection()
            .setTarget()
            .setConstants(0.4, 0.0, 0.0)
            .setSource(xPos);

    public DriveTeleOp(Cyborg robot, CBStdDriveRequestData requestData, CBStdDriveControlData controlData) {
        super(robot, requestData, controlData);
    }

    @Override
    public void update() {
        super.update();
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
    }
}
