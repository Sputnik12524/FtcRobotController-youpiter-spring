package org.firstinspires.ftc.teamcode.modules.archive_modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoWithPosition {
    private LinearOpMode opMode;
    private final double OPEN_POSITION;
    private final double CLOSE_POSITION;
    private Servo servo;

    public ServoWithPosition(LinearOpMode opMode, double OPEN_POSSITION, double CLOSE_POSSITION, String servoName) {
        this.opMode = opMode;
        this.OPEN_POSITION = OPEN_POSSITION;
        this.CLOSE_POSITION = CLOSE_POSSITION;
        servo = opMode.hardwareMap.get(Servo.class, servoName);
    }


    public void OPEN(double b) {
        servo.setPosition(b);

    }
    public void CLOSE(double a) {

        servo.setPosition(a);
    }
}

