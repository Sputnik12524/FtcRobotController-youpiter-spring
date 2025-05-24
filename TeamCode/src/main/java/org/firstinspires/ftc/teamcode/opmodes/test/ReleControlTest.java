package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
@TeleOp(name = "Rele")
public class ReleControlTest extends LinearOpMode {
    DcMotor motor;

    public static double TARGET = 2000;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotor.class, "motor");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        // while добавить для цикла
        while (opModeIsActive()) {
            if (motor.getCurrentPosition() < TARGET) {
                motor.setPower(1);
            } else if (motor.getCurrentPosition() > TARGET) {
                motor.setPower(-1);
            } else {
                motor.setPower(0);
            }
        }
    }
}
