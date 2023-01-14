package org.firstinspires.ftc.teamcode.subsystem;

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
        this.leftBack.setDirection(DcMotor.Direction.REVERSE);
        this.rightFront.setDirection(DcMotor.Direction.FORWARD);
        this.rightBack.setDirection(DcMotor.Direction.FORWARD);

        this.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void enableEncoder() {
        this.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void enableRunToPos() {
        this.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public void disableEncoder() {
        this.leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    public void resetEncoder() {
        this.rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public int Pos() {
        return this.rightFront.getCurrentPosition();
    }


    public void moveTo(int position, double power) {
        this.leftBack.setTargetPosition(position);
        this.setNormal(power, power);
    }


    // not used
    public void disableMoveTo() {
        this.disableEncoder();
        this.resetEncoder();
        this.stop();
    }


    // normal drive
    public void setNormal(double leftPower, double rightPower) {
        this.leftFront.setPower(leftPower);
        this.leftBack.setPower(leftPower);
        this.rightFront.setPower(rightPower);
        this.rightBack.setPower(rightPower);
    }


    // strafe drive
    public void setStrafe(double leftPower, double rightPower) {
        this.leftFront.setPower(-leftPower);
        this.leftBack.setPower(leftPower);
        this.rightFront.setPower(-rightPower);
        this.rightBack.setPower(rightPower);
    }


    public void stop() {
        this.setNormal(0, 0);
    }


    public void goForward() {
        this.setNormal(0.5, 0.5);
    }


    public void goBackward() {
        this.setNormal(0.5, 0.5);
    }


    // controller logic
    public void setControl(Gamepad gamepad) {
        this.disableEncoder();
        if (gamepad.left_bumper) {
            this.setStrafe(-0.5, -0.5);
        } else if (gamepad.right_bumper) {
            this.setStrafe(0.5, 0.5);
        } else if (gamepad.dpad_up) {
            this.goForward();
        } else if (gamepad.dpad_down) {
            this.goBackward();
        } else {
            this.setNormal(gamepad.left_stick_y, gamepad.right_stick_y);
        }
    }


}