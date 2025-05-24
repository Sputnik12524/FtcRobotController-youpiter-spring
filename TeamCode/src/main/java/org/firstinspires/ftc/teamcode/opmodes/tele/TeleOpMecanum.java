//package org.firstinspires.ftc.teamcode.opmodes.tele;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//
//@TeleOp(name =  "TeleOpMecanum", group = "Robot")
//public class TeleOpMecanum extends LinearOpMode {
//    DcMotor rightFront;
//    DcMotor leftBack;
//    DcMotor rightBack;
//    DcMotor leftFront;
//    DcMotor liftMotor;
//    //DcMotor sss;
//    DcMotor extensionMotor;
//    DcMotor suspension;
//    // DcMotor sss;
//    Servo liftClaw;
//    Servo turnServo;
//    Servo extensionClaw;
//    double forward;
//    double turn;
//    double side;
//    static final double POWER = 0.3;
//    static final double SLOW = 0.2;
//    static int MAX = 4000;
//    static int MAX2 = 6000;
//    static int MAX3 = 1950;
//    static int TOP = 2800;
//    static int FASTEN = 2125;
//    static int BOARD = 885;
//    double pos = 0;
//    double pos2 = 0;
//    double pos3 = 0;
//
//
//    public void runOpMode() {
//        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
//        //sss = hardwareMap.get(DcMotor.class, "sss");
//        extensionMotor = hardwareMap.get(DcMotor.class, "extensionMotor");
//        suspension = hardwareMap.get(DcMotor.class, "suspension");
//        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        extensionMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        extensionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        suspension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        suspension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        suspension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//
//        //sss.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        //sss.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        //sss.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//
//        liftClaw = hardwareMap.get(Servo.class, "liftClaw");
//        liftClaw.setPosition(0.82);
//        extensionClaw = hardwareMap.get(Servo.class, "extensionClaw");
//        extensionClaw.setPosition(0.7);
//        turnServo = hardwareMap.get(Servo.class, "turnServo");
//        turnServo.setPosition(0);
//
//
//
//        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
//        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
//        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
//        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
//
//        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
//        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
//        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        extensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        suspension.setDirection(DcMotorSimple.Direction.REVERSE);
//        //sss.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        waitForStart();
//        // Установка неприрывного движения с помощью gamepad
//        /*
//         * ТЗ: заменить все while на if
//         *  добавить else для передвижения направляющей по стику, в котором устанавливается мощность 0
//         *
//         * */
//        boolean aState = false;
//        boolean slow = true;
//
//
//        while (opModeIsActive()) {
//            forward = gamepad1.left_stick_y;
//            turn = gamepad1.right_stick_x;
//            side = gamepad1.left_stick_x;
//
//            pos = liftMotor.getCurrentPosition();
//            pos2 = suspension.getCurrentPosition();
//            pos3 = extensionMotor.getCurrentPosition();
//            if (gamepad2.dpad_up && (pos <= TOP)) {
//                liftMotor.setPower(POWER);
//                pos = liftMotor.getCurrentPosition();
//            } else if (gamepad2.dpad_down && (pos >= 0)) {
//                liftMotor.setPower(-POWER);
//                pos = liftMotor.getCurrentPosition();
//            } else if (gamepad2.dpad_right && (pos <= BOARD)) {
//                liftMotor.setPower(POWER);
//                pos = liftMotor.getCurrentPosition();
//            } else if (gamepad2.dpad_left && (pos <= FASTEN)) {
//                liftMotor.setPower(POWER);
//                pos = liftMotor.getCurrentPosition();
//            } else {
//                if ( (pos <= MAX)) {
//                    liftMotor.setPower(gamepad2.left_stick_y);
//                    pos = liftMotor.getCurrentPosition();
//                } else {
//                    liftMotor.setPower(0);
//                }
//            }
//
//            /*if ((pos2 >= 0) && (pos2 <= MAX2)) {
//                sss.setPower(-gamepad2.right_stick_y);
//                pos2 = sss.getCurrentPosition();
//            } else if (pos2>=MAX2){
//                sss.setPower(-SLOW);
//            }
//            else if (pos2 < 0){
//                sss.setPower(SLOW);
//            }*/
//
//
//            extensionMotor.setPower(-gamepad2.right_stick_y);
//            suspension.setPower(-gamepad1.left_trigger);
//
//
//
//            if (!aState && gamepad1.a) {
//                slow = !slow;
//            }
//            aState = gamepad1.a;
//            if (slow) {
//                leftFront.setPower((-forward + turn + side) * 0.5);
//                rightFront.setPower((-forward - turn - side) * 0.5);
//                leftBack.setPower((-forward + turn - side) * 0.5);
//                rightBack.setPower((-forward - turn + side) * 0.5);
//            } else {
//                leftFront.setPower((-forward + turn + side) * 1);
//                rightFront.setPower((-forward - turn - side) * 1);
//                leftBack.setPower((-forward + turn - side) * 1);
//                rightBack.setPower((-forward - turn + side) * 1);
//            }
//
//            if(gamepad2.right_bumper){
//                turnServo.setPosition(0);
//            }
//            if(gamepad2.left_bumper){
//                turnServo.setPosition(0.39);
//            }
//
//            if (gamepad2.a) {
//                liftClaw.setPosition(0.82);
//            }
//            if (gamepad2.b) {
//                liftClaw.setPosition(0.65);
//            }
//            if (gamepad2.x){
//                extensionClaw.setPosition(0.16);
//            }
//            if (gamepad2.y) {
//                extensionClaw.setPosition(0.35);
//            }
//
//            telemetry.addData("КлешняЗахват", extensionClaw.getPosition());
//            telemetry.addData("Захват", extensionMotor.getCurrentPosition());
//            telemetry.addData("Подьем", liftMotor.getCurrentPosition());
//            telemetry.addData("КлешняПодьем", liftClaw.getPosition());
//            telemetry.update();
//
//        }
//    }}
