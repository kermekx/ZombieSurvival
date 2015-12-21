package com.kermekx.engine.debbuger;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.Sys;

import com.kermekx.engine.log.KELogger;

public class Usages {
	
	private final static Usages INSTANCE = new Usages();
	
	private final Map<String, Long> usages;
	private long lastCall;
	
	public Usages() {
		usages = new HashMap<String, Long>();
	}
	
	public static void setNewLoop() {
		INSTANCE.getDelta();
	}
	
	public static void setUse(String fonction) {
		if(INSTANCE.usages.get(fonction) == null)
			INSTANCE.usages.put(fonction, new Long(0l));
		INSTANCE.usages.put(fonction, new Long(INSTANCE.usages.get(fonction) + INSTANCE.getDelta()));
	}
	
	public static void log() {
		for (String s : INSTANCE.usages.keySet())
			KELogger.logInfo(s + " use " + (INSTANCE.usages.get(s) * 0.1) + "% of time" );
		INSTANCE.usages.clear();
	}
	
	private int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastCall);
		lastCall = time;

		return delta;
	}
	
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}
