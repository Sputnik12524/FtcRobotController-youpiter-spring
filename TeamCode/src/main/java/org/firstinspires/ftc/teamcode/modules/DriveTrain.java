package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class DriveTrain {


    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

    IMU imu;
    private  RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection;
    private  RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection;

    static final double ENCODER_PULSES = 537.7;
    static final double WHEELS_DIAMETER = 9.6;
    static final double AXIS = 4;
    static final double LIFT_CM = ENCODER_PULSES / (AXIS * Math.PI);
    static final double PULSES_PER_CM = ENCODER_PULSES / (Math.PI * WHEELS_DIAMETER);

    public enum RobotDirection {FORWARD, BACK, RIGHT, LEFT, CLOCKWISE, COUNTERCLOCKWISE}

    ;

    public DriveTrain(LinearOpMode mode) {

        leftFront = mode.hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = mode.hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = mode.hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = mode.hardwareMap.get(DcMotor.class, "rightBack");

        logoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection);


        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void driveByEncoder(RobotDirection direction, double power, double distanse) {
        while (Math.abs(leftFront.getCurrentPosition()) < Math.abs(distanse)) {
            switch (direction) {

                case FORWARD:
                    setPower(power);
                    break;
                case BACK:
                    setPower(-power);
                    break;
                case RIGHT:
                    setPower(power, -power, -power, power);
                    break;
                case LEFT:
                    setPower(-power, power, power, -power);
                    break;
            }
        }
    }

    public void turnRight(double power, double degrees) {
        imu.resetYaw();
        while (Math.abs(this.getDegrees()) < Math.abs(degrees)) {
            setPower(power, -power, power, -power);


        }
    }


    public void setMode(DcMotor.RunMode mode) {
        leftFront.setMode(mode);
        rightFront.setMode(mode);
        leftBack.setMode(mode);
        rightBack.setMode(mode);
    }

    public void setPower(double lF, double rF, double lB, double rB) {
        leftFront.setPower(lF);
        rightFront.setPower(rF);
        leftBack.setPower(lB);
        rightBack.setPower(rB);
    }

    public void setPower(double power) {
        setPower(power, power, power, power);
    }

    public void driveForward(double power, double distanse) {
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (Math.abs(leftFront.getCurrentPosition()) < distanse * PULSES_PER_CM) {
            leftFront.setPower(power);
            rightFront.setPower(power);
            leftBack.setPower(power);
            rightBack.setPower(power);
        }
    }

    public double getDegrees() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }


}
