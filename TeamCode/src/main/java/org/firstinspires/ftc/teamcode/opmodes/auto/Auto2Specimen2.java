//package org.firstinspires.ftc.teamcode.opmodes.auto;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.IMU;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
//
//@Autonomous(name = "Auto2Specimen2", group = "Robot")
//public class Auto2Specimen2 extends LinearOpMode {
//    DcMotor rightFront;
//    DcMotor leftBack;
//    DcMotor rightBack;
//    DcMotor leftFront;
//    DcMotor lift;
//    DcMotor extension;
//    IMU imu;
//    Servo liftClaw;
//    Servo extensionClaw;
//
//    static final double ENCODER_PULSES = 537.7;
//    static final double PI = 3.1415926535;
//    static final double WHEELS_DIAMETER = 9.6;
//    static final double AXIS = 4;
//    static final double LIFT_CM = ENCODER_PULSES / (AXIS * PI);
//    static final double PULSES_PER_CM = ENCODER_PULSES / (PI * WHEELS_DIAMETER);
//
//    public double getDegrees() {
//        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
//        return orientation.getYaw(AngleUnit.DEGREES);
//    }
//    public void setMode(DcMotor.RunMode mode) {
//        leftFront.setMode(mode);
//        rightFront.setMode(mode);
//        leftBack.setMode(mode);
//        rightBack.setMode(mode);
//    }
//    /*public void setPowers(double lf, double rf, double lb, double rb) {
//        leftFront.setPower(lf);
//        rightFront.setPower(rf);
//        leftBack.setPower(lb);
//        rightBack.setPower(rb);
//
//    }
//
//    public void setPowers(double power) {
//        setPowers(power, power, power, power);
//    }*/
//
//    public void driveForward(double power, double distanse){
//        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        while (Math.abs(leftFront.getCurrentPosition()) < distanse * PULSES_PER_CM) {
//            leftFront.setPower(power);
//            rightFront.setPower(power);
//            leftBack.setPower(power);
//            rightBack.setPower(power);
//        }
//    }
//    public void runOpMode() {
//        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
//        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
//        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
//        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
//        lift = hardwareMap.get(DcMotor.class, "lift");
//        extension = hardwareMap.get(DcMotor.class, "extension");
//        liftClaw = hardwareMap.get(Servo.class, "liftClaw");
//        extensionClaw = hardwareMap.get(Servo.class, "extensionClaw");
//
//        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
//        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
//        lift.setDirection(DcMotorSimple.Direction.REVERSE);
//        extension.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        waitForStart();
//
//        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//    }}
//
