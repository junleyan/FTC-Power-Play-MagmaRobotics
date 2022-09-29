package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystem.drive.MecanumDrive;
// C:\Users\yanju\navx-micro


@TeleOp(name="Test Teleop", group="OpMode")
public class MainTeleop extends OpMode {

    private MecanumDrive drive = new MecanumDrive();

    private Servo leftServo;
    private Servo rightServo;

    @Override
    public void init() {
        this.drive.init(hardwareMap);

        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");

        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void loop() {

        double leftPower = gamepad1.left_stick_y;
        double rightPower = gamepad1.right_stick_y;

        if (gamepad1.y) {
            rightServo.setPosition(1.0);
            leftServo.setPosition(0.0);
        } else if (gamepad1.x) {
            rightServo.setPosition(0.0);
            leftServo.setPosition((1.0));
        }

        this.drive.set(leftPower, rightPower);

        telemetry.addData("Status", "Initialized");

    }

    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped");
    }
}