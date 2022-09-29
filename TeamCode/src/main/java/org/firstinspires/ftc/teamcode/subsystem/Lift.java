package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {

    private DcMotor lift;
    private HardwareMap hwMap;

    public Lift() {

    }

    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;

        this.lift = this.hwMap.get(DcMotor.class, "lift");
        this.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void up() {
        this.lift.setPower(1.0);
    }

    public void down() {
        this.lift.setPower(-1.0);
    }

    public void stop() {
        this.lift.setPower(0.0);
    }


}
