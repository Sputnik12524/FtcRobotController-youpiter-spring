package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.ClawExt;
import org.firstinspires.ftc.teamcode.modules.ClawLift;

@TeleOp(name = "Claw Test", group = "Robot")
public class ClawTest extends LinearOpMode {
    ClawLift cl;
    ClawExt ce;

    public void runOpMode() {
        ClawExt ce = new ClawExt(this);
    }
}
