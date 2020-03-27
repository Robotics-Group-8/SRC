import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class End implements Behavior{
	private MovePilot pilot;
	private SampleProvider color;
	private float [] level = new float[1];

	public End(MovePilot pilot, SampleProvider color) {
		this.pilot = pilot;
		this.color = color;
	}
	
	@Override
	public boolean takeControl() {
		color.fetchSample(level, 0);
                LCD.drawString(Float.toString(level[0]),2,5);
		return (level[0] == 0);
	}
	
	@Override
	public void action() {
		if (pilot.isMoving()) {
			Sound.beepSequence();
			Sound.beepSequenceUp();
			LCD.drawString("DONE!", 6, 4);
			pilot.stop();
		}
	}
	
	@Override
	public void suppress() {
	}
}