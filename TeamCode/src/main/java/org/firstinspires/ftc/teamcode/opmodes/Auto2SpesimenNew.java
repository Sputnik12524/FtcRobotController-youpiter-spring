package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.modules.DriveTrain.RobotDirection.BACK;
import static org.firstinspires.ftc.teamcode.modules.DriveTrain.RobotDirection.BACKRIGHT;
import static org.firstinspires.ftc.teamcode.modules.DriveTrain.RobotDirection.CLOCKWISE;
import static org.firstinspires.ftc.teamcode.modules.DriveTrain.RobotDirection.FORWARD;
import static org.firstinspires.ftc.teamcode.modules.Guides2.RobotPosition.BASKET;
import static org.firstinspires.ftc.teamcode.modules.Guides2.RobotPosition.BOARD;
import static org.firstinspires.ftc.teamcode.modules.Guides2.RobotPosition.FASTEN;
import static org.firstinspires.ftc.teamcode.modules.Guides2.RobotPosition.TOP;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.modules.DriveTrain;
import org.firstinspires.ftc.teamcode.modules.Guides2;
import org.firstinspires.ftc.teamcode.modules.ServoWithPosition;
import org.firstinspires.ftc.teamcode.modules.Gyroscope;

@Autonomous(name = "AutoSpesimen2New", group = "Robot")
public class Auto2SpesimenNew extends LinearOpMode {



    static final double POWER = 0.25;
    static final double AWAY = 1500;
    static int MAX = 4500;
    static final double SLOW = 0.1;
    static final double FAST = 0.8;
    static final double ALING_POWER = 0.4;
    static final double UP = 0.3;
    final double BORD_BSUB = 58;
    final double BSUB_SUB = 4;
    final double SUB_CENTRE = 40;
    final double CENTRE_BSPESIMEN = 100;
    final double SPESIMEN_BSPESIMEN = 20;
    final double BSPESIMEN_SPESIMEN = 15;
    final double ALING = 40;
    final double SUB_PARK = 100;
    static final double OPEN_LIFTCLAW = 0.65;
    static final double CLOSE_LIFTCLAW = 0.82;
    static final double OPEN_EXTENSIONCLAW = 0.16;
    static final double CLOSE_EXTENSIONCLAW = 0.35;
    static final double CLOSE_TURNSERVO = 0.39;
    static final double OPEN_TURNSERVO = 0;

    public void runOpMode() {
        DriveTrain drivetrain = new DriveTrain(this);
        ServoWithPosition extensionClaw = new ServoWithPosition(this, 0.88, 0.65, "extensionClaw");
        ServoWithPosition liftClaw = new ServoWithPosition(this, 0.88, 0.65, "liftClaw");
        ServoWithPosition turnServo = new ServoWithPosition(this, 0.88, 0.65, "turnServo");
        Guides2 lift = new Guides2(this, "lift");
        Guides2 extension = new Guides2(this, "extension");

        drivetrain.resetYaw();
        liftClaw.CLOSE(CLOSE_LIFTCLAW);




        waitForStart();

        drivetrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drivetrain.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Подьем", lift.getCurrentPosition());
        telemetry.update();

        drivetrain.DriveByEncoder(FORWARD, POWER, BORD_BSUB);
        lift.setPower(TOP, -UP);
        drivetrain.DriveByEncoder(FORWARD, SLOW, BSUB_SUB);
        lift.setPower(FASTEN, POWER);
        liftClaw.OPEN(OPEN_LIFTCLAW);
        drivetrain.DriveByEncoder(BACK, POWER, SUB_CENTRE);
        sleep(500);
        drivetrain.TurnRight(90, POWER);
        drivetrain.DriveByEncoder(FORWARD, FAST, CENTRE_BSPESIMEN);
        sleep(500);
        drivetrain.TurnRight(90, POWER);
        lift.setPower(BOARD, POWER);
        drivetrain.DriveByEncoder(FORWARD, POWER, BSPESIMEN_SPESIMEN);
        liftClaw.CLOSE(CLOSE_LIFTCLAW);
        lift.setPower(TOP, -POWER);
        drivetrain.DriveByEncoder(BACK, POWER, SPESIMEN_BSPESIMEN);
        sleep(500);
        drivetrain.TurnRight(90, POWER);
        drivetrain.DriveByEncoder(FORWARD, POWER, CENTRE_BSPESIMEN);
        sleep(500);
        drivetrain.TurnRight(90, POWER);
        drivetrain.DriveByEncoder(BACK, ALING_POWER, ALING);
        drivetrain.DriveByEncoder(FORWARD, POWER, BORD_BSUB);
        lift.setPower(TOP, -UP);
        drivetrain.DriveByEncoder(FORWARD, SLOW, BSUB_SUB);
        lift.setPower(FASTEN, POWER);
        liftClaw.OPEN(OPEN_LIFTCLAW);
        drivetrain.DriveByEncoder(BACKRIGHT, POWER, SUB_PARK);


        /*drivetrain.DriveByEncoder(FORWARD, POWER, BORD_BSUB);
        lift.setPower(TOP, -UP);
        drivetrain.DriveByEncoder(FORWARD, SLOW, BSUB_SUB);
        lift.setPower(FASTEN, POWER);
        liftClaw.OPEN(OPEN_LIFTCLAW);
        drivetrain.DriveByEncoder(BACK, POWER, SUB_CENTRE);
        drivetrain.TurnByDegrees(90, POWER);
        drivetrain.DriveByEncoder(FORWARD, FAST, CENTRE_BSPESIMEN);
        drivetrain.TurnByDegrees(90, POWER);
        lift.setPower(BOARD, POWER);
        drivetrain.DriveByEncoder(FORWARD, POWER, BSPESIMEN_SPESIMEN);
        liftClaw.CLOSE(CLOSE_LIFTCLAW);
        lift.setPower(TOP, -POWER);
        drivetrain.DriveByEncoder(BACK, POWER, SPESIMEN_BSPESIMEN);
        drivetrain.TurnByDegrees(90, POWER);
        drivetrain.DriveByEncoder(FORWARD, POWER, CENTRE_BSPESIMEN);
        drivetrain.TurnByDegrees(90, POWER);
        drivetrain.DriveByEncoder(BACK, ALING_POWER, ALING);
        drivetrain.DriveByEncoder(FORWARD, POWER, BORD_BSUB);
        lift.setPower(TOP, -UP);
        drivetrain.DriveByEncoder(FORWARD, SLOW, BSUB_SUB);
        lift.setPower(FASTEN, POWER);
        liftClaw.OPEN(OPEN_LIFTCLAW);
        drivetrain.DriveByEncoder(BACKRIGHT, POWER, SUB_PARK);*/



    }
}






