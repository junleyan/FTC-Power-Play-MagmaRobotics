package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Constants;

public class Lift {

    private DcMotor lift;

    public Lift() {

    }

    // initialization
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
        if (gamepad.a) {
            this.up();
        } else if(gamepad.b) {
            this.down();
        } else {
            this.stop();
        }
    }

}
