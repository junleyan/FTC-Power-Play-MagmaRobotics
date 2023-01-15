package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Lift;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.NavX;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@Autonomous(name="*ABCDEFG", group="Auto")
public class EncoderAuto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime elapsedTime = new ElapsedTime();
    private MecanumDrive drive = new MecanumDrive();
    private SensorGroup sensor = new SensorGroup();
    private NavX navx = new NavX();
    private Lift lift = new Lift();
    private Claw claw = new Claw();

    private int scheduled_zone = 0;


    public void adjustDrive() {
        double rightPower = Constants.Auto.forwardPower;
        double leftPower = Constants.Auto.forwardPower;
        if (this.navx.isTiltedLeft()) {
            this.drive.setNormal(-leftPower, -rightPower * 0.75);
        } else if (this.navx.isTiltedRight()) {
            this.drive.setNormal(-leftPower * 0.75, -rightPower);
        } else {
            this.drive.setNormal(-leftPower, -rightPower);
        }
    }


    public void adjustDriveBeta() {
        double rightPower = -0.25;
        double leftPower = -0.25;
        if (this.navx.Heading() < -93) {
            this.drive.setNormal(leftPower, rightPower * 0.85);
        } else if (this.navx.Heading() > -87) {
            this.drive.setNormal(leftPower * 0.85, rightPower);
        } else {
            this.drive.setNormal(leftPower, rightPower);
        }
    }


    @Override
    public void runOpMode() {


        this.drive.init(hardwareMap);
        this.sensor.init(hardwareMap);
        this.navx.init(hardwareMap);
        this.lift.init(hardwareMap);
        this.claw.init(hardwareMap);
        this.drive.resetEncoder();
        waitForStart();
        elapsedTime.reset();


        this.claw.close();
        this.adjustDrive();
        this.runtime.reset();
        while (opModeIsActive() && this.runtime.milliseconds() <= 250) {
            this.telemetry.addData("Status","Moving forward until close to sleeve");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.addData("Heading", this.drive.Pos());
            this.telemetry.update();
        }


        this.runtime.reset();
        while (opModeIsActive() && this.runtime.milliseconds() <= 500) {
            this.telemetry.addData("Status","Moving forward until close to sleeve");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.addData("Heading", this.drive.Pos());
            this.telemetry.update();
        }

        this.drive.stop();
        this.runtime.reset();
        this.lift.up();
        while (opModeIsActive() && this.runtime.milliseconds() <= 1000) {
            this.telemetry.addData("Status","Lifting");
            this.telemetry.update();
        }

        this.adjustDrive();
        // move toward the signal sleeve
        while (opModeIsActive() && (this.scheduled_zone == 0) &&
                (this.elapsedTime.seconds() < Constants.Time.autoTime)){
            this.telemetry.addData("Status","Moving forward until close to sleeve");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.addData("Heading", this.drive.Pos());
            this.telemetry.update();
            if (!(sensor.Zone() == 0)) {
                this.scheduled_zone = sensor.Zone();
                this.drive.stop();
            }
        }



        // move forward more to adjust the position
        this.runtime.reset();

        while (this.drive.Pos() >= -2300) {
            this.telemetry.addData("Status","Adjusting y-axis location");
            this.telemetry.addData("Heading", this.drive.Pos());
            this.telemetry.update();
            this.drive.setNormal(-Constants.Auto.forwardPower, -Constants.Auto.forwardPower);
        }
        this.drive.stop();




        this.lift.set(0.5);
        // strafe to score
        this.runtime.reset();
        this.drive.resetEncoder();
        while (this.drive.Pos() <= 800) {
            this.telemetry.addData("Status","Strafe through to score");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.addData("Heading", this.drive.Pos());
            this.telemetry.update();
            this.drive.setStrafe(0.5, 0.5);
        }

        this.drive.stop();
        this.runtime.reset();
        while (opModeIsActive() && this.runtime.milliseconds() <= 1000) {
            this.telemetry.addData("Status","Moving forward until close to sleeve");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.addData("Heading", this.drive.Pos());
            this.telemetry.update();
        }

        this.lift.stop();
        this.runtime.reset();
        while (runtime.milliseconds() < 1000) {
            this.telemetry.addData("Status","Adjusting y-axis location");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.addData("Heading", this.drive.Pos());
            this.telemetry.update();
            this.drive.setNormal(-0.05, -0.05);
        }


        this.runtime.reset();
        this.claw.open();
        while (runtime.milliseconds() < 1000) {
            this.telemetry.addData("Status","Adjusting y-axis location");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.addData("Heading", this.drive.Pos());
            this.telemetry.update();
        }
        this.lift.up();

        // final lift down
        this.runtime.reset();
        while (this.runtime.milliseconds() < 250 && this.elapsedTime.seconds() <= 25) {
            this.lift.set(0.5);
        }

        this.runtime.reset();
        while (this.runtime.milliseconds() < 250 && this.elapsedTime.seconds() <= 25) {
            this.lift.set(0.3);
        }

        this.runtime.reset();
        while (this.runtime.milliseconds() < 250 && this.elapsedTime.seconds() <= 25) {
            this.lift.set(0.2);
        }

        this.runtime.reset();
        while (this.runtime.milliseconds() < 250 && this.elapsedTime.seconds() <= 25) {
            this.lift.set(0.0);
        }




        // strafe to score
        this.runtime.reset();
        this.drive.resetEncoder();
        while (this.drive.Pos() >= -800) {
            this.telemetry.addData("Status","Strafe back");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.update();
            this.drive.setStrafe(-0.5, -0.5);
        }


        // route if zone is 3 or 1 | make 90 degree turn
        this.runtime.reset();
        while ((this.scheduled_zone == 3 || this.scheduled_zone == 1) &&
                (this.navx.Heading() > -90) &&
                (this.elapsedTime.seconds() < Constants.Time.autoTime)) {
            telemetry.addData("Status","Moving toward the left");
            telemetry.addData("Heading", navx.Heading());
            telemetry.update();
            this.drive.setNormal(-0.2, 0.2);
        }


        // move forward more to adjust position | move to wall
        this.runtime.reset();
        while ((this.runtime.milliseconds() < 2500) &&
                (this.scheduled_zone == 3)) {
            telemetry.addData("Status","Adjusting x-axis location");
            telemetry.addData("Heading", this.navx.Heading());
            telemetry.update();
            this.drive.setNormal(-Constants.Auto.forwardPower, -Constants.Auto.forwardPower);
        }
        this.drive.stop();


        // route if zone is 1 | goes to zone 1
        this.runtime.reset();
        while ((this.scheduled_zone == 1) &&
                (this.runtime.milliseconds() < 1500) &&
                (this.elapsedTime.seconds() < Constants.Time.autoTime)) {
            telemetry.addData("Status","Moving toward the left");
            telemetry.addData("Heading", navx.Heading());
            telemetry.addData("Distance", sensor.Distance());
            telemetry.update();
            this.drive.setNormal(0.3, 0.3);
        }
        this.drive.stop();


        // end of auto and reports status
        while (opModeIsActive()) {
            telemetry.addData("Status", "Auto completed! Please record the following values!");
            telemetry.addData("Route", this.scheduled_zone);
            telemetry.addData("RBG", this.sensor.Red() + ", " + this.sensor.Blue() + ", " + this.sensor.Green());
            telemetry.addData("Heading", this.navx.Heading());
            telemetry.update();
        }
        this.drive.stop();


    }


}