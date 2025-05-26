package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
@TeleOp(name = "Proportion")
public class Proportion_test extends LinearOpMode {
    DcMotor motor;

    public static double TARGET = 2000;
    public static double kP = 0.1;
    public static double error;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotor.class, "motor");

        FtcDashboard dashboard = FtcDashboard.getInstance();

        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while (opModeIsActive()) {
            error = TARGET - motor.getCurrentPosition();
            motor.setPower(kP * error);

            dashboardTelemetry.addData("Current position - ", motor.getCurrentPosition());
            dashboardTelemetry.addData("Target position", TARGET);
            dashboardTelemetry.addData("Error", TARGET - motor.getCurrentPosition());
            dashboardTelemetry.update();
        }
    }
}
