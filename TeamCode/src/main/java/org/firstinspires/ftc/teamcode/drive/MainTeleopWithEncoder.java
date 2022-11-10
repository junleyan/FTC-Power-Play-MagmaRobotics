package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Lift;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@TeleOp(name="Main Teleop Mode With Lift Encoder", group="OpMode")
public class MainTeleopWithEncoder extends OpMode {


    private ElapsedTime runtime = new ElapsedTime();
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

        this.lift.downWithEncoder();

        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void loop() {
        if (this.runtime.seconds() < Constants.Timer.teleopTime) {
            this.drive.setControl(gamepad1);
            this.lift.setControl(gamepad2, true);
            this.claw.setControl(gamepad2);

            telemetry.addData("Status", "Enabled");
            telemetry.addData("Time Remaining", this.runtime.seconds());
            telemetry.addData("Lift Position", this.lift.getPosition());
            telemetry.addData("Distance", this.sensor.getDistance());
            telemetry.addData("Detected Zone", this.sensor.getZone());
            telemetry.addData("Color Red", this.sensor.getRed());
            telemetry.addData("Color Green", this.sensor.getGreen());
            telemetry.addData("Color Blue", this.sensor.getBlue());
            telemetry.addData("Button Pressed", gamepad2.dpad_down);
        } else {
            this.drive.stop();
        }
    }


    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped");
    }

}