package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@Autonomous(name="Signal Sleeve Based Parking", group="Auto")
public class SignalSleevePark extends LinearOpMode {

    private MecanumDrive drive = new MecanumDrive();
    private SensorGroup sensor = new SensorGroup();
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime elapsedTime = new ElapsedTime();
    private int scheduled_zone = 0;


    @Override
    public void runOpMode() {


        drive.init(hardwareMap);
        sensor.init(hardwareMap);
        waitForStart();


        this.drive.setNormal(Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        while (opModeIsActive() && (scheduled_zone == 0) && (elapsedTime.seconds() < Constants.Time.autoTime)){
            telemetry.addData("Task","Moving forward until close to sleeve");
            telemetry.update();
            if (!(sensor.getZone() == 0)) {
                scheduled_zone = sensor.getZone();
                this.drive.stop();
            }
        }


        runtime.reset();
        while (runtime.milliseconds() < Constants.Time.signalParkingTimeMinor) {
            telemetry.addData("Task","Adjusting y-axis location");
            telemetry.update();
            this.drive.setNormal(Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        }
        this.drive.stop();


        runtime.reset();
        while ((scheduled_zone == 3) &&
                (runtime.milliseconds() < Constants.Time.signalParkingTime) &&
                (elapsedTime.seconds() < Constants.Time.autoTime)) {
            telemetry.addData("Task","Moving toward the left");
            telemetry.update();
            this.drive.setStrafe(-Constants.Auto.strafePower, -Constants.Auto.strafePower);
        }


        runtime.reset();
        while ((scheduled_zone == 1) &&
                (runtime.milliseconds() < Constants.Time.signalParkingTime) &&
                (elapsedTime.seconds() < Constants.Time.autoTime)) {
            telemetry.addData("Task","Moving forward the right");
            telemetry.update();
            this.drive.setStrafe(Constants.Auto.strafePower, Constants.Auto.strafePower);
        }


        this.drive.stop();


        telemetry.addData("Task","Completed");
        telemetry.addData("Route", scheduled_zone);
        telemetry.update();


    }


}