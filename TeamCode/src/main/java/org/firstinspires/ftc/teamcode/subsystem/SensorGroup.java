package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Constants;


public class SensorGroup {


    private DistanceSensor distance;
    private ColorSensor color;


    // initialization
    public SensorGroup() {

    }


    public void init(HardwareMap hwMap) {
        this.distance = hwMap.get(DistanceSensor.class, Constants.SensorGroup.distanceSensor);
        this.color = hwMap.get(ColorSensor.class, Constants.SensorGroup.colorSensor);
    }


    // gets detected distance in cm
    public double Distance() {
        return this.distance.getDistance(DistanceUnit.CM);
    }


    // get red values from color sensor
    public int Red() {
        return this.color.red();
    }


    public int Blue() {
        return this.color.blue();
    }


    public int Green() {
        return this.color.green();
    }


    public int Zone() {
        if (Distance() < 2.7) {
            if (this.Green() > (this.Red() + this.Blue()) * 0.75) {
                return 3;
            }
            if (this.Blue() > (this.Red() + this.Green()) * 0.75) {
                return 2;
            }
            return 1;
        }
        return 0;    }


}