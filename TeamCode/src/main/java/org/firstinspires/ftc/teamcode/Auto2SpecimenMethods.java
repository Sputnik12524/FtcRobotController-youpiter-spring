package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous(name = "Auto2Specimen", group = "Robot")
public class Auto2SpecimenMethods extends LinearOpMode {
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor upMotor;

    private Servo liftClaw;
    IMU imu;

    private RevTouchSensor limitSwitch;

    static final double PULSES = 537.7;
    static final double WHEELS_DIAMETER = 9.6;
    public final double PULSES_PER_CM = PULSES / (Math.PI * WHEELS_DIAMETER);

    public void runOpMode() {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        upMotor = hardwareMap.get(DcMotor.class, "lift");
        liftClaw = hardwareMap.get(Servo.class, "liftClaw");
        limitSwitch = hardwareMap.get(RevTouchSensor.class, "limitSwitch");


        RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection);

        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientation));


        imu.resetYaw();

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);

        upMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        upMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        /*
        /проезд в перёд до апарата
        /подём до верхнего отсека
        /опускание и отъезд назад
        /поворот и проезд до зоны наблюдения
        /поворот и взять образец
        /поднятие длифта поворот и проезд влево и выравнивание об борт
        /проезд прерёд и действия 1-3 строчку
        /проезд назад до борта и поехать вправо
         */
        driveStraight(0.5, 50);
        liftUp(2430, 0.3);
        driveStraight(0.5, 10);
        liftUp(1930, 0.3);
        liftClaw.setPosition(0.65);
        driveStraight(-0.5, 30);
        liftZero(-0.5);
        driveRotate(0.5, 110);
        driveStraight(0.5, 140);
        driveRotate(0.5, 70);
        driveStraight(0.5, 40);
        liftClaw.setPosition(0);
        driveStraight(0.5, 60);
        driveSide(0.5, 130);
        driveRotate(0.5, 180);
        driveStraight(-0.5, 40);
        driveStraight(0.5, 70);
        liftUp(2430, 0.3);
        driveStraight(0.5, 10);
        liftUp(1930, 0.3);
        liftClaw.setPosition(0.65);
        driveStraight(-0.5, 30);
        liftZero(-0.5);
        driveStraight(0.5, -70);
        driveSide(0.5, 150);




    }

    public void driveStraight(double speed, double distance) {


        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < PULSES_PER_CM * distance){

            leftFront.setPower(speed);
            rightFront.setPower(speed);
            leftBack.setPower(speed);
            rightBack.setPower(speed);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        sleep(300);
    }

    // направление по умолчанию вправо
    public void driveSide(double speed, double distance) {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < PULSES_PER_CM * distance) {
            leftFront.setPower(speed);
            rightFront.setPower(-speed);
            leftBack.setPower(-speed);
            rightBack.setPower(speed);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        sleep(300);
    }
    // без минуса по часовой
    public void driveRotate (double speed, double angle) {

        while (opModeIsActive() && Math.abs(getDegrees()) < angle) {
            imu.resetYaw();
            leftFront.setPower(speed);
            rightFront.setPower(speed);
            leftBack.setPower(-speed);
            rightBack.setPower(-speed);

        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        sleep(300);
    }

    public void liftUp(double position, double power) {

        upMotor.setTargetPosition((int) position);
        upMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        upMotor.setPower(power);

        while (opModeIsActive() && upMotor.isBusy()) {

            telemetry.addData("Позиция для мотора", upMotor.getCurrentPosition());
            telemetry.update();
        }




    }
    public void liftZero(double power) {
        while (opModeIsActive() && !limitSwitch.isPressed()){
            upMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            upMotor.setPower(power);
        }
        upMotor.setPower(0);
        upMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }


    public double getDegrees() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }
}


