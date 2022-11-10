package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Lift;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@TeleOp(name="Reset Hardwares", group="OpMode")
public class ResetHardwares extends OpMode {


    private MecanumDrive drive = new MecanumDrive();
    private Lift lift = new Lift();
    private Claw claw = new Claw();
    private SensorGroup sensor = new SensorGroup();


    @Override
    public void init() {
        this.drive.init(hardwareMap);
        this.lift.init(hardwareMap);
        this.claw.init(hardwareMap);
        this.sensor.init(hardwareMap);

        this.lift.down();

        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void loop() {

    }


    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped");
    }

}