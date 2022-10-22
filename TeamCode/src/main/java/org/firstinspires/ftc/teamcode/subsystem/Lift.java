package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

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


    // lift goes up
    public void up() {
        this.lift.setPower(Constants.Lift.power);
    }


    // lift goes down
    public void down() {
        this.lift.setPower(-Constants.Lift.power);
    }


    // lift stops and brakes
    public void stop() {
        this.lift.setPower(0.0);
    }


    // controller logic
    public void setControl(Gamepad gamepad) {
        if (gamepad.dpad_down) {
            this.up();
        } else if(gamepad.dpad_up) {
            this.down();
        } else {
            this.stop();
        }
    }


}
