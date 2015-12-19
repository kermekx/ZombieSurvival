package com.kermekx.engine;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.kermekx.engine.renderer.Renderer;
import com.kermekx.engine.scene.Scene;

public abstract class KermekxEngine implements Runnable {

	private final String WINDOW_NAME;
	private final int WIDTH;
	private final int HEIGHT;
	private Renderer renderer = new Renderer();

	private long lastFrame;
	private int fps;
	private long lastFPS;

	public KermekxEngine(String windowName) {
		WINDOW_NAME = windowName;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();

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
		Display.setDisplayMode(new DisplayMode(WIDTH / 2, HEIGHT / 2));
		Display.setTitle(WINDOW_NAME);
		Display.setResizable(true);
		Display.create();
		Display.sync(60);
	}

	public abstract void launch();

	private void loop() {
		glClearColor(0f, 0f, 0f, 0f);
		glEnable(GL_BLEND);
		glEnable(GL_VERTEX_ARRAY);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			renderer.update(delta);
			renderer.render();
			updateFPS();
			Display.update();
		}

		Display.destroy();
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return (delta < 0) ? 0 : delta;
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
