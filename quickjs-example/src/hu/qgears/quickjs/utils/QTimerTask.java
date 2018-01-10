package hu.qgears.quickjs.utils;

import java.util.TimerTask;

public class QTimerTask extends TimerTask
{
	private Runnable runnable;
	
	public QTimerTask(Runnable runnable) {
		super();
		this.runnable = runnable;
	}

	@Override
	public void run() {
		runnable.run();
	}

}
