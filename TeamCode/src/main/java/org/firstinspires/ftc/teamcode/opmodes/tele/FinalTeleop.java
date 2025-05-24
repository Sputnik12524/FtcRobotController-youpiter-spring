//package org.firstinspires.ftc.teamcode.opmodes.tele;
//
//import com.qualcomm.hardware.rev.RevTouchSensor;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//
//@TeleOp( group = "Robot")
//public class FinalTeleop extends LinearOpMode {
//    DcMotor rightFront;
//    DcMotor leftBack;
//    DcMotor rightBack;
//    DcMotor leftFront;
//
//    Servo liftClaw;
//    Servo extensionClaw;
//    Servo turnServo;
//
//    RevTouchSensor limitSwitch;
//    DcMotor lift;
//    DcMotor suspension;
//    DcMotor extension;
//
//    double forward;
//    double turn;
//    double side;
//    static final double POWER = 0.3;
//    double EXTENSION_CLAW_OPEN = 0.18;
//    double EXTENSION_CLAW_CLOSE = 0.36;
//    double LIFT_CLAW_OPEN = 0.65;
//    double LIFT_CLAW_CLOSE = 0.82;
//    double TURN_SERVO_DOWN = 0;
//    double TURN_SERVO_UP = 0.67;
//    double TURN_SERVO_FORWARD = 0.34;
//    static final double SLOW = 0.2;
//    double precision = 100;
//    double LIFT_MAX = 4550;
//    double EXTENSION_MAX = 10000;
//    double SUSPENSION_MAX = 6500;
//    double TOP = 2430;
//    double FASTEN = 1950;
//    double BOARD = 700;
//    double BASKET = 4250;
//    double LIFT_POWER = 0.4;
//    double pos = 0;
//
//
//    public void runOpMode() {
//        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
//        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
//        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
//        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
//
//        extension = hardwareMap.get(DcMotor.class, "extension");
//        suspension = hardwareMap.get(DcMotor.class, "suspension");
//        lift = hardwareMap.get(DcMotor.class, "lift");
//
//        liftClaw = hardwareMap.get(Servo.class, "liftClaw");
//        extensionClaw = hardwareMap.get(Servo.class, "extensionClaw");
//        turnServo = hardwareMap.get(Servo.class, "turnServo");
//
//        limitSwitch = hardwareMap.get(RevTouchSensor.class, "limitSwitch");
//
//        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
//        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
//        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
//        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
//
//        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
//        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
//
//        lift.setDirection(DcMotorSimple.Direction.REVERSE);
//        suspension.setDirection(DcMotorSimple.Direction.REVERSE);
//        extension.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        suspension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        suspension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        suspension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        extension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        extension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        extension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        liftClaw.setPosition(LIFT_CLAW_CLOSE);
//        extensionClaw.setPosition(EXTENSION_CLAW_CLOSE);
//        turnServo.setPosition(TURN_SERVO_UP);
//
//        boolean x2State = false;
//        boolean a2State = false;
//        boolean aState = false;
//        boolean slow = true;
//        boolean dpadUpState = false;
//        boolean dpadDownState = false;
//        boolean dpadRightState = false;
//        boolean dpadLeftState = false;
//        boolean isClawPressed = false;
//        boolean isClosed = true;
//        boolean bState = false;
//        boolean hang = false;
//        boolean susp = true;
//        boolean l1 = false;
//        boolean l2 = true;
//        boolean e1 = false;
//        boolean e2 = true;
//        boolean t1 = false;
//        boolean t2 = true;
//        boolean tab = false;
//
//
//        waitForStart();
//        // Установка неприрывного движения с помощью gamepad    1
//        /*
//         * ТЗ: заменить все while на if
//         *  добавить else для передвижения направляющей по стику, в котором устанавливается мощность 0
//         *
//         * */
//
//
//        while (opModeIsActive()) {
//            forward = gamepad1.left_stick_y;
//            turn = gamepad1.right_stick_x;
//            side = gamepad1.left_stick_x;
//
//
//            if (gamepad2.dpad_up) {
//                lift.setTargetPosition(2000);
//                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                lift.setPower(0.8);
//
//                telemetry.addData("Позиция мотора", lift.getCurrentPosition());
//                telemetry.update();
//            }
//
//            if (gamepad2.dpad_left) {
//                while (opModeIsActive() && !limitSwitch.isPressed()) {
//                    lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                    lift.setPower(-0.8);
//                }
//                lift.setPower(0);
//                lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//            }
//            if (gamepad2.dpad_right) {
//                lift.setTargetPosition(1650);
//                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                lift.setPower(0.8);
//
//                telemetry.addData("Позиция мотора", lift.getCurrentPosition());
//                telemetry.update();
//            }
//            if (gamepad2.a) {
//                liftClaw.setPosition(LIFT_CLAW_OPEN);
//            }
//            if (gamepad2.b) {
//                liftClaw.setPosition(LIFT_CLAW_CLOSE);
//            }
////            if (gamepad2.x) {
////                extensionClaw.setPosition(EXTENSION_CLAW_OPEN);
////            }
//            if (gamepad2.y) {
//                extensionClaw.setPosition(EXTENSION_CLAW_CLOSE);
//            }
//            if (gamepad2.left_bumper) {
//                turnServo.setPosition(TURN_SERVO_DOWN);
//            }
//            if (gamepad2.right_bumper) {
//                turnServo.setPosition(TURN_SERVO_FORWARD);
//            }
//            if (gamepad2.dpad_down) {
//                turnServo.setPosition(TURN_SERVO_UP);
//            }
//            if (!x2State && gamepad2.x) {
//                if (extensionClaw.getPosition() == LIFT_CLAW_OPEN) {
//                    extensionClaw.setPosition(EXTENSION_CLAW_CLOSE);
//                } else {
//                    extensionClaw.setPosition(EXTENSION_CLAW_OPEN);
//                }
//            }
//            x2State = gamepad2.x;
//
//            // dpadUpState = gamepad2.dpad_up;
//            // dpadDownState = gamepad2.dpad_down;
//            // dpadRightState = gamepad2.dpad_right;
//            // dpadLeftState = gamepad2.dpad_left;
//
//            //if ((lift.getCurrentPosition() >= 0) && (lift.getCurrentPosition() <= LIFT_MAX)) {
//            //   lift.setPower(-gamepad2.left_stick_y);
//            // } else if (lift.getCurrentPosition() >= LIFT_MAX) {
//            //     lift.setPower(-SLOW);
//            //  } else if (lift.getCurrentPosition() < 0) {
//            //      lift.setPower(SLOW);
//            // }
//
//
//
//
//
//          /*  if(gamepad2.a && !tab){
//                if(liftClaw.getPosition() == 0.65){
//                    liftClaw.setPosition(0.82);
//                }
//                else{
//                    liftClaw.setPosition(0.65);
//                }
//            }
//
//
//
//
//            if ( gamepad2.a &&  !isClawPressed) {
//                if (isClosed){
//                    liftClaw.setPosition(LIFT_CLAW_OPEN);
//                    isClosed = false;
//                } else {
//                    liftClaw.setPosition(LIFT_CLAW_CLOSE);
//                    isClosed = true;
//                }
//            }
//
//
//
//            if (bState && gamepad2.b) {
//                if (turnServo.getPosition() == TURN_SERVO_FORWARD) {
//                    turnServo.setPosition(TURN_SERVO_DOWN);
//                } else {
//                    turnServo.setPosition(TURN_SERVO_FORWARD);
//                }
//            }
//
//            if(!l1 && gamepad2.a){
//                l2 = !l2;
//           }
//            if(l2){
//                liftClaw.setPosition(LIFT_CLAW_OPEN);
//            }
//            else{
//                liftClaw.setPosition(LIFT_CLAW_CLOSE);
//            }*/
//
//
//            if (!hang && gamepad1.b) {
//                susp = !susp;
//            }
//            hang = gamepad1.b;
//            if (susp) {
//                if (extension.getCurrentPosition() <= EXTENSION_MAX) {
//                    extension.setPower(-gamepad2.right_stick_y);
//                }
//            } else {
//                extension.setPower(-0.1);
//                if ((suspension.getCurrentPosition() >= 0) && (suspension.getCurrentPosition() <= SUSPENSION_MAX)) {
//                    suspension.setPower(-gamepad2.right_stick_y);
//                } else if (suspension.getCurrentPosition() >= SUSPENSION_MAX) {
//                    suspension.setPower(-SLOW);
//                } else if (suspension.getCurrentPosition() < 0) {
//                    suspension.setPower(SLOW);
//                }
//            }
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
//            //Телеметрия переворот сервы
//            telemetry.addData("Переворот", turnServo.getPosition());
//            //Телеметрия горизонтальной клешни
//            telemetry.addData("Горизонт_Клешня", extensionClaw.getPosition());
//            //Телеметрия горизонтального выдвижения
//            telemetry.addData("Горизонт", extension.getCurrentPosition());
//            //Телеметрия подьема
//            telemetry.addData("Лифт", lift.getCurrentPosition());
//            //Тедеметрия подвеса
//            telemetry.addData("Подвес", suspension.getCurrentPosition());
//            //Телеметрия лифт клешни
//            telemetry.addData("Лифт_Клешня", liftClaw.getPosition());
//            telemetry.update();
//
//            tab = gamepad2.a;
//            isClawPressed = gamepad2.a;
//        }
//    }
//}