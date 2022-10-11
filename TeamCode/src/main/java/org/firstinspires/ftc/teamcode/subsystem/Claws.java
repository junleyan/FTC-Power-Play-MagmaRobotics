package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class Claws {

    private Servo claws;

    public Claws() {

    }

    // initialization
    public void init(HardwareMap hwMap) {
        this.claws = hwMap.get(Servo.class, Constants.Claws.deviceName);
    }

    // open claws
    public void open() {
        this.claws.setPosition(0);
    }

    // close claws
    public void close() {
        this.claws.setPosition(-1);
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
