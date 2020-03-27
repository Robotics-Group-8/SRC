import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Save implements Behavior{
	private MovePilot pilot;
	private SampleProvider color;
	private float [] level = new float[1];

	public Save(MovePilot pilot, SampleProvider color) {
		this.pilot = pilot;
		this.color = color;
	}
	
	@Override
	public boolean takeControl() {
		color.fetchSample(level, 0);
		return (level[0] == 1);
	}
	
	@Override
	public void action() {
		pilot.travel(-70);
		pilot.rotate(480);
	    pilot.travel(-70);
	}
	
	@Override
	public void suppress() {
	}
}