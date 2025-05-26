package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
@TeleOp (name="Alina_Rele",group = "Robot")
public class ReleControlerTest extends LinearOpMode {
    private DcMotor motor;
    public static double TARGET = 2000;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotor.class, "motor");
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while (opModeIsActive()){
            if (motor.getCurrentPosition() < TARGET) {
            motor.setPower(1);
        } else if (motor.getCurrentPosition() > TARGET) {
            motor.setPower(-1);
        } else {
            motor.setPower(0);
        }
            dashboardTelemetry.addData("Current position - ", motor.getCurrentPosition());
            dashboardTelemetry.addData("arget position - ", TARGET);
            dashboardTelemetry.addData("Eror -", TARGET - motor.getCurrentPosition());
            dashboardTelemetry.update();
    }

}}

