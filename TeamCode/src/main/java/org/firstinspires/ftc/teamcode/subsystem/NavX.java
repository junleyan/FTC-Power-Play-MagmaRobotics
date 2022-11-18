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


    // initialization
    public NavX() {

    }


    public void init(HardwareMap hwMap) {
        navxMicro = hwMap.get(NavxMicroNavigationSensor.class, Constants.NavX.deviceName);
        gyro = (IntegratingGyroscope)navxMicro;
        while (isCalibrating()) {
        }
    }


    public boolean isCalibrating() {
        return navxMicro.isCalibrating();
    }


    public double getAngVelX() {
        AngularVelocity rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
        return rates.xRotationRate;
    }


    public double getAngVelY() {
       AngularVelocity rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
       return rates.yRotationRate;
    }


    public double getAngVelZ() {
        AngularVelocity rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
        return rates.zRotationRate;
    }


    public double getHeading() {
        Orientation angles = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angles.firstAngle;
    }


    public boolean isCenter() {
        if ((this.getHeading() < 1.0) && (this.getHeading() > 1.0)) {
            return true;
        }
        return false;
    }


    public boolean isTiltedLeft() {
        return (this.getHeading() >= 1.0);
    }


    public boolean isTiltedRight() {
        return (this.getHeading() <= -1.0);
    }


}