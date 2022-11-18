package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Constants;


public class NavX {


    private IntegratingGyroscope gyro;
    private NavxMicroNavigationSensor navxMicro;
    private double targetAngle = 0;


    // initialization
    public NavX() {

    }


    public void init(HardwareMap hwMap) {
        navxMicro = hwMap.get(NavxMicroNavigationSensor.class, Constants.NavX.deviceName);
        gyro = (IntegratingGyroscope)navxMicro;
        while (this.isCalibrating()) {

        }
    }


    public boolean isCalibrating() {
        return navxMicro.isCalibrating();
    }


    public double AngVelX() {
        AngularVelocity rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
        return rates.xRotationRate;
    }


    public double AngVelY() {
       AngularVelocity rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
       return rates.yRotationRate;
    }


    public double AngVelZ() {
        AngularVelocity rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
        return rates.zRotationRate;
    }


    public double Heading() {
        Orientation angles = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angles.firstAngle;
    }


    public boolean isCenter() {
        if ((this.Heading() < 1.0) && (this.Heading() > 1.0)) {
            return true;
        }
        return false;
    }


    public boolean isTiltedLeft() {
        return (this.Heading() >= 1.0);
    }


    public boolean isTiltedRight() {
        return (this.Heading() <= -1.0);
    }


}