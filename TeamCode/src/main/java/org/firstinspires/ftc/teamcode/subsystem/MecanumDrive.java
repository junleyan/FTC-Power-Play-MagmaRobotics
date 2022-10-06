package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class MecanumDrive {

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private HardwareMap hwMap;

    public MecanumDrive() {

    }

    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;

        this.leftFront = this.hwMap.get(DcMotor.class, Constants.MecanumDrive.leftFront);
        this.leftBack = this.hwMap.get(DcMotor.class, Constants.MecanumDrive.leftBack);
        this.rightFront = this.hwMap.get(DcMotor.class, Constants.MecanumDrive.rightFront);
        this.rightBack = this.hwMap.get(DcMotor.class, Constants.MecanumDrive.rightBack);

        this.leftFront.setDirection(DcMotor.Direction.FORWARD);
        this.leftBack.setDirection(DcMotor.Direction.FORWARD);
        this.rightFront.setDirection(DcMotor.Direction.REVERSE);
        this.rightBack.setDirection(DcMotor.Direction.REVERSE);

    }

    public void set(double leftPower, double rightPower) {
        this.leftFront.setPower(leftPower);
        this.leftBack.setPower(leftPower);
        this.rightFront.setPower(rightPower);
        this.rightBack.setPower(rightPower);
    }
}
