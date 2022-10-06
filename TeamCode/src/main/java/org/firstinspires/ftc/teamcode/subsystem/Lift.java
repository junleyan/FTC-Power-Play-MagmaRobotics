package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;

public class Lift {

    private DcMotor lift;
    private HardwareMap hwMap;

    public Lift() {

    }

    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;

        this.lift = this.hwMap.get(DcMotor.class, Constants.Lift.deviceName);
        this.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void up() {
        this.lift.setPower(Constants.Lift.power);
    }

    public void down() {
        this.lift.setPower(-Constants.Lift.power);
    }

    public void stop() {
        this.lift.setPower(0.0);
    }


}
