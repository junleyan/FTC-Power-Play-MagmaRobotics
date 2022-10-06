package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystem.Lift;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;


@TeleOp(name="Test Teleop", group="OpMode")
public class MainTeleop extends OpMode {

    private MecanumDrive drive = new MecanumDrive();
    private Lift lift = new Lift();


    @Override
    public void init() {
        this.drive.init(hardwareMap);
        this.lift.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void loop() {

        if (gamepad1.a) {
            lift.up();
        } else if(gamepad1.b) {
            lift.down();
        } else {
            lift.stop();
        }
        this.drive.set(gamepad1.left_stick_y, gamepad1.right_stick_y);

        telemetry.addData("Status", "Initialized");

    }

    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped");
    }
}