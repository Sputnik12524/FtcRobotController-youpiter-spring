package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config


@TeleOp(name = "ProportionalControllerTest", group = "Robot")
public class ProportionalControllerTest extends LinearOpMode {
    DcMotor motor;
    public static double target = 2000;
    public static double KP = 0.1;
    public static double error = 0;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "motor");


        FtcDashboard dashboard = FtcDashboard.getInstance();

        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while (opModeIsActive()) {

            error = (target - motor.getCurrentPosition());
            motor.setPower(KP * error);
            dashboardTelemetry.addData("Current position -", motor.getCurrentPosition());
            dashboardTelemetry.addData("Target position - ", target);
            dashboardTelemetry.addData("Error - ", target - motor.getCurrentPosition());
            dashboardTelemetry.update();

        }

    }


}
