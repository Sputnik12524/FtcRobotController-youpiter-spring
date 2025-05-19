package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Extension {
    DcMotor extensionMotor;
    Servo extensionClaw;
    Servo turnServo;

    public static final double EXTENSION_SERVO_CLOSE = 0.36;
    public static final double EXTENSION_SERVO_OPEN = 0.18;
    public static final double TURN_SERVO_UP = 0.18;
    public static final double TURN_SERVO_DOWN = 0;
    public static final double TURN_SERVO_FORWARD = 0.34;
    public static final double MAX = 10000;

    public Extension(LinearOpMode linearOpMode) {
        extensionMotor = linearOpMode.hardwareMap.get(DcMotorEx.class, "extensionMotor");
        extensionClaw = linearOpMode.hardwareMap.get(Servo.class, "extensionClaw");
        turnServo = linearOpMode.hardwareMap.get(Servo.class, "turnServo");
    }

    public void extensionMotor(double power) {
        if (extensionMotor.getCurrentPosition() >= 0 && extensionMotor.getCurrentPosition() < MAX) {
            extensionMotor.setPower(power);
        } else {
            extensionMotor.setPower(-power);
        }
    }

    public void extensionClawClose() {
        extensionClaw.setPosition(EXTENSION_SERVO_CLOSE);
    }

    public void extensionClawOpen() {
        extensionClaw.setPosition(EXTENSION_SERVO_OPEN);
    }

    public void turnServoUp() {
        turnServo.setPosition(TURN_SERVO_UP);
    }

    public void turnServoDown() {
        turnServo.setPosition(TURN_SERVO_DOWN);
    }

    public void turnServoForward() {
        turnServo.setPosition(TURN_SERVO_FORWARD);
    }
}