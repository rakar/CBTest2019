package frc.robot.cbtest.data;

import org.montclairrobotics.cyborg.core.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.core.utils.CBEdgeTrigger;

/**
 * Add your docs here.
 */
public class RequestData {
    /**
     * Drivetrain
     */
    public CBStdDriveRequestData drivetrain = new CBStdDriveRequestData();

    public CBEdgeTrigger doTwoHatch1;
    public CBEdgeTrigger alignToVisionTarget;
    public CBEdgeTrigger chaseVisionTarget;

    // Sensor Data
    public double yaw;
    
}
