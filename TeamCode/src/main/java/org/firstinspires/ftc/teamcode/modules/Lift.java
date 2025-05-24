package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Lift {
    private Servo liftClaw;
    private DcMotor lift;

    private static final int TOP = 2800;
    private static final int FASTEN = 2125;
    private static final int BOARD = 885;
    private static final double CLOSECLAW = 0.82;
    private static final double OPENCLAW = 0.65;

    public enum RobotPosition {TOP, FASTEN, BOARD}

    ;

    public Lift(LinearOpMode linearOpMode) {
        liftClaw = linearOpMode.hardwareMap.get(Servo.class, "liftClaw");
        lift = linearOpMode.hardwareMap.get(DcMotor.class, "lift");
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