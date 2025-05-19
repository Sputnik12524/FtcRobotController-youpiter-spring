package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "extensionTest", group = "Robot")
public class extensionTest extends LinearOpMode {
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotor leftFront;
    //DcMotor sss;
    DcMotor extension;
    // DcMotor sss;
    DcMotor lift;
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
    double num = 0;


    public void runOpMode() {
        lift = hardwareMap.get(DcMotor.class, "lift");
        //sss = hardwareMap.get(DcMotor.class, "sss");
        extension = hardwareMap.get(DcMotor.class, "extension");
        extensionClaw = hardwareMap.get(Servo.class, "extensionClaw");
        turnServo = hardwareMap.get(Servo.class, "turnServo");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //sss.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //sss.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //sss.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.update();
//72

        //extensionClaw = hardwareMap.get(Servo.class, "extensionClaw");
        //extensionClaw.setPosition(0);

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        extension.setDirection(DcMotorSimple.Direction.REVERSE);
        //sss.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        // Установка неприрывного движения с помощью gamepad
        /*
         * ТЗ: заменить все while на if
         *  добавить else для передвижения направляющей по стику, в котором устанавливается мощность 0
         *
         * */
        boolean a = false;


        while (opModeIsActive()) {

            lift.setPower( -gamepad1.right_stick_x);




            telemetry.addData("Клешня",lift.getCurrentPosition());
            telemetry.update();
        }
    }

}


