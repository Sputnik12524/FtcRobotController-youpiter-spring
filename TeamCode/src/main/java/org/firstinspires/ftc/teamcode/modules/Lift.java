package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class Lift {

    DcMotorEx motorLift;
    DigitalChannel magneticSensor;
    LinearOpMode opMode;

    private double error;
    private static double kP = 0.01;
    private volatile double target = 0;

    public static double POS_MAX = 100;
    private static double POS_MIN = 0;
    private static double POS_FOR_SPECIMEN = 50;
    private static double POS_FOR_BASKET = 70;
    private static double POS_FOR_SIDE = 20;


    private boolean isOnLimits = false;


    public Lift(LinearOpMode opMode) {
        motorLift = opMode.hardwareMap.get(DcMotorEx.class, "Lift");

        motorLift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorLift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    private double getCurrentPosLift() {
        int stepsPerRevolution = 252;
        int D = 4;
        return D * Math.PI * motorLift.getCurrentPosition() / stepsPerRevolution;
    }
    public boolean isMagneting() { return !magneticSensor.getState(); }


    public class LiftController extends Thread {

        @Override
        public void run() {
            motorLift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            motorLift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

            while (!isInterrupted()) {

                double error = target - getCurrentPosLift();
                double power = error * kP;

                if (power > 1) {
                    power = 1;
                } else if (power < -1) {
                    power = -1;
                }

                movingWithLimits(power);
            }
        }
    }

    public void movingWithLimits(double power) {
        if (isMagneting() && power < 0) {
            motorLift.setPower(0);
            isOnLimits = true;
        } else if (getCurrentPosLift() <= POS_MAX && power > 0) {
            motorLift.setPower(0);
            isOnLimits = true;
        } else {
            motorLift.setPower(power);
            isOnLimits = false;
        }

    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getTarget() { return target; }
    public double getError() { return error; }
    public double getPower() { return motorLift.getPower(); }
    public double getCurrentPosition() { return motorLift.getCurrentPosition(); }
    public boolean isOnLimits() { return isOnLimits; }


}
