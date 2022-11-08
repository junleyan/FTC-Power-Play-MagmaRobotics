package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@Autonomous(name="Signal Sleeve Based Parking", group="Auto")
public class SignalSleevePark extends LinearOpMode {


    private MecanumDrive drive = new MecanumDrive();
    private SensorGroup sensor = new SensorGroup();
    private ElapsedTime runtime = new ElapsedTime();
    private int scheduled_zone;


    @Override
    public void runOpMode() {
        drive.init(hardwareMap);
        sensor.init(hardwareMap);

        waitForStart();
        runtime.reset();

        drive.setNormal(0.3,0.3);
        while (opModeIsActive() && (sensor.getDistance() <= 3)){
            telemetry.addData("Task","Moving forward until close to sleeve");
        }
        drive.stop();


        scheduled_zone = sensor.getZone();
        if (scheduled_zone == 1) {
            runtime.reset();
            drive.setStrafe(-0.5, -0.5);
            while (opModeIsActive() && (runtime.milliseconds() <= 2000)){
                telemetry.addData("Task","Moving toward the left");
            }
            drive.stop();
        } else if (scheduled_zone == 3) {
            runtime.reset();
            drive.setStrafe(0.5, 0.5);
            while (opModeIsActive() && (runtime.milliseconds() <= 2000)){
                telemetry.addData("Task","Moving toward the right");
            }
            drive.stop();
        }

        runtime.reset();
        drive.setNormal(0.5, 0.5);
        while (opModeIsActive() && (runtime.milliseconds() <= 2000)){
            telemetry.addData("Task","Moving forward");
        }
        drive.stop();


        telemetry.addData("Task","Completed");
    }


}