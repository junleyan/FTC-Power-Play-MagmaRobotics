package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystem.Claws;
import org.firstinspires.ftc.teamcode.subsystem.Lift;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@TeleOp(name="Test Teleop", group="OpMode")
public class MainTeleop extends OpMode {

    private MecanumDrive drive = new MecanumDrive();
    private Lift lift = new Lift();
    private Claws claws = new Claws();
    private SensorGroup sensor = new SensorGroup();


    @Override
    public void init() {
        this.drive.init(hardwareMap);
        this.lift.init(hardwareMap);
        this.claws.init(hardwareMap);
        this.sensor.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void loop() {

        this.drive.setControl(gamepad1);
        this.lift.setControl(gamepad1);
        this.claws.setControl(gamepad1);

        telemetry.addData("Distance", this.sensor.getDistance());

    }

    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped");
    }
}