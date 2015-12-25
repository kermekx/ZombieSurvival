package com.kermekx.zombiesurvival.sound;

import com.kermekx.engine.position.Vector;
import com.kermekx.engine.sound.SoundManager;

public enum Sounds {

	GUNSHOT(10, "gunshot"), TRIGGER(11, "trigger");

	private static final String SOUNDS_PATH = "assets/fx/";
	private static final String SOUND_FORMAT = ".wav";
	public static final Sounds[] SOUNDS = new Sounds[100];
	public static double progress = 0;

	private final int id;
	private final String name;
	private int soundId;

	private Sounds(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getSoundId() {
		return soundId;
	}

	public static double loadSounds() {
		double fail = 0;
		int size = Sounds.values().length;
		double progressPerSound = 1.0 / size;
		progress = 0;
		for (Sounds sound : Sounds.values()) {
			sound.soundId = SoundManager.loadSound(SOUNDS_PATH + sound.name + SOUND_FORMAT);
			SOUNDS[sound.id] = sound;
			progress += progressPerSound;
		}
		progress = 1;
		return fail / size;
	}
	
	public void play() {
		SoundManager.play(soundId, new Vector());
	}

	public void play(Vector position) {
		SoundManager.play(soundId, position);
	}
}
