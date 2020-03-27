import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Main {
	public static void main(String [] args) {
		final float WHEEL_DIAMETER = 51;
		final float AXLE_LENGTH = 44;
		final float ANGULAR_SPEED = 100;
		final float LINEAR_SPEED = 70;
		EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S1);
		EV3UltrasonicSensor usTwo = new EV3UltrasonicSensor(SensorPort.S2);
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		float[] sample = new float[1];
		final float[] leftwall = new float[1];
		final float[] frontwall = new float[1];
		SampleProvider findLeft = us.getDistanceMode();
		SampleProvider findFront = usTwo.getDistanceMode();
		SampleProvider findColor = cs.getColorIDMode();
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor (MotorPort.A);
		Wheel wLeft = WheeledChassis.modelWheel(mLeft, WHEEL_DIAMETER).offset(-AXLE_LENGTH/2);
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor (MotorPort.D);
		Wheel wRight = WheeledChassis.modelWheel(mRight, WHEEL_DIAMETER).offset(AXLE_LENGTH/2);
		Chassis chassis = new WheeledChassis((new Wheel[] {wRight, wLeft}), WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot plt = new MovePilot(chassis);
                Behavior one = new Run(plt,findLeft,findFront);
		Behavior two = new TurnLeft(plt,findLeft,findFront);
		Behavior three = new TurnRight(plt,findLeft,findFront);
		Behavior four = new Unknown(plt,findLeft,findFront);
		Behavior five = new Save(plt,findColor);
                Behavior six = new End(plt,findColor);
		Behavior seven = new LowBattery();
		Behavior [] barray = {one,two,three,four,five,six};
		Arbitrator arbitrator = new Arbitrator(barray);
		
		Button.ENTER.waitForPressAndRelease();
		LCD.drawString("Welcome", 2,3);
		LCD.drawString("By Abdallah, Aoife, Hyun, Stefanie", 2,5);
		LCD.drawString("Version 1", 2,7);
		Delay . msDelay (1000);
		LCD.clear();
		
		arbitrator.go();
	}
}