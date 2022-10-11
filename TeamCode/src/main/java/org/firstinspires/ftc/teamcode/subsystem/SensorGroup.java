package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Constants;

public class SensorGroup {

    private DistanceSensor distance;

    public SensorGroup() {

    }

    // initialization
    public void init(HardwareMap hwMap) {
        this.distance = hwMap.get(DistanceSensor.class, Constants.SensorGroup.distanceSensor);
    }

    // gets detected distance in cm
    public double getDistance() {
        return this.distance.getDistance(DistanceUnit.CM);
    }
}