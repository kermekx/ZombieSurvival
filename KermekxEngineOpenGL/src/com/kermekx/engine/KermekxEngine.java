package com.kermekx.engine;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.kermekx.engine.keyboard.Key;
import com.kermekx.engine.log.KELogger;
import com.kermekx.engine.renderer.Renderer;
import com.kermekx.engine.scene.Scene;

public abstract class KermekxEngine implements Runnable {

	private final String WINDOW_NAME;
	private final int WIDTH;
	private final int HEIGHT;
	private boolean fullScren = false;
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
		Display.create();
		Display.sync(60);
	}

	public abstract void launch();

	private void loop() {
		KELogger.logInfo("Initialized!");
		glClearColor(0f, 0f, 0f, 0f);
		glEnable(GL_BLEND);
		glEnable(GL_VERTEX_ARRAY);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		getDelta();

		while (!Display.isCloseRequested()) {
			if (Keyboard.isKeyDown(Key.KEY_F5))
				this.setFullScreen(!fullScren);

			int delta = getDelta();
			renderer.update(delta);
			renderer.render();
			updateFPS();
			Display.update();
		}

		KELogger.logInfo(WINDOW_NAME + " termined!");
		KELogger.logInfo("This game use Kermekx Engine, Developed by Kevin MESSIAEN and Paul DUMONT.");
		Display.destroy();

	}

	private void setFullScreen(boolean fullScreen) {
		/*
		 * try { Display.setFullscreen(fullScreen); fullScren = !fullScren; }
		 * catch (LWJGLException e) { e.printStackTrace(); }
		 */
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
			Display.setTitle("FPS : " + fps);
			lastFPS = getTime();
			fps = 0;
		}
		fps++;
	}

	public void setScene(Scene scene) {
		renderer.setScene(scene);
	}

}
