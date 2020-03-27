import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class LowBattery implements Behavior{
	static int LOWBATTERY = (int) 7.8; 	
	
	@Override
	public boolean takeControl() {
		int CURRENTVOLTAGE = (int) Battery.getVoltage();
		if (CURRENTVOLTAGE<LOWBATTERY) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		System.exit(0);
		
	}

	@Override
	public void suppress() {
		Button.ENTER.waitForPressAndRelease();
		
	}
	
	

}