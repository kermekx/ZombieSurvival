package com.kermekx.engine.texture;

public class TextureLoader {

	private static double progress = 0;

	public static double loadTexture(LoadableTexturePack ltp) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (progress < 1f) {
					progress = ltp.getProgress();
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

	public static double getProgress() {
		return progress;
	}

}
