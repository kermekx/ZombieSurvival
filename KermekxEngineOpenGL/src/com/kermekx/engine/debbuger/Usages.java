package com.kermekx.engine.debbuger;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.Sys;

import com.kermekx.engine.log.KELogger;

public class Usages {

	/**
	 * Instance de la classe du taux d'usages des fonctions
	 */
	private final static Usages INSTANCE = new Usages();

	/**
	 * Map de correspondance des fonctions à leur temps d'usage en millisecondes
	 */
	private final Map<String, Long> usages;
	/**
	 * Temps en millisecondes du dernier appele de la fonction
	 */
	private long lastCall;

	/**
	 * crée l'instance du taux d'usages
	 */
	private Usages() {
		usages = new HashMap<String, Long>();
	}

	/**
	 * Indique qu'ue boucle viens de commencer
	 */
	public static void setNewLoop() {
		INSTANCE.getDelta();
	}

	/**
	 * Indique la fin d'une fonction et l'enregistre dans la map
	 * 
	 * @param fonction
	 *            nom de la fonction (clef)
	 */
	public static void setUse(String fonction) {
		if (INSTANCE.usages.get(fonction) == null)
			INSTANCE.usages.put(fonction, new Long(0l));
		INSTANCE.usages.put(fonction, new Long(INSTANCE.usages.get(fonction) + INSTANCE.getDelta()));
	}

	/**
	 * Affiche le taux d'utilisation et vide la map
	 */
	public static void log() {
		for (String s : INSTANCE.usages.keySet())
			KELogger.logInfo(s + " use " + (INSTANCE.usages.get(s) * 0.1) + "% of time");
		INSTANCE.usages.clear();
	}

	/**
	 * Calcule le temps depuis le dernier appel
	 * 
	 * @return temps d'écart en millisecondes
	 */
	private int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastCall);
		lastCall = time;

		return delta;
	}

	/**
	 * Renvoie le temps du système
	 * 
	 * @return temps du système en millisecondes
	 */
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}
