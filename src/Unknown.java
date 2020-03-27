import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Unknown implements Behavior{
	private MovePilot pilot;
	private SampleProvider left;
	private SampleProvider front;
	private float [] leftwall = new float[1];
	private float [] frontwall = new float[1];

	public Unknown(MovePilot pilot,SampleProvider left,SampleProvider front) {
		this.pilot = pilot;
		this.left = left;
		this.front = front;
	}
	
	@Override
	public boolean takeControl() {
		left.fetchSample(leftwall, 0);
		front.fetchSample(frontwall,0);
		return (leftwall[0]>0.2f && frontwall[0]>0.2f);
	}
	
	@Override
	public void action() {
		if (!pilot.isMoving()) {
			pilot.travel(50);
			pilot.rotate(-250);
			pilot.travel(200);
		}
	}
	
	@Override
	public void suppress() {
		pilot.stop();
	}
}