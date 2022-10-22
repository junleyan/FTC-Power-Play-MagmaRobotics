package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;


public class Claw {


    private Servo clawLeft;
    private Servo clawRight;


    // initialization
    public Claw() {

    }

    public void init(HardwareMap hwMap) {
        this.clawLeft = hwMap.get(Servo.class, Constants.Claw.clawLeft);
        this.clawRight = hwMap.get(Servo.class, Constants.Claw.clawRight);
    }


    // open claws
    public void open() {
        this.clawLeft.setPosition(1);
        this.clawRight.setPosition(-1);
    }


    // close claws
    public void close() {
        this.clawLeft.setPosition(-1);
        this.clawRight.setPosition(1);
    }


    // controller logic
    public void setControl(Gamepad gamepad) {
        if (gamepad.x) {
            this.open();
        } else if (gamepad.y) {
            this.close();
        }
    }


}
