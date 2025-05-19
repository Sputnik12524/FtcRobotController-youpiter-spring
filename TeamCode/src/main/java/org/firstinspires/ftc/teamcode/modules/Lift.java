package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



public class Lift {
    Servo liftClaw;
    DcMotor lift;

    static int TOP = 2800;
    static int FASTEN = 2125;
    static int BOARD = 885;
    static double LIFTCLOSE = 0.82;
    static double LIFTOPEN = 0.65;

    public enum RobotPosition{TOP, FASTEN, BOARD};

    public Lift(LinearOpMode linearOpMode) {
        liftClaw = linearOpMode.hardwareMap.get(Servo.class, "liftClaw");
        lift = linearOpMode.hardwareMap.get(DcMotor.class, "lift");
    }

    public void liftUp(double position, double power) {

        lift.setTargetPosition((int) position);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(power);
    }

    public void liftZero(double power) {

        lift.setPower(0);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void liftClose() {
        liftClaw.setPosition(LIFTCLOSE);
    }

    public void liftOpen() {
        liftClaw.setPosition(LIFTOPEN);
    }

    public void switchPosition(RobotPosition robotPosition, double power) {
        lift.setPower(power);
        double target;
        double TOP, FASTEN, BOARD;
        switch (robotPosition) {
            case TOP:
                target = 2430;
                break;
            case FASTEN:
                target = 1930;
                break;
            case BOARD:
                target = 700;
                break;

        }

    }
}