package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.NavX;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@Autonomous(name="Signal Sleeve Based Parking Beta", group="Auto")
public class SignalSleeveParkWithNavX extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime elapsedTime = new ElapsedTime();
    private MecanumDrive drive = new MecanumDrive();
    private SensorGroup sensor = new SensorGroup();
    private NavX navx = new NavX();

    private int scheduled_zone = 0;


    public void adjustDrive() {
        double rightPower = Constants.Auto.forwardPower;
        double leftPower = Constants.Auto.forwardPower;
        if (this.navx.isTiltedLeft()) {
            this.drive.setNormal(leftPower, rightPower * 0.75);
        } else if (this.navx.isTiltedRight()) {
            this.drive.setNormal(leftPower * 0.75, rightPower);
        } else {
            this.drive.setNormal(leftPower, rightPower);
        }
    }


    @Override
    public void runOpMode() {


        drive.init(hardwareMap);
        sensor.init(hardwareMap);
        navx.init(hardwareMap);
        waitForStart();


        // this.drive.setNormal(Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        adjustDrive();
        while (opModeIsActive() && (scheduled_zone == 0) && (elapsedTime.seconds() < Constants.Time.autoTime)){
            telemetry.addData("Task","Moving forward until close to sleeve");
            telemetry.addData("Heading", navx.getHeading());
            telemetry.update();
            if (!(sensor.getZone() == 0)) {
                scheduled_zone = sensor.getZone();
                this.drive.stop();
            }
        }


        runtime.reset();
        while (runtime.milliseconds() < Constants.Time.signalParkingTimeMinor) {
            telemetry.addData("Task","Adjusting y-axis location");
            telemetry.addData("Heading", navx.getHeading());
            telemetry.update();
            this.drive.setNormal(Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        }
        this.drive.stop();


        runtime.reset();
        while ((scheduled_zone == 3) &&
                (navx.getHeading() > -90) &&
                (elapsedTime.seconds() < Constants.Time.autoTime)) {
            telemetry.addData("Task","Moving toward the left");
            telemetry.addData("Heading", navx.getHeading());
            telemetry.update();
            this.drive.setNormal(-Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        }


        runtime.reset();
        while ((scheduled_zone == 1) &&
                (navx.getHeading() < 90) &&
                (elapsedTime.seconds() < Constants.Time.autoTime)) {
            telemetry.addData("Task","Moving forward the right");
            telemetry.addData("Heading", navx.getHeading());
            telemetry.update();
            this.drive.setNormal(Constants.Auto.forwardPower, -Constants.Auto.forwardPower);
        }


        runtime.reset();
        while (runtime.milliseconds() < Constants.Time.signalParkingTimeMajor) {
            telemetry.addData("Task","Adjusting x-axis location");
            telemetry.addData("Heading", navx.getHeading());
            telemetry.update();
            this.drive.setNormal(Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        }
        this.drive.stop();


        while (opModeIsActive()) {
            telemetry.addData("MSG", "Auto completed! Please record the following values!");
            telemetry.addData("Route", scheduled_zone);
            telemetry.addData("Heading", navx.getHeading());
            telemetry.update();
        }
        this.drive.stop();

    }


}