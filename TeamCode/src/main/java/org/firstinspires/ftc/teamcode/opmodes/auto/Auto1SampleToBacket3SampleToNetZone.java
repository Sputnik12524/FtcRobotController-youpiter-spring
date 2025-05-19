package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.modules.Guides2.RobotPosition.BASKET;
import static org.firstinspires.ftc.teamcode.modules.Guides2.RobotPosition.BOARD;
import static org.firstinspires.ftc.teamcode.modules.Guides2.RobotPosition.FASTEN;
import static org.firstinspires.ftc.teamcode.modules.Guides2.RobotPosition.TOP;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous(name = "Auto1SampleToBox3SampleNetZone", group = "Robot")
public class Auto1SampleToBacket3SampleToNetZone extends LinearOpMode {
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotor leftFront;
    DcMotor lift;
    DcMotor suspension;
    IMU imu;
    RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
    RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
    RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection);
    //Перевод тиков в метрическую систему
    static final double ENCODER_PULSES = 537.7;
    static final double PI = 3.1415926535;
    static final double WHEELS_DIAMETER = 9.6;
    static final double AXIS = 4;
    static final double LIFT_CM = ENCODER_PULSES / (AXIS * PI);
    static final double PULSES_PER_CM = ENCODER_PULSES / (PI * WHEELS_DIAMETER);
    //Мощности на колесную базу
    static final double TURN = 0.25;
    static final double POWER = 0.3; //подьем
    static final double SLOW = 0.1;
    static final double FAST = 0.7;
    static final double ALING_POWER = 0.5;
    //Мощность подьемника
    static final double FORWARD = 0.3;
    // Позиции подьемника
    static final double AWAY = 1500;
    static int MAX = 4550;
    static int TOP = 2830;
    static int FASTEN = 2150;
    static int BOARD = 885;
    static int BASKET = 4400;
    //Обнуление позиции направляющей
    double pos = 0;
    //Задание серво мотора
    Servo liftClaw;
    //Позиции проезда колесной базы
    public static double CENTRE_SUB = 25;//56
    public static double DELIVERY = 12;
    public static double DELIVERY2 = 30;
    public static double CENTRE2 = 11;
    public static double LONG_FAST = 120;
    public static double LONG_FAST2 = 110;
    public static double SPESIMEN = 25;
    public static double SPESIMEN2 = 9;
    public static double ALING = 35;
    public static double PARK = 100;
    public static double CLOSE_liftClaw = 0.82;
    public static double OPEN_liftClaw = 0.65;
    public static double UP = 0.5;
    public static double FAST_UP = 0.6;
    public static double HANDING = 0.5;

    //Задание IMU
    public double getDegrees() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }

    /*
    1) Проезд вперед от борта до подводного аппарата (CENTRE_SUB)
    2) Поднятие подьемника до верхнего отсека (TOP)
    3) Проезд вперед (DELIVERY)
    4) Опускание подьеммника (FASTEN)
    5) Раскрытие клешни (OPEN_liftClaw)
    6) Опускание подьемника (BOARD)
    7) Отьезд назад(-CENTRE2)
    8) Поворот на 90 по часовой стрелке
    9) Проезд вперед (LONG_FAST)
    10) Поворот на 90 по часовой стрелке
    11) Проезд вперед (SPESIMEN)
    12) Закрытие клешни (CLOSE_liftClaw)
    13) Поднятие направляющей (TOP)
    14) Проезд назад (-SPESIMEN)
    15) Поворот на 90 по часовой
    16) Проезд вперед (LONG_FAST)
    17) Поворот на 90 по часовой
    18) Выравнивание о борт (ALING)
    19) Проезд вперед от борта до подводного аппарата (CENTRE_SUB)
    20) Поднятие подьемника до верхнего отсека (TOP)
    21) Проезд вперед (DELIVERY)
    22) Опускание подьеммника (FASTEN)
    23) Раскрытие клешни (OPEN_liftClaw)
    24) Отьезд назад (CENTRE2)
    25) Опускание направляющей (BOARD)
    26) Проезд вправо (PARK)
     */
    public void runOpMode() {
        //Задание моторов и серво моторов
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        lift = hardwareMap.get(DcMotor.class, "lift");
        suspension = hardwareMap.get(DcMotor.class, "suspension");
        liftClaw = hardwareMap.get(Servo.class, "liftClaw");
        liftClaw.setPosition(0.82);

        // Устанавливаем режим для всех моторов на остановку и сброс энкодеров
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        suspension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Устанавливаем режим для всех моторов на использование эн
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        suspension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientation));
        imu.resetYaw();
        // По умолчанию в какую сторону крутятся колёса.
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        suspension.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

//1) Проезд вперед от борта до подводного аппарата (CENTRE_SUB)





        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < CENTRE_SUB * PULSES_PER_CM) {
            leftFront.setPower(FORWARD);
            rightFront.setPower(FORWARD);
            leftBack.setPower(FORWARD);
            rightBack.setPower(FORWARD);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

//2) Поднятие подьемника до верхнего отсека (TOP)
        while (pos <= BASKET) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(-FAST_UP);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(-0.025);



//3) Проезд вперед (DELIVERY)
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < 24 * PULSES_PER_CM) {
            leftFront.setPower(0.5);
            rightFront.setPower(0.5);
            leftBack.setPower(0.5);
            rightBack.setPower(0.5);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);



        //5) Раскрытие клешни

        liftClaw.setPosition(OPEN_liftClaw);



        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -40 * PULSES_PER_CM) {
            leftFront.setPower(-0.5);
            rightFront.setPower(-0.5);
            leftBack.setPower(-0.5);
            rightBack.setPower(-0.5);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

//6) Опускание подьемника (0)
        while (pos >= 50) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(FAST_UP);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(0);
//7) Отьезд вправо(CENTRE2)
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > 10 * PULSES_PER_CM) {
            leftFront.setPower(0.5);
            rightFront.setPower(-0.5);
            leftBack.setPower(-0.5);
            rightBack.setPower(0.5);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        while (getDegrees() >= -85) {
            telemetry.addData("Degrees", getDegrees());
            telemetry.update();
            leftFront.setPower(TURN);
            rightFront.setPower(-TURN);
            leftBack.setPower(TURN);
            rightBack.setPower(-TURN);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        imu.resetYaw();
        telemetry.addData("Degrees", getDegrees());
        telemetry.update();

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -30 * PULSES_PER_CM) {
            leftFront.setPower(-0.5);
            rightFront.setPower(-0.5);
            leftBack.setPower(-0.5);
            rightBack.setPower(-0.5);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < 100 * PULSES_PER_CM) {
            leftFront.setPower(0.5);
            rightFront.setPower(0.5);
            leftBack.setPower(0.5);
            rightBack.setPower(0.5);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -25 * PULSES_PER_CM) { //35
            leftFront.setPower(-POWER);
            rightFront.setPower(POWER);
            leftBack.setPower(POWER);
            rightBack.setPower(-POWER);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -100 * PULSES_PER_CM) {
            leftFront.setPower(-0.6);
            rightFront.setPower(-0.6);
            leftBack.setPower(-0.6);
            rightBack.setPower(-0.6);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < 15 * PULSES_PER_CM) {
            leftFront.setPower(0.5);
            rightFront.setPower(-0.5);
            leftBack.setPower(-0.5);
            rightBack.setPower(0.5);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -DELIVERY * PULSES_PER_CM) {
            leftFront.setPower(-POWER);
            rightFront.setPower(-POWER);
            leftBack.setPower(-POWER);
            rightBack.setPower(-POWER);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -20 * PULSES_PER_CM) {
            leftFront.setPower(-0.5);
            rightFront.setPower(0.5);
            leftBack.setPower(0.5);
            rightBack.setPower(-0.5);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < 100 * PULSES_PER_CM) {
            leftFront.setPower(0.5);
            rightFront.setPower(0.5);
            leftBack.setPower(0.5);
            rightBack.setPower(0.5);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -25 * PULSES_PER_CM) {
            leftFront.setPower(-POWER);
            rightFront.setPower(POWER);
            leftBack.setPower(POWER);
            rightBack.setPower(-POWER);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -80 * PULSES_PER_CM) {
            leftFront.setPower(-0.6);
            rightFront.setPower(-0.6);
            leftBack.setPower(-0.6);
            rightBack.setPower(-0.6);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < 80 * PULSES_PER_CM) {
            leftFront.setPower(0.5);
            rightFront.setPower(0.5);
            leftBack.setPower(0.5);
            rightBack.setPower(0.5);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -30 * PULSES_PER_CM) {
            leftFront.setPower(-POWER);
            rightFront.setPower(POWER);
            leftBack.setPower(POWER);
            rightBack.setPower(-POWER);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -130 * PULSES_PER_CM) {
            leftFront.setPower(-0.6);
            rightFront.setPower(-0.6);
            leftBack.setPower(-0.6);
            rightBack.setPower(-0.6);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

    }
}


