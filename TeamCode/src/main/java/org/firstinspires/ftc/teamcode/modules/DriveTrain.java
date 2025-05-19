package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class DriveTrain {

    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    IMU imu;

    static final double ENCODER_PULSES = 537.7;
    static final double PI = 3.1415926535;
    static final double WHEELS_DIAMETER = 9.6;
    static final double AXIS = 4;
    static final double LIFT_CM = ENCODER_PULSES / (AXIS * PI);
    static final double PULSES_PER_CM = ENCODER_PULSES / (PI * WHEELS_DIAMETER);

    public DriveTrain(LinearOpMode mode){

        leftFront = mode.hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = mode.hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = mode.hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = mode.hardwareMap.get(DcMotor.class, "rightBack");

        public enum[]

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void driveByEncoder(double direction, double power, double distanse){
        switch(leftFront.getCurrentPosition() != distanse){

            case FORWARD:
                setPower(power);
                break;
            case BACK:
                setPower(-power);
                break;
            case RIGHT:
                setPower()

        }



    }

    enum

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

    public void driveForward(double power, double distanse){
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
