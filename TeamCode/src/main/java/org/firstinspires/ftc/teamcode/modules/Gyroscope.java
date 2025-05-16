package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Gyroscope {
    private  LinearOpMode opMode;
    IMU imu;
    private IMU.Parameters parameters = new IMU.Parameters(
            new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                    RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));

    public Gyroscope(LinearOpMode opMode) {
        this.opMode = opMode;
        imu = opMode.hardwareMap.get(IMU.class, "imu");

    }


    public double getHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    public void resetYaw() {
        imu.resetYaw();
    }
}

