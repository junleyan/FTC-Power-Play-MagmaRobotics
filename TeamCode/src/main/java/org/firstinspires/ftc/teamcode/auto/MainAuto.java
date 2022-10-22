package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@Autonomous(name="Main Auto Mode", group="Auto")
public class MainAuto extends LinearOpMode {


    private MecanumDrive drive = new MecanumDrive();
    private SensorGroup sensor = new SensorGroup();
    private double power = 0.2;
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        drive.init(hardwareMap);
        sensor.init(hardwareMap);

        waitForStart();

        while (opModeIsActive() && (runtime.seconds() <= 3.0)) {
            drive.setStrafe(0.5, 0.5);
        }
        drive.stop();
        runtime.reset();
    }


}

