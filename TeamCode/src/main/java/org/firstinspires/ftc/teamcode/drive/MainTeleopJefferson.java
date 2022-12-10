package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Lift;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@TeleOp(name="Main Teleop Mode (Jefferson)", group="OpMode")
public class MainTeleopJefferson extends OpMode {


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


        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void loop() {
        if (this.runtime.seconds() < Constants.Time.teleopTime) {
            this.drive.setControlJefferson(gamepad1);
            this.lift.setControl(gamepad2, false);
            this.claw.setControl(gamepad2);

            telemetry.addData("Status", "Enabled");
            telemetry.addData("Time Remaining", this.runtime.seconds());
            telemetry.addData("Lift Position", this.lift.getPosition());
            telemetry.addData("Detected Zone", this.sensor.Zone());
            telemetry.addData("Color Red", this.sensor.Red());
            telemetry.addData("Color Green", this.sensor.Green());
            telemetry.addData("Color Blue", this.sensor.Blue());
        } else {
            this.drive.stop();
        }
    }


    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped");
    }

}