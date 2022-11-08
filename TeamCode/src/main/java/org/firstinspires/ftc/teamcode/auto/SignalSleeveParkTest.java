package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@Autonomous(name="Signal Sleeve Based Parking", group="Auto")
public class SignalSleeveParkTest extends LinearOpMode {

    private SensorGroup sensor = new SensorGroup();
    private ElapsedTime runtime = new ElapsedTime();
    private int scheduled_zone;


    @Override
    public void runOpMode() {
        sensor.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("Task","Moving forward until close to sleeve");
            if (!(sensor.getZone() == 0)) {
                scheduled_zone = sensor.getZone();
            }
        }


        runtime.reset();
        while ((scheduled_zone == 1) && (runtime.milliseconds() < 3000)) {
            telemetry.addData("Task","Moving toward the left");
        }

        runtime.reset();
        while ((scheduled_zone == 3) && (runtime.milliseconds() < 3000)) {
            telemetry.addData("Task","Moving forward the right");
        }


        telemetry.addData("Task","Completed");
        telemetry.addData("Route", scheduled_zone);
    }


}