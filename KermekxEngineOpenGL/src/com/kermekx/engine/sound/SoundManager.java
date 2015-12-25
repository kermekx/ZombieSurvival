package com.kermekx.engine.sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class SoundManager {

	private static IntBuffer buffer = BufferUtils.createIntBuffer(100);
	private static IntBuffer source = BufferUtils.createIntBuffer(100);

	private static FloatBuffer sourcePos = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
	private static FloatBuffer sourceVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
	private static FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
	private static FloatBuffer listenerVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f });
	private static FloatBuffer listenerOri = BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f });

	private static boolean gen = false;
	private static int index = 0;
	private static Map<String, Integer> sounds = new HashMap<String, Integer>();
	
	public static int loadSound(String path) {
		if (sounds.get(path) != null)
			return sounds.get(path);
		
		AL10.alGenBuffers(buffer);

		if (AL10.alGetError() != AL10.AL_NO_ERROR)
			return -1;

	    BufferedInputStream bin = null;
		try {
			bin = new BufferedInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
		WaveData waveFile = WaveData.create(bin);
		
		AL10.alBufferData(buffer.get(index), waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();

		if(!gen) {
			AL10.alGenSources(source);
			setListenerValues();
			gen = true;
		}
		
		if (AL10.alGetError() != AL10.AL_NO_ERROR)
			return -1;

		sourcePos.flip();
		sourceVel.flip();
		AL10.alSourcei(source.get(index), AL10.AL_BUFFER, buffer.get(0));
		AL10.alSourcef(source.get(index), AL10.AL_PITCH, 1.0f);
		AL10.alSourcef(source.get(index), AL10.AL_GAIN, 1.0f);
		AL10.alSource(source.get(index), AL10.AL_POSITION, sourcePos);
		AL10.alSource(source.get(index), AL10.AL_VELOCITY, sourceVel);

		if (AL10.alGetError() == AL10.AL_NO_ERROR) {
			sounds.put(path, new Integer(index));
			return index++;
		}
		
		return -1;
	}

	private static void setListenerValues() {
		listenerPos.flip();
		listenerVel.flip();
		listenerOri.flip();
		AL10.alListener(AL10.AL_POSITION, listenerPos);
		AL10.alListener(AL10.AL_VELOCITY, listenerVel);
		AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
	}

	public static void killALData() {
		AL10.alDeleteSources(source);
		AL10.alDeleteBuffers(buffer);
	}

	public static void play(int id) {
		AL10.alSourcePlay(source.get(id));
	}
	
	public static void stop(int id) {
		AL10.alSourceStop(source.get(id));
	}
	
	public static void pause(int id) {
		AL10.alSourcePause(source.get(id));
	}
}
