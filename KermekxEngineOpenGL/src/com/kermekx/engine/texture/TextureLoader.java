package com.kermekx.engine.texture;

import org.lwjgl.opengl.Display;

import com.kermekx.engine.KermekxEngine;
import com.kermekx.engine.listener.LoadingListener;

public class TextureLoader {

	private static double progress = 0;
	private static LoadingListener listener;

	public static double loadTexture(LoadableTexturePack ltp) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (progress < 1f) {
					progress = ltp.getProgress();

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		return ltp.loadTextures();
	}

	public static double loadTexture(LoadableTexturePack[] ltps) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (progress < 1f) {
					double tmp = 0f;
					for (LoadableTexturePack ltp : ltps) {
						tmp += ltp.getProgress();
					}
					progress = tmp / ltps.length;
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		double success = 0;

		for (LoadableTexturePack ltp : ltps)
			success += ltp.loadTextures();
		return success / ltps.length;
	}

	public static double loadTexture(LoadableTexturePack ltp, LoadingListener l) {
		listener = l;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (progress < 1f) {
					progress = ltp.getProgress();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		return ltp.loadTextures();

	}

	public static double loadTexture(LoadableTexturePack[] ltps, LoadingListener l) {
		listener = l;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (progress < 1f) {
					double tmp = 0f;
					for (LoadableTexturePack ltp : ltps) {
						tmp += ltp.getProgress();
					}
					progress = tmp / ltps.length;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		double success = 0;

		for (LoadableTexturePack ltp : ltps)
			success += ltp.loadTextures();
		return success / ltps.length;
	}

	public static double getProgress() {
		return progress;
	}

	public static void update() {
		if (listener == null)
			return;
		listener.onUpdate(progress);
		KermekxEngine.INSTANCE.getRenderer().render();
		Display.update();
	}

}
