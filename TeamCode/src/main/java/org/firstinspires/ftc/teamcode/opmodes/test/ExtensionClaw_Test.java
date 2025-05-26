package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ExtensionClaw_Test", group = "Robot")
public class ExtensionClaw_Test extends LinearOpMode {
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotor leftFront;
    DcMotor liftMotor;
    //DcMotor sss;
    DcMotor extensionMotor;
    // DcMotor sss;
    Servo liftClaw;
    Servo turnServo;
    Servo extensionClaw;
    double forward;
    double turn;
    double side;
    static final double POWER = 0.3;
    static final double SLOW = 0.2;
    static int MAX = 4000;
    static int MAX2 = 6000;
    static int MAX3 = 3000;
    static int TOP = 2800;
    static int FASTEN = 2125;
    static int BOARD = 885;
    double pos = 0;
    double pos2 = 0;
    double pos3 = 0;


    public void runOpMode() {
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        //sss = hardwareMap.get(DcMotor.class, "sss");
        extensionMotor = hardwareMap.get(DcMotor.class, "extensionMotor");
        extensionClaw = hardwareMap.get(Servo.class, "extensionClaw");
        turnServo = hardwareMap.get(Servo.class, "turnServo");
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extensionMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extensionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //sss.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //sss.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //sss.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.update();
//72

        liftClaw = hardwareMap.get(Servo.class, "liftClaw");
        liftClaw.setPosition(0.65);
        turnServo.setPosition(0);

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        extensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //sss.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        // Установка неприрывного движения с помощью gamepad
        /*
         * ТЗ: заменить все while на if
         *  добавить else для передвижения направляющей по стику, в котором устанавливается мощность 0
         *
         * */
        boolean aState = false;
        boolean slow = true;


        while (opModeIsActive()) {
            turnServo.setPosition(gamepad2.right_stick_y);
            telemetry.addData("Клешня", turnServo.getPosition());
            telemetry.update();
        }

    }
}


