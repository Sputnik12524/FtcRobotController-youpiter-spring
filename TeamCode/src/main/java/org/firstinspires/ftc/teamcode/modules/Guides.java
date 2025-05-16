/*package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Dclift;

public class Guides {
    private LinearOpMode opMode;
    private Dclift lift;

    public Guides(LinearOpMode opMode, String guidesName) {
        this.opMode = opMode;
        lift = opMode.hardwareMap.get(Dclift.class, guidesName);

    }

    static final double precislow = 25;

    public void setPower(double power) {
        setPower(power);


    }
    double pos = lift.getCurrentPosition();


    public enum RobotPosition {TOP, FASTEN, BOARD, MAX, BASKET}

    public void SetPower(RobotPosition robotPosition, double power) {
        double pos, MAX, TOP, FASTEN, BASKET, BOARD;
        switch (robotPosition) {
            case BASKET:
                if (pos - precislow > BASKET || pos + precislow > BASKET) {
                    while (opMode.opModeIsActive() && (lift.getCurrentPosition() <= BASKET)) {
                        setPower(power);
                    }
                } else if (pos > BASKET) {
                    while (opMode.opModeIsActive() && (lift.getCurrentPosition() >= BASKET)) {
                        setPower(-power);
                    }
                }
                setPower(0);
                break;

            case MAX:
                while (opMode.opModeIsActive() && (lift.getCurrentPosition() <= MAX)) {
                    setPower(power);
                }
                setPower(0);
                break;

            case TOP:
                if (pos < TOP) {
                    while (opMode.opModeIsActive() && (lift.getCurrentPosition() <= TOP)) {
                        setPower(power);
                    }
                } else if (pos > TOP) {
                    while (opMode.opModeIsActive() && (lift.getCurrentPosition() >= TOP)) {
                        ;
                        setPower(-power);
                    }
                }
                break;

            case FASTEN:
                if (pos < FASTEN) {
                    while (opMode.opModeIsActive() && (lift.getCurrentPosition() <= FASTEN)) {
                        ;
                        setPower(power);
                    }
                } else if (pos > FASTEN) {
                    while (opMode.opModeIsActive() && (lift.getCurrentPosition() >= FASTEN))
                        ;
                    setPower(-power);
                }
                setPower(0);
                break;

            case BOARD:
                if (pos < BOARD) {
                    while (opMode.opModeIsActive() && (lift.getCurrentPosition() <= BOARD)) {
                        ;
                        setPower(power);
                    }
                } else if (pos > BOARD) {
                    setPower(0);
                    while (opMode.opModeIsActive() && (lift.getCurrentPosition() >= BOARD))
                        ;
                    setPower(-power);
                }
                setPower(0);
                break;
        }
    }
}


dt
public void DRIVER_THE_ENCODORES() {
        setModes(Dclift.RunMode.STOP_AND_RESET_ENCODER);
        setModes(Dclift.RunMode.RUN_USING_ENCODER);
    }

    public void DRIVE_BY_ENCODORES(RobotDirection robotDirection, double distance, double power) {
        setModes(Dclift.RunMode.STOP_AND_RESET_ENCODER);
        setModes(Dclift.RunMode.RUN_USING_ENCODER);
        if (leftFront.getPower() > 0) {
            while (opModeIsActive() && leftFront.getCurrentPosition() < distance * PULSES_PER_CM) {
                setPower(robotDirection, power);
            }
            setPower(0);
        } else {
            while (opModeIsActive() && leftFront.getCurrentPosition() > -distance * PULSES_PER_CM) {
                setPower(robotDirection, power);
            }
            setPower(0);
        }

    }

    ackage org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Dclift;
import com.qualcomm.robotcore.hardware.DcliftSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Guides2 {
    private LinearOpMode opMode;
    private Dclift lift;
    private Telemetry telemetry;


    final double a = 3;


    public void setPower(double power) {
        lift.setPower(power);
    }

    public double getCurrentPosition() {

        return lift.getCurrentPosition();
    }

    public void Telemetry(){
        telemetry.addData("Подьем", lift.getCurrentPosition());
        telemetry.update();
    }



    public Guides2(LinearOpMode opMode, String guidesName) {
        this.opMode = opMode;
        this.telemetry = opMode.telemetry;
        lift = opMode.hardwareMap.get(Dclift.class, guidesName);
        lift.setMode(Dclift.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(Dclift.RunMode.RUN_USING_ENCODER);
        lift.setZeroPowerBehavior(Dclift.ZeroPowerBehavior.BRAKE);
        lift.setDirection(DcliftSimple.Direction.REVERSE);

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
                target = 0;


        }

        //Через while сравниваем позицию мотора подъёмника с позицией, в которой должен оказаться подъёмник с учётом погрешности (15 тиков)
        while (lift.getCurrentPosition() > target + precislow || lift.getCurrentPosition() < target - precislow) {
            while (lift.getCurrentPosition() >= target + precislow) {
                lift.setPower(power);
                Telemetry();

            }
            while (lift.getCurrentPosition() <= target - precislow) {
                lift.setPower(power);
                Telemetry();


            }
            //Остановка мотора подъёмника,после того, как мотор оказался в нужном нам положении
                lift.setPower(0);

//Вывод телеметрии



        }
        lift.setPower(0);




    }

    public enum RobotPosition {TOP, FASTEN, BOARD, MAX, BASKET}


}






*/

