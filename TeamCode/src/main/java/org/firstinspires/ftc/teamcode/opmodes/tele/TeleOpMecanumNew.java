//package org.firstinspires.ftc.teamcode.opmodes.tele;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//@TeleOp(name =  "TeleOpMecanumNew", group = "Robot")
//public class TeleOpMecanumNew extends LinearOpMode {
//
//
//
//    double forward;
//    double turn;
//    double side;
//    static final double POWER = 0.3;
//    static final double SLOW = 0.2;
//    static final double OPEN_LIFTCLAW = 0.65;
//    static final double CLOSE_LIFTCLAW = 0.82;
//    static final double OPEN_EXTENSIONCLAW = 0.4;
//    static final double CLOSE_EXTENSIONCLAW = 0.65;
//    static final double CLOSE_TURNSERVO = 0.32;
//    static final double OPEN_TURNSERVO = 0;
//    static final double UP_TURNSERVO = 0.68;
//
//    static int MAX = 4000;
//    static int MAX2 = 6500;
//    //boolean MAX2S = 4000;
//
//    static int MAX3 = 1950;
//    static int TOP = 2800;
//    static int FASTEN = 2125;
//    static int BOARD = 885;
//    double pos = 0;
//    double pos2 = 0;
//    double pos3 = 0;
//    double pos4 = 0;
//
//
//    public void runOpMode() {
//        DriveTrain drivetrain = new DriveTrain(this);
//        ServoWithPosition extensionClaw = new ServoWithPosition(this, 0.88, 0.65, "extensionClaw");
//        ServoWithPosition liftClaw = new ServoWithPosition(this, 0.88, 0.65, "liftClaw");
//        ServoWithPosition turnServo = new ServoWithPosition(this, 0.88, 0.65, "turnServo");
//        Guides2 lift = new Guides2(this, "lift");
//        Guides2 extension = new Guides2(this, "extension");
//        Guides2 suspension = new Guides2(this, "suspension");
//
//
//
//        //telemetry.update();
//        liftClaw.OPEN(OPEN_LIFTCLAW);
//        extensionClaw.OPEN(CLOSE_EXTENSIONCLAW);
//        turnServo.OPEN(UP_TURNSERVO);
//
//
//        waitForStart();
//        /*        // Установка неприрывного движения с помощью gamepad
//         *//*
//         * ТЗ: заменить все while на if
//         *  добавить else для передвижения направляющей по стику, в котором устанавливается мощность 0
//         */
//
//        boolean aState = false;
//        boolean slow = true;
//        boolean hang = false;
//        boolean susp = true;
//
//
//        while (opModeIsActive()) {
//
//            if (pos <= MAX) {
//                lift.setPower(gamepad2.left_stick_y);
//                pos = lift.getCurrentPosition();
//            } else {
//                lift.setPower(0);
//            }
//
//
//
//              /*if (pos2 <= MAX2 ) {
//                suspension.setPower(-gamepad1.right_stick_y);
//                pos2 = suspension.getCurrentPosition();
//            } else {
//                suspension.setPower(0);
//            }*/
//
//
//
//
//            if(!hang && gamepad1.b ){
//                susp = !susp;
//
//            }
//            hang = gamepad1.b;
//
//            if(susp){
//                if(extension.getCurrentPosition() <= MAX3) {
//                    extension.setPower(-gamepad2.right_stick_y);
//                }else {
//                    extension.setPower(-0.3);
//                }
//            }
//            else{
//                if (pos2 <= MAX2 ) {
//                    suspension.setPower(-gamepad2.right_stick_y);
//                    pos2 = suspension.getCurrentPosition();
//                    extension.setPower(-0.1);
//                } else {
//                    suspension.setPower(0);
//                }
//
//            }
//
//
//
//            if (!aState && gamepad1.a) {
//                slow = !slow;
//            }
//            aState = gamepad1.a;
//
//            if(slow){
//                drivetrain.setPower(gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
//            }
//            else {
//                drivetrain.setPowerFast(gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
//            }
//            if (gamepad2.a) {
//                liftClaw.CLOSE(CLOSE_LIFTCLAW);
//            }
//            if (gamepad2.b) {
//                liftClaw.OPEN(OPEN_LIFTCLAW);
//            }
//            if (gamepad2.x){
//                extensionClaw.CLOSE(CLOSE_EXTENSIONCLAW);
//            }
//            if (gamepad2.y) {
//                extensionClaw.OPEN(OPEN_EXTENSIONCLAW);
//            }
//            if(gamepad2.right_bumper){
//                turnServo.CLOSE(CLOSE_TURNSERVO);
//            }
//            if(gamepad2.left_bumper){
//                turnServo.OPEN(OPEN_TURNSERVO);
//            }
//            if (gamepad2.dpad_up){
//                turnServo.OPEN(UP_TURNSERVO);
//            }
//            telemetry.addData("Захват", extension.getCurrentPosition());
//            telemetry.addData("Подьем", lift.getCurrentPosition());
//
//        }
//    }
//}
//
//
