package timer;

import application.CCApplication;
import interfaces.ITimer;

/**
 * Timer class to call a method regularly. Main method
 * can be executed in a scheduled way.
 *
 * @author Team_19
 *
 */
public class Timer {

	public static void main(String[] args) {
		ITimer ccApp = new CCApplication();
		ccApp.runTimer();
	}
}
