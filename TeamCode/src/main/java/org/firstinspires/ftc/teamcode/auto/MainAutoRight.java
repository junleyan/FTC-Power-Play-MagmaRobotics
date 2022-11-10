package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@Autonomous(name="Main Auto Mode", group="Auto")
public class MainAutoRight extends LinearOpMode {


    private MecanumDrive drive = new MecanumDrive();
    private SensorGroup sensor = new SensorGroup();
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {


        drive.init(hardwareMap);
        sensor.init(hardwareMap);


        waitForStart();
        drive.setStrafe(Constants.Auto.strafePower,Constants.Auto.strafePower);


        runtime.reset();
        while (opModeIsActive() && (runtime.milliseconds() < 2500)){
        }


        drive.stop();
        telemetry.addData("path","Complete");
        telemetry.update();
        sleep(1000);


    }


}

