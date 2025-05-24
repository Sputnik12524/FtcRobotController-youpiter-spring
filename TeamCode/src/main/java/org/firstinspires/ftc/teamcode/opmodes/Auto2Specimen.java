package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous(name = "AutoSpecimen2", group = "Robot")
public class Auto2Specimen extends LinearOpMode {
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotor leftFront;
    DcMotor lift;
    DcMotor extension;
    IMU imu;

    RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
    RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
    RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection);

    public void setModes(DcMotor.RunMode mode) {
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


    //Перевод тиков в метрическую систему
    static final double ENCODER_PULSES = 537.7;
    static final double PI = 3.1415926535;
    static final double WHEELS_DIAMETER = 9.6;
    static final double AXIS = 4;
    static final double LIFT_CM = ENCODER_PULSES / (AXIS * PI);
    static final double PULSES_PER_CM = ENCODER_PULSES / (PI * WHEELS_DIAMETER);
    //Мощности
    static final double TURN = 0.25;
    static final double POWER = 0.3; //подьем
    static final double SLOW = 0.1;
    static final double FAST = 1;//0.8
    static final double ALING_POWER = 0.4;
    static final double OPEN = 0.65;
    static final double CLOSE = 0.82;
    static final double FORWARD = 0.3;
    public static double UP = 0.8;
    public static double FAST_UP = 0.6;
    //Позиции подьемника
    static final double AWAY = 1500;
    static int MAX = 4500;
    static int TOP = 2430;
    static int FASTEN = 1950;
    static int BOARD = 700;
    //Обнуление позиции направляющей
    double pos = 0;
    //Задание серво мотора
    Servo liftClaw;
    Servo extensionClaw;
    //Позиции проезда колесной базы
    public static double CENTRE_SUB = 59;//56
    public static double DELIVERY = 9;
    public static double CENTRE2 = 58;
    public static double LONG_FAST = 120;
    public static double SPESIMEN = 25;
    public static double SPESIMEN2 = 9;
    public static double ALING = 60;
    public static double PARK = 100;
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
    5) Раскрытие клешни (OPEN)
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
    23) Раскрытие клешни (OPEN)
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
        extension = hardwareMap.get(DcMotor.class, "extension");
        liftClaw = hardwareMap.get(Servo.class, "liftClaw");
        extensionClaw = hardwareMap.get(Servo.class, "extensionClaw");
        //Закрытие клешни на подьеме
        liftClaw.setPosition(0.82);
        //imu
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientation));
        imu.resetYaw();
        // По умолчанию в какую сторону крутятся колёса.
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        extension.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
//1) Проезд вперед от борта до поeddдводного аппарата (CENTRE_SUB)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

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
        while (opModeIsActive() && pos <= TOP) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(-FAST_UP);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(0);
//3) Проезд вперед (DELIVERY)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < DELIVERY * PULSES_PER_CM) {
            leftFront.setPower(SLOW);
            rightFront.setPower(SLOW);
            leftBack.setPower(SLOW);
            rightBack.setPower(SLOW);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
//4) Опускание подьеммника (FASTEN)
        while (opModeIsActive() && pos >= FASTEN) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(UP);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(0);
//5) Раскрытие клешни (OPEN)
        liftClaw.setPosition(OPEN);
//6) Опускание подьемника (BOARD)
        while (opModeIsActive() && pos >= BOARD) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(UP);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(0);
//7) Отьезд назад(CENTRE2)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -CENTRE2 * PULSES_PER_CM) {
            leftFront.setPower(-0.4);
            rightFront.setPower(-0.4);
            leftBack.setPower(-0.4);
            rightBack.setPower(-0.4);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
//8) Поворот на 90 по часовой стрелке
        while (getDegrees() > -80) {
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

//9) Проезд вперед (LONG_FAST)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < LONG_FAST * PULSES_PER_CM) {
            leftFront.setPower(FAST);
            rightFront.setPower(FAST);
            leftBack.setPower(FAST);
            rightBack.setPower(FAST);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

//10) Поворот на 90 по часовой стрелке
        while (getDegrees() > -85) {
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

        //Поднятие направляющей(BOARD)
        while (opModeIsActive() && pos >= BOARD) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(POWER);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(0);
        //Проезд вперед(SPESIMEN)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < SPESIMEN * PULSES_PER_CM) {
            leftFront.setPower(POWER);
            rightFront.setPower(POWER);
            leftBack.setPower(POWER);
            rightBack.setPower(POWER);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
//12) Закрытие клешни (CLOSE_liftClaw)
        liftClaw.setPosition(CLOSE);
//13) Поднятие направляющей (AWAY)
        while (opModeIsActive() && pos <= AWAY) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(-POWER);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(0);
//14) Проезд назад (-SPESIMEN)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -SPESIMEN * PULSES_PER_CM) {
            leftFront.setPower(-POWER);
            rightFront.setPower(-POWER);
            leftBack.setPower(-POWER);
            rightBack.setPower(-POWER);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
//15) Поворот на 90 по часовой
        while (getDegrees() > -90) {
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

        sleep(500);

        imu.resetYaw();
        telemetry.addData("Degrees", getDegrees());
        telemetry.update();
//16) Проезд вперед (LONG_FAST)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < 100 * PULSES_PER_CM) {
            leftFront.setPower(FAST);
            rightFront.setPower(FAST);
            leftBack.setPower(FAST);
            rightBack.setPower(FAST);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

//17) Поворот на 90 по часовой
        while (getDegrees() > -85) {
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
//18) Выравнивание о борт (ALING)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -ALING * PULSES_PER_CM) {
            leftFront.setPower(-ALING_POWER);
            rightFront.setPower(-ALING_POWER);
            leftBack.setPower(-ALING_POWER);
            rightBack.setPower(-ALING_POWER);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

//19) Проезд вперед от борта до подводного аппарата (CENTRE_SUB)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < 57 * PULSES_PER_CM) {
            leftFront.setPower(FORWARD);
            rightFront.setPower(FORWARD);
            leftBack.setPower(FORWARD);
            rightBack.setPower(FORWARD);
        }
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
//20) Поднятие подьемника до верхнего отсека (TOP)
        while (opModeIsActive() && pos <= TOP) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(-UP);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(0);
//21) Проезд вперед (DELIVERY)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < 4 * PULSES_PER_CM) {
            leftFront.setPower(SLOW);
            rightFront.setPower(SLOW);
            leftBack.setPower(SLOW);
            rightBack.setPower(SLOW);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
//22) Опускание подьеммника (FASTEN)
        while (opModeIsActive() && pos >= FASTEN) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(UP);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(0);
//23) Раскрытие клешни (OPEN)
        liftClaw.setPosition(OPEN);
//24) Отьезд назад (CENTRE2)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() > -20 * PULSES_PER_CM) {
            leftFront.setPower(0);
            rightFront.setPower(-FAST);
            leftBack.setPower(-FAST);
            rightBack.setPower(0);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

//25) Опускание направляющей (0)
        while (opModeIsActive() && pos >= 0) {
            telemetry.addData("Tics", lift.getCurrentPosition());
            telemetry.update();
            lift.setPower(UP);
            pos = lift.getCurrentPosition();
        }
        lift.setPower(0);
//26) Проезд вправо (PARK)
        setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setModes(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive() && leftFront.getCurrentPosition() < PARK * PULSES_PER_CM) {
            leftFront.setPower(FAST);
            rightFront.setPower(-FAST);
            leftBack.setPower(-FAST);
            rightBack.setPower(FAST);
        }

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }

}


