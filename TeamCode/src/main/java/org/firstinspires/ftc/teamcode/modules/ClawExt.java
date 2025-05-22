package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawExt {

    Servo servoCE;
    LinearOpMode opMode;

    public static double POS_OPEN = 0;
    public static double POS_CLOSE = 1;

    private boolean stateOfClawPos = false;

    public ClawExt(LinearOpMode opMode) {
        this.opMode = opMode;
        servoCE = opMode.hardwareMap.get(Servo.class, "ClawExt");
    }

    public void openClaw() {
        servoCE.setPosition(POS_OPEN);
    }

    public void closeClaw() {
        servoCE.setPosition(POS_CLOSE);
    }

    public void switchPosClaw() {
        if (!stateOfClawPos) {
            servoCE.setPosition(POS_OPEN);
            stateOfClawPos = true;
        } else {
            servoCE.setPosition(POS_CLOSE);
            stateOfClawPos = false;
        }
    }
}
