package org.firstinspires.ftc.teamcode.controllers;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
@TeleOp (name="Rele")
public class ReleControllerTest extends LinearOpMode {
    public static double TARGET = 2000;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");

        FtcDashboard dashboard = FtcDashboard.getInstance();

        Telemetry dashboardTelemetry = dashboard.getTelemetry();;

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if (motor.getCurrentPosition() < TARGET) {
                motor.setPower(1);
            } else if (motor.getCurrentPosition() > TARGET) {
                motor.setPower(-1);
            } else if (motor.getCurrentPosition() == TARGET) {
                motor.setPower(0);
            }
            dashboardTelemetry.addData("Current position - ", motor.getCurrentPosition());
            dashboardTelemetry.addData("Target position - ", TARGET);
            dashboardTelemetry.update();
            dashboardTelemetry.update();
        }

    }
}
