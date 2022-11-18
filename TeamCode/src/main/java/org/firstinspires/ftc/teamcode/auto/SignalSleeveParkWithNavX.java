package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.NavX;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@Autonomous(name="Signal Sleeve Based Parking With NavX", group="Auto")
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


        // move toward the signal sleeve
        adjustDrive();
        while (opModeIsActive() && (scheduled_zone == 0) && (elapsedTime.seconds() < Constants.Time.autoTime)){
            telemetry.addData("Status","Moving forward until close to sleeve");
            telemetry.addData("Heading", navx.Heading());
            telemetry.update();
            if (!(sensor.Zone() == 0)) {
                scheduled_zone = sensor.Zone();
                this.drive.stop();
            }
        }


        // move forward more to adjust the position
        runtime.reset();
        while (runtime.milliseconds() < Constants.Time.signalParkingTimeMinor) {
            telemetry.addData("Status","Adjusting y-axis location");
            telemetry.addData("Heading", navx.Heading());
            telemetry.update();
            this.drive.setNormal(Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        }
        this.drive.stop();


        // route if zone is 3
        runtime.reset();
        while ((scheduled_zone == 3) &&
                (navx.Heading() > -90) &&
                (elapsedTime.seconds() < Constants.Time.autoTime)) {
            telemetry.addData("Status","Moving toward the left");
            telemetry.addData("Heading", navx.Heading());
            telemetry.update();
            this.drive.setNormal(-Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        }


        // route if zone is 1
        runtime.reset();
        while ((scheduled_zone == 1) &&
                (navx.Heading() < 90) &&
                (elapsedTime.seconds() < Constants.Time.autoTime)) {
            telemetry.addData("Status","Moving forward the right");
            telemetry.addData("Heading", navx.Heading());
            telemetry.update();
            this.drive.setNormal(Constants.Auto.forwardPower, -Constants.Auto.forwardPower);
        }


        // move forward more to adjust position
        runtime.reset();
        while (runtime.milliseconds() < Constants.Time.signalParkingTimeMajor) {
            telemetry.addData("Status","Adjusting x-axis location");
            telemetry.addData("Heading", navx.Heading());
            telemetry.update();
            this.drive.setNormal(Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        }
        this.drive.stop();


        // end of auto and reports status
        while (opModeIsActive()) {
            telemetry.addData("Status", "Auto completed! Please record the following values!");
            telemetry.addData("Route", scheduled_zone);
            telemetry.addData("RBG", sensor.Green());
            telemetry.addData("Heading", navx.Heading());
            telemetry.update();
        }
        this.drive.stop();


    }


}