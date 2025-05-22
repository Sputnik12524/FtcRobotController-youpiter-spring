package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawLift {

    Servo servoCL;
    LinearOpMode opMode;

    public static double POS_OPEN = 0;
    public static double POS_CLOSE = 1;

    private boolean stateOfClawPos = false;

    public ClawLift(LinearOpMode opMode) {
        this.opMode = opMode;
        servoCL = opMode.hardwareMap.get(Servo.class, "ClawLift");
    }

    public void openClaw() {
        servoCL.setPosition(POS_OPEN);
    }

    public void closeClaw() {
        servoCL.setPosition(POS_CLOSE);
    }

    public void switchPosClaw() {
        if (!stateOfClawPos) {
            servoCL.setPosition(POS_OPEN);
            stateOfClawPos = true;
        } else {
            servoCL.setPosition(POS_CLOSE);
            stateOfClawPos = false;
        }
    }
}
