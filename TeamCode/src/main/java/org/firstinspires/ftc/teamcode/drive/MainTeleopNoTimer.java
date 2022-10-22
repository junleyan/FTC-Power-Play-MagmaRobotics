package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Lift;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@TeleOp(name="Main Teleop Mode (No Timer)", group="OpMode")
public class MainTeleopNoTimer extends OpMode {


    private MecanumDrive drive = new MecanumDrive();
    private Lift lift = new Lift();
    private Claw claw = new Claw();
    private SensorGroup sensor = new SensorGroup();


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void loop() {
        this.drive.setControl(gamepad1);
        this.lift.setControl(gamepad2);
        this.claw.setControl(gamepad2);

        telemetry.addData("Status", "Enabled");
        telemetry.addData("Distance", this.sensor.getDistance());
        telemetry.addData("Color", this.sensor.getRed());
    }


    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped");
    }

}