package org.firstinspires.ftc.teamcode.controller_test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;

@Config
public class ReleControllerTest extends LinearOpMode {
    public static double TARGET = 100;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        if (isStopRequested()) return;
        if (motor.getCurrentPosition() < TARGET) {
            motor.setPower(1);
        } else if (motor.getCurrentPosition() > TARGET) {
            motor.setPower(-1);
        } else if (motor.getCurrentPosition() == TARGET) {
            motor.setPower(0);
        }
    }
}
