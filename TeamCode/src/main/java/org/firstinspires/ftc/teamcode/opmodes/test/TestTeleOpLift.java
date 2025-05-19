package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "TestTeleopLift")
public class TestTeleOpLift extends LinearOpMode {
    DcMotor liftMotor;



    public void runOpMode(){
        liftMotor = hardwareMap.get(DcMotor.class, "lift");
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.dpad_up) {
                liftMotor.setTargetPosition(1600);
                liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftMotor.setPower(0.3);

                telemetry.addData("Позиция мотора", liftMotor.getCurrentPosition());
                telemetry.update();
            }



        }

    }






}
