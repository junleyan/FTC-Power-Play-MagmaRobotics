package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;


public class MecanumDrive {


    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;


    // initialization
    public MecanumDrive() {

    }

    public void init(HardwareMap hwMap) {
        this.leftFront = hwMap.get(DcMotor.class, Constants.MecanumDrive.leftFront);
        this.leftBack = hwMap.get(DcMotor.class, Constants.MecanumDrive.leftBack);
        this.rightFront = hwMap.get(DcMotor.class, Constants.MecanumDrive.rightFront);
        this.rightBack = hwMap.get(DcMotor.class, Constants.MecanumDrive.rightBack);

        this.leftFront.setDirection(DcMotor.Direction.REVERSE);
        this.leftBack.setDirection(DcMotor.Direction.FORWARD);
        this.rightFront.setDirection(DcMotor.Direction.FORWARD);
        this.rightBack.setDirection(DcMotor.Direction.REVERSE);
    }


    // normal drive
    public void setNormal(double leftPower, double rightPower) {
        rightPower *= Constants.MecanumDrive.powerMultiplier;
        leftPower *= Constants.MecanumDrive.powerMultiplier;
        this.leftFront.setPower(-leftPower);
        this.leftBack.setPower(-leftPower);
        this.rightFront.setPower(-rightPower);
        this.rightBack.setPower(rightPower);
    }


    // strafe drive
    public void setStrafe(double leftPower, double rightPower) {
        this.leftFront.setPower(-leftPower);
        this.leftBack.setPower(leftPower);
        this.rightFront.setPower(rightPower);
        this.rightBack.setPower(rightPower);
    }


    // strafe drive but with fixed power
    public void setFixedStrafe() {
        this.leftFront.setPower(-Constants.MecanumDrive.strafePower);
        this.leftBack.setPower(Constants.MecanumDrive.strafePower);
        this.rightFront.setPower(-Constants.MecanumDrive.strafePower);
        this.rightBack.setPower(-Constants.MecanumDrive.strafePower);
    }

    public void stop() {
        this.leftFront.setPower(0);
        this.leftBack.setPower(0);
        this.rightFront.setPower(0);
        this.rightBack.setPower(0);
    }


    // controller logic
    public void setControl(Gamepad gamepad) {
        if (gamepad.left_bumper) {
            this.setStrafe(-0.5, -0.5);
        } else if (gamepad.right_bumper) {
            this.setStrafe(0.5, 0.5);
        } else {
            this.setNormal(gamepad.left_stick_y, gamepad.right_stick_y);
        }
    }


}
