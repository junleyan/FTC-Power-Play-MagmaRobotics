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
    public double getDistance() {
        return this.distance.getDistance(DistanceUnit.CM);
    }


    // get red values from color sensor
    public int getRed() {
        return this.color.red();
    }


    public int getBlue() {
        return this.color.blue();
    }


    public int getGreen() {
        return this.color.green();
    }


    public int getZone() {
        if (getDistance() <= 3.0) {
            if (this.getGreen() > (this.getRed() + this.getBlue()) * 0.75) {
                return 3;
            }
            if (this.getBlue() > (this.getRed() + this.getGreen()) * 0.75) {
                return 2;
            }
            return 1;
        }
        return 0;    }


}