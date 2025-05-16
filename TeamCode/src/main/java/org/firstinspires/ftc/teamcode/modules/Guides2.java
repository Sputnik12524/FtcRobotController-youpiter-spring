package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Guides2 {
    private LinearOpMode opMode;
    private DcMotor motor;
    private Telemetry telemetry;


    final double a = 3;

    public void Up(double position, double power){
        motor.setTargetPosition((int) position);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(power);
        while(opMode.opModeIsActive()&& motor.isBusy()){
            telemetry.addData("Позиция", motor.getCurrentPosition());
            telemetry.update();
        }
    }


    public void setPower(double power) {
        motor.setPower(power);
    }

    public double getCurrentPosition() {

        return motor.getCurrentPosition();
    }

    public void Telemetry(){
        telemetry.addData("Подьем", motor.getCurrentPosition());
        telemetry.update();
    }



    public Guides2(LinearOpMode opMode, String guidesName) {
        this.opMode = opMode;
        this.telemetry = opMode.telemetry;
        motor = opMode.hardwareMap.get(DcMotor.class, guidesName);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    static final double precislow = 15;

    // Через switch устанавливаем название и сколько подъёмник должен поднять в тиках
    public void setPower(RobotPosition robotPosition, double power) {
        setPower(power);
        double target;
        double pos, MAX, TOP, FASTEN, BOARD, BACKET;

        switch (robotPosition) {
            case BASKET:
                target = 4200;
                break;
            case TOP:
                target = 2430;
                break;
            case FASTEN:
                target = 1930;
                break;
            case BOARD:
                target = 700;
                break;

            default:
                target = 50;


        }

        //Через while сравниваем позицию мотора подъёмника с позицией, в которой должен оказаться подъёмник с учётом погрешности (15 тиков)
        while (motor.getCurrentPosition() > target + precislow || motor.getCurrentPosition() < target - precislow) {

            while (motor.getCurrentPosition() >= target + precislow) {
                motor.setPower(power);
                Telemetry();


            }
            while (motor.getCurrentPosition() <= target - precislow) {
                motor.setPower(power);
                Telemetry();





            }
            //Остановка мотора подъёмника,после того, как мотор оказался в нужном нам положении
            motor.setPower(0);

//Вывод телеметрии



        }
        motor.setPower(0);




    }

    public enum RobotPosition {TOP, FASTEN, BOARD, MAX, BASKET}


}





