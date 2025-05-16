package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveTrain {
    private LinearOpMode opMode;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private Telemetry telemetry;
    IMU imu;
    static final double ENCODER_PULSES = 537.7;
    static final double PI = 3.1415926535;
    static final double WHEELS_DIAMETER = 9.6;
    static final double AXIS = 4;
    static final double LIFT_CM = ENCODER_PULSES / (AXIS * PI);
    static final double PULSES_PER_CM = ENCODER_PULSES / (PI * WHEELS_DIAMETER);


    public DriveTrain(LinearOpMode opMode) {
        this.opMode = opMode;
        telemetry = opMode.telemetry;
        imu = opMode.hardwareMap.get(IMU.class, "imu");
        leftFront = opMode.hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = opMode.hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = opMode.hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = opMode.hardwareMap.get(DcMotor.class, "rightBack");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public enum RobotDirection {FORWARD, BACK, CLOCKWISE, COUNTCLOCKWISE, LEFT, RIGHT, FORWARDLEFT, FORWARDRIGHT, BACKLEFT, BACKRIGHT, STOP}

    public void setPowers(double lf, double rf, double lb, double rb) {
        leftFront.setPower(lf);
        rightFront.setPower(rf);
        leftBack.setPower(lb);
        rightBack.setPower(rb);

    }

    public void setPowers(double power) {
        setPowers(power, power, power, power);
    }

    public void DriveByEncoder(RobotDirection direction, double power, double distanse) {
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        switch (direction) {
            case FORWARD:
                setPowers(power);
                break;
            case BACK:
                setPowers(-power);
                break;
            case LEFT:
                setPowers(-power, power, power, -power);
                break;
            case RIGHT:
                setPowers(power, -power, -power, power);
                break;
            case CLOCKWISE:
                setPowers(power, -power, power, -power);
                break;
            case COUNTCLOCKWISE:
                setPowers(-power, power, -power, power);
                break;
            case FORWARDLEFT:
                setPowers(0, power, power, 0);
                break;
            case FORWARDRIGHT:
                setPowers(power, 0, 0, power);
                break;
            case BACKLEFT:
                setPowers(-power, 0, 0, -power);
                break;
            case BACKRIGHT:
                setPowers(0, -power, -power, 0);
                break;
            case STOP:
                setPowers(0, 0, 0, 0);
                break;
            default:
                setPowers(0);
        }
        while (Math.abs(leftFront.getCurrentPosition()) < distanse * PULSES_PER_CM) {
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

    }

    public void setMode(DcMotor.RunMode mode) {

        leftFront.setMode(mode);
        rightFront.setMode(mode);
        leftBack.setMode(mode);
        rightBack.setMode(mode);

    }

    public void setPower(double forward, double turn, double side) {
        leftFront.setPower((-forward + turn + side) * 0.5);
        rightFront.setPower((-forward - turn - side) * 0.5);
        leftBack.setPower((-forward + turn - side) * 0.5);
        rightBack.setPower((-forward - turn + side) * 0.5);


    }

    public void setPowerFast(double forward, double turn, double side) {
        leftFront.setPower((-forward + turn + side) * 1);
        rightFront.setPower((-forward - turn - side) * 1);
        leftBack.setPower((-forward + turn - side) * 1);
        rightBack.setPower((-forward - turn + side) * 1);


    }



    private IMU.Parameters parameters = new IMU.Parameters(
            new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                    RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));

    public double getHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    public void resetYaw() {
        imu.resetYaw();
    }

    public void addTelemetry(){
        telemetry.addData("Градусы", getHeading());
        telemetry.update();
    }

    public void TurnRight(double degrees, double power) {

        while (getHeading() > -degrees) {
            telemetry.addData("Degrees", getHeading());
            telemetry.update();
            leftFront.setPower(power);
            rightFront.setPower(-power);
            leftBack.setPower(power);
            rightBack.setPower(-power);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        resetYaw();
        telemetry.addData("Degrees", getHeading());
        telemetry.update();
    }

        /*if (degrees > 0) {
            while (getHeading() > degrees) {
                leftFront.setPower(power);
                rightFront.setPower(-power);
                leftBack.setPower(power);
                rightBack.setPower(-power);
            }

        }
        else{
            while (getHeading() < degrees) {
                leftFront.setPower(-power);
                rightFront.setPower(power);
                leftBack.setPower(-power);
                rightBack.setPower(power);
            }*/



}


