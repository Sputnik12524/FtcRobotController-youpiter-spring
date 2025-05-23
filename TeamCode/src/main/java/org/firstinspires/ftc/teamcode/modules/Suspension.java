package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Suspension {

    DcMotorEx suspMotor;
    LinearOpMode opMode;
    private double error;
    private static double kP = 0.1;
    private volatile double target = 0;
    private boolean isOnLimits = false;

    public static double POS_MIN = 0;
    public static double POS_MAX = 10;
    public Suspension(LinearOpMode opmode) {
        this.opMode = opmode;
        suspMotor = opmode.hardwareMap.get(DcMotorEx.class, "susMotor");
        suspMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        suspMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public double getCurrentPositionSuspension(){
        int stepsPerRevolution = 252;
        int D = 4;
        return D * Math.PI * suspMotor.getCurrentPosition() / stepsPerRevolution;
    }
    public class SuspensionController extends Thread{
        @Override
        public void run(){
            while(!isInterrupted()){
                error = target - getCurrentPositionSuspension();
                double power = error * kP;
                if (power > 1) {
                    power = 1;
                } else if (power < -1) {
                    power = -1;
                }

            }
        }
        public void movingWithLimits(double power) {
            if (getCurrentPositionSuspension() <= POS_MIN && power < 0) {
                suspMotor.setPower(0);
                isOnLimits = true;
            } else if (getCurrentPositionSuspension() >= POS_MAX && power > 0) {
                suspMotor.setPower(0);
                isOnLimits = true;
            } else {
                suspMotor.setPower(power);
                isOnLimits = false;
            }
        }
    }
    public void setTarget(double target) {
        this.target = target;
    }

}
