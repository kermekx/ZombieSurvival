package com.kermekx.engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.kermekx.engine.debbuger.Usages;
import com.kermekx.engine.keyboard.Key;
import com.kermekx.engine.log.KELogger;
import com.kermekx.engine.renderer.Renderer;
import com.kermekx.engine.scene.Scene;

public abstract class KermekxEngine implements Runnable {

	private final String WINDOW_NAME;
	private final int WIDTH;
	private final int HEIGHT;
	private boolean fullScreen = false;
	private Renderer renderer = new Renderer();

	private long lastFrame;
	private int fps;
	private long lastFPS;

	public KermekxEngine(String windowName) {
		WINDOW_NAME = windowName;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();

		KELogger.logInfo("Starting " + windowName);
	}

	public void run() {
		try {
			init();
			launch();
			loop();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public void init() throws LWJGLException {
		KELogger.logInfo("Initializing display...");
		Display.setDisplayMode(new DisplayMode(WIDTH / 2, HEIGHT / 2));
		Display.setTitle(WINDOW_NAME);
		Display.setResizable(true);
		Display.setVSyncEnabled(true);
		Display.create();
	}

	public abstract void launch();

	private void loop() {
		KELogger.logInfo("Initialized!");
		glClearColor(0f, 0f, 0f, 0f);
		glEnable(GL_BLEND);
		glEnable(GL_VERTEX_ARRAY);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		getDelta();

		/**
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					int delta = getDelta();
					renderer.update(delta);
					Usages.setUse("update");
				}
			}
		}).start();*/

		while (!Display.isCloseRequested()) {
			Usages.setNewLoop();
			if (Keyboard.isKeyDown(Key.KEY_F5))
				this.setFullScreen(!fullScreen);
			int delta = getDelta();
			renderer.update(delta);
			Usages.setUse("update");
			renderer.render();
			Usages.setUse("render");
			Display.sync(60);
			updateFPS();
			Usages.setUse("wait");
			Display.update();
			Usages.setUse("display");
		}

		KELogger.logInfo(WINDOW_NAME + " termined!");
		KELogger.logInfo("This game use Kermekx Engine, Developed by Kevin MESSIAEN and Paul DUMONT.");
		Display.destroy();

	}

	private void setFullScreen(boolean fullScreen) {
		try {
			if (fullScreen)
				setDisplayMode(WIDTH, HEIGHT, fullScreen);
			else
				setDisplayMode(WIDTH / 2, HEIGHT / 2, fullScreen);
			Display.setFullscreen(fullScreen);
			this.fullScreen = fullScreen;
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the display mode to be used
	 * 
	 * @param width
	 *            The width of the display required
	 * @param height
	 *            The height of the display required
	 * @param fullscreen
	 *            True if we want fullscreen mode
	 */
	public void setDisplayMode(int width, int height, boolean fullscreen) {

		if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height)
				&& (Display.isFullscreen() == fullscreen)) {
			return;
		}

		try {
			DisplayMode targetDisplayMode = null;

			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;

				for (int i = 0; i < modes.length; i++) {
					DisplayMode current = modes[i];

					if ((current.getWidth() == width) && (current.getHeight() == height)) {
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null)
									|| (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel())
								&& (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else {
				targetDisplayMode = new DisplayMode(width, height);
			}

			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);

		} catch (LWJGLException e) {
			KELogger.logInfo("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
		}
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Usages.log();
			lastFPS = getTime();
			fps = 0;
		}
		fps++;
	}

	public void setScene(Scene scene) {
		renderer.setScene(scene);
	}

}
