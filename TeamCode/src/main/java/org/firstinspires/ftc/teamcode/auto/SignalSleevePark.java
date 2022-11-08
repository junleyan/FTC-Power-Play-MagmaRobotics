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

        drive.setNormal(0.3,0.3);
        while (opModeIsActive()){
            telemetry.addData("Task","Moving forward until close to sleeve");
            if (!(sensor.getZone() == 0)) {
                scheduled_zone = sensor.getZone();
            }
        }
        drive.stop();


        runtime.reset();
        while ((scheduled_zone == 1) && (runtime.milliseconds() < 3000)) {
            drive.setStrafe(-0.3, -0.3);
            telemetry.addData("Task","Moving toward the left");
        }
        drive.stop();

        runtime.reset();
        while ((scheduled_zone == 3) && (runtime.milliseconds() < 3000)) {
            drive.setStrafe(0.3, 0.3);
            telemetry.addData("Task","Moving forward the right");
        }
        drive.stop();


        telemetry.addData("Task","Completed");
    }


}