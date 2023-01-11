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


@Autonomous(name="RUN THIS", group="Auto")
public class EncoderSignalPark extends LinearOpMode {

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


    @Override
    public void runOpMode() {


        this.drive.init(hardwareMap);
        this.sensor.init(hardwareMap);
        this.navx.init(hardwareMap);
        this.lift.init(hardwareMap);
        this.claw.init(hardwareMap);
        waitForStart();
        elapsedTime.reset();


        // move toward the signal sleeve
        this.adjustDrive();
        while (opModeIsActive() && (this.scheduled_zone == 0) &&
                (this.elapsedTime.seconds() < Constants.Time.autoTime)){
            this.telemetry.addData("Status","Moving forward until close to sleeve");
            this.telemetry.addData("Heading", this.navx.Heading());
            this.telemetry.update();
            if (!(sensor.Zone() == 0)) {
                this.scheduled_zone = sensor.Zone();
                this.drive.stop();
            }
        }


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


        // strafe
        this.runtime.reset();
        while (opModeIsActive() && (this.runtime.milliseconds() > 2000)) {

        }

    }


}