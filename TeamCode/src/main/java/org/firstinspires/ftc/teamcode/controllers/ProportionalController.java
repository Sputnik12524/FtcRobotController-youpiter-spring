package org.firstinspires.ftc.teamcode.controllers;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
@TeleOp(name = "Proportional Controller", group = "Robot")
public class ProportionalController extends LinearOpMode {

    DcMotor motor;

    private double target = 0;
    public static double kP = 0.1;

    public static double NEW_TARGET = 100;

    private boolean btnX = false;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        waitForStart();

        while (opModeIsActive()) {

            double error = target - motor.getCurrentPosition();
            double power = error * kP;

            if (power > 1) {
                power = 1;
            } else if (power < -1) {
                power = -1;
            }

            motor.setPower(power);

            if (gamepad1.x && !btnX) {
                target = NEW_TARGET;
            }

            telemetry.addData("Желаемое положение:", target);
            dashboardTelemetry.addData("Желаемое положение:", target);
            telemetry.addData("Реальное положение:", motor.getCurrentPosition());
            dashboardTelemetry.addData("Реальное положение:", motor.getCurrentPosition());
            telemetry.addData("Ошибка:", error);
            dashboardTelemetry.addData("Ошибка:", error);
            telemetry.addData("Мощность:", power);
            telemetry.update();
            dashboardTelemetry.update();

        }
    }
}
