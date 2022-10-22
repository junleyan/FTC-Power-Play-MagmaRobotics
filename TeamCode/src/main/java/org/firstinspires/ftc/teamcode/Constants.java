package org.firstinspires.ftc.teamcode;


public class Constants {


    public static final class MecanumDrive {
        public static final String leftFront = "leftFront";
        public static final String leftBack = "leftBack";
        public static final String rightFront = "rightFront";
        public static final String rightBack = "rightBack";

        public static final double strafePower = 0.5;
    }


    public static final class Lift {
        public static final String liftLeft = "liftLeft";
        public static final String liftRight = "liftRight";

        public static final double powerUp = 1;
        public static final double powerDown = 0.25;
    }


    public static final class Claw {
        public static final String clawLeft = "clawLeft";
        public static final String clawRight = "clawRight";
    }


    public static final class SensorGroup {
        public static final String distanceSensor = "distance";
        public static final String colorSensor = "color";
    }


    public static final class Timer {
        public static final int teleopTime = 120;
        public static final int autoTime = 30;
    }


}