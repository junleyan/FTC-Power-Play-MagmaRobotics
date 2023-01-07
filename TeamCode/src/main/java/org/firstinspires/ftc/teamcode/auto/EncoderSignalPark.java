package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.NavX;
import org.firstinspires.ftc.teamcode.subsystem.SensorGroup;


@Autonomous(name="RUN THIS", group="Auto")
public class EncoderSignalPark extends LinearOpMode {

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
            this.drive.setNormal(-leftPower, -rightPower * 0.75);
        } else if (this.navx.isTiltedRight()) {
            this.drive.setNormal(-leftPower * 0.75, -rightPower);
        } else {
            this.drive.setNormal(-leftPower, -rightPower);
        }
    }


    @Override
    public void runOpMode() {


        this.drive.init(hardwareMap);
        this.sensor.init(hardwareMap);
        this.navx.init(hardwareMap);
        waitForStart();
        elapsedTime.reset();




        // move forward more to adjust the position
        this.runtime.reset();
        this.drive.resetEncoder();
        this.drive.enableEncoder();

        this.drive.moveTo(-2000, -0.2);
        this.drive.enableRunToPos();
        while (this.drive.Pos() >= -2000) {
            this.telemetry.addData("Status","Adjusting y-axis location");
            this.telemetry.addData("Encoder", this.drive.Pos());
            this.telemetry.update();
        }
        this.drive.stop();
        this.drive.resetEncoder();
        this.drive.disableEncoder();


        this.runtime.reset();
        while (runtime.milliseconds() < 1000) {
            this.telemetry.addData("Status","Adjusting y-axis location");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.update();
            this.drive.setStrafe(Constants.Auto.forwardPower, Constants.Auto.forwardPower);
        }
        this.drive.stop();

    }


}