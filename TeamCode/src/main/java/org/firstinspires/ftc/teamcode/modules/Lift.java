package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

public class Lift {
    private Servo liftClaw;
    private DcMotor lift;
    //private DigitalChannel magnetikLimitSwith;

    public static double TOP = 2800;
    public static double FASTEN = 2125;
    public static double BOARD = 885;
    public static double CLOSECLAW = 0.82;
    public static double OPENCLAW = 0.65;
    public static double MIN_POSITION = 0;
    public static double MAX_POSITION = 100;

    public static double kp = 0.1;
    private double error;
    public volatile double target;

    public LiftControler liftControler = new LiftControler();

    public Lift(LinearOpMode linearOpMode) {
        liftClaw = linearOpMode.hardwareMap.get(Servo.class, "liftClaw");
        lift = linearOpMode.hardwareMap.get(DcMotor.class, "lift");
        //magnetikLimitSwith = linearOpMode.hardwareMap.get(DigitalChannel.class, "magnetikLimitSwith");
    }

    public class LiftControler extends Thread {

        @Override
        public void run() {
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            while (!isInterrupted()) {
                error = target - lift.getCurrentPosition();
                double power = kp * error;
                lift.setPower(power);
            }
        }
    }

    public void setTarget(double target) {
        this.target = target;
    }

//    public boolean isMagetion() {
//        return !magnetikLimitSwith.getState();
//    }

    public void movingLiftWithLimits(double power) {
        if ((power < 0 || lift.getCurrentPosition() >= MAX_POSITION && power > 0)) {
            lift.setPower(0);
        } else {
            lift.setPower(power);
        }
    }

    public void liftUp(double position, double power) {

        lift.setTargetPosition((int) position);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(power);
    }

    public void liftSetPositionZero(double power) {

        lift.setPower(0);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void closeClaw() {
        liftClaw.setPosition(CLOSECLAW);
    }

    public void openClaw() {
        liftClaw.setPosition(OPENCLAW);
    }


}
