package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Extension {

    DcMotorEx motorExt;
    LinearOpMode opMode;

    private double error;
    private static double kP = 0.1;
    private volatile double target = 0;

    public static double POS_MIN = 0;
    public static double POS_MAX = 10;

    private boolean isOnLimits = false;

    public Extension(LinearOpMode opMode) {
        this.opMode = opMode;
        motorExt = opMode.hardwareMap.get(DcMotorEx.class, "Ext");

        motorExt.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorExt.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    private double getCurrentPosExt() { //Надо будет переделать под плечо
        int stepsPerRevolution = 252;
        int D = 4;
        return D * Math.PI * motorExt.getCurrentPosition() / stepsPerRevolution;
    }

    public class LiftController extends Thread {

        @Override
        public void run() {
            motorExt.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            motorExt.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

            while (!isInterrupted()) {

                double error = target - getCurrentPosExt();
                double power = error * kP;

                if (power > 1) {
                    power = 1;
                } else if (power < -1) {
                    power = -1;
                }

                movingWithLimits(power);
            }
        }
        public void movingWithLimits(double power) {
            if (getCurrentPosExt() <= POS_MIN && power < 0) {
                motorExt.setPower(0);
                isOnLimits = true;
            } else if (getCurrentPosExt() >= POS_MAX && power > 0) {
                motorExt.setPower(0);
                isOnLimits = true;
            } else {
                motorExt.setPower(power);
                isOnLimits = false;
            }
        }
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getTarget() { return target; }
    public double getError() { return error; }
    public double getPower() { return motorExt.getPower(); }
    public double getCurrentPosition() { return motorExt.getCurrentPosition(); }
    public boolean isOnLimits() { return isOnLimits; }











//    public void ExtForward() {
//        while (motorExt.getCurrentPosition() < MAX_POS) {
//            motorExt.setPower(SPEED);
//        }
//        motorExt.setPower(0);
//    }
//    public void ExtBack() {
//        while (motorExt.getCurrentPosition() > MIN_POS) {
//            motorExt.setPower(-SPEED);
//        }
//        motorExt.setPower(0);
//    }


}
