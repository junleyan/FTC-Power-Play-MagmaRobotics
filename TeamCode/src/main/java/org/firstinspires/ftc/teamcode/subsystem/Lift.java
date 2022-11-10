package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.Constants;


public class Lift {


    private DcMotor lift;


    // initialization
    public Lift() {

    }

    public void init(HardwareMap hwMap) {
        this.lift = hwMap.get(DcMotor.class, Constants.Lift.deviceName);
        this.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void up() {
        this.lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.lift.setPower(Constants.Lift.power);
    }


    public void down() {
        this.lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.lift.setPower(-Constants.Lift.power);
    }


    // lift goes up
    public void upWithEncoder() {
        if (!this.lift.isBusy()) {
            this.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.lift.setPower(Constants.Lift.power);
            this.lift.setTargetPosition(Constants.Lift.targetPosition);
            this.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }


    // lift goes down
    public void downWithEncoder() {
        if (!this.lift.isBusy()) {
            this.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.lift.setPower(-Constants.Lift.power);
            this.lift.setTargetPosition(0);
            this.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }


    // lift stops and brakes
    public void stop() {
        this.lift.setPower(0.0);
    }

    public int getPosition() {
        return this.lift.getCurrentPosition();
    }


    // controller logic
    public void setControl(Gamepad gamepad, boolean withEncoder) {
        if (withEncoder) {
            if (gamepad.dpad_up) {
                this.upWithEncoder();
            } else if (gamepad.dpad_down) {
                this.downWithEncoder();
            }
        } else {
            if (gamepad.dpad_up) {
                this.up();
            } else if (gamepad.dpad_down) {
                this.down();
            } else {
                this.stop();
            }
        }
    }


}
