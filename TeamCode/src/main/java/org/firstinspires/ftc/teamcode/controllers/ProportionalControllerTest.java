package org.firstinspires.ftc.teamcode.controllers;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
@TeleOp (name="Proportional Controller")
public class ProportionalControllerTest extends LinearOpMode {
    public static double TARGET = 2000;
    public static double kP = 0.1;
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");

        FtcDashboard dashboard = FtcDashboard.getInstance();

        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            double power = (TARGET - motor.getCurrentPosition() * kP);
            motor.setPower(power);
            dashboardTelemetry.addData("Current position - ", motor.getCurrentPosition());
            dashboardTelemetry.addData("Target position - ", TARGET);
            dashboardTelemetry.addData("Error -", TARGET - motor.getCurrentPosition());
            dashboardTelemetry.update();
        }

    }
}
