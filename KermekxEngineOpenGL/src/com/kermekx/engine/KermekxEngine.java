package com.kermekx.engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
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
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.kermekx.engine.debbuger.Usages;
import com.kermekx.engine.keyboard.Key;
import com.kermekx.engine.log.KELogger;
import com.kermekx.engine.renderer.Renderer;
import com.kermekx.engine.scene.Scene;
import com.kermekx.engine.sound.SoundManager;

public abstract class KermekxEngine implements Runnable {

	public static KermekxEngine INSTANCE;

	/**
	 * Nom de la fenêtre
	 */
	private final String WINDOW_NAME;

	/**
	 * Largeur de l'écran
	 */
	private final int WIDTH;

	/**
	 * Hauteur de l'écran
	 */
	private final int HEIGHT;

	/**
	 * Vrai lorsque la fenêtre est en plein écran, faux sinon
	 */
	private boolean fullScreen = false;

	/**
	 * Classe de rendue de la fenêtre
	 */
	private Renderer renderer = new Renderer();

	/**
	 * Temps de la dernière frame.
	 */
	private long lastFrame;

	/**
	 * Conteur de FPS
	 */
	private int fps;

	/**
	 * Temps du dernier calcule de FPS
	 */
	private long lastFPS;
	
	public boolean run = false;

	/**
	 * Créer une instance du moteur graphique
	 * 
	 * @param windowName
	 *            Nom de la denêtre
	 */
	public KermekxEngine(String windowName) {
		INSTANCE = this;
		WINDOW_NAME = windowName;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();

		KELogger.logInfo("Starting " + windowName);
	}

	/**
	 * Lance le moteur graphique
	 */
	public void run() {
		try {
			init();
			launch();
			loop();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialise le moteur graphique
	 * 
	 * @throws LWJGLException
	 *             Erreur lors de l'initialisation d'openGL
	 */
	public void init() throws LWJGLException {
		KELogger.logInfo("Initializing display...");
		Display.setDisplayMode(new DisplayMode(WIDTH / 2, HEIGHT / 2));
		Display.setTitle(WINDOW_NAME);
		Display.setResizable(true);
		Display.setVSyncEnabled(true);
		Display.create();
		AL.create();

		glClearColor(0f, 0f, 0f, 0f);
		glEnable(GL_BLEND);
		glEnable(GL_VERTEX_ARRAY);
		glEnable(GL_DEPTH_TEST);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		KELogger.logInfo("Initialized!");
	}

	/**
	 * Classe abstraite permetant de lancer le jeu après l'initialisation
	 * d'openGL
	 */
	public abstract void launch();

	/**
	 * Boucle de rendue graphique et de mise à jour de la scène
	 */
	private void loop() {

		boolean isLogicThreadRunning = false;
		run = true;
		getDelta();

		// TODO : Mise à jour de la scène dans une autre Thread
		if(Runtime.getRuntime().availableProcessors() > 1) {
			Runnable update = new Runnable() {
				@Override
				public void run() {
					boolean isAIThreadRunning = false;
					
					if(Runtime.getRuntime().availableProcessors() > 2) {
						Runnable updateAI = new Runnable() {
							long lastFrame;
							@Override
							public void run() {
								getDelta();
								int delta = getDelta();
								while(run) {
									renderer.updateAI(delta);
									Usages.setUse("update AI");
									if (1000/32-delta > 0)
										try {
											Thread.sleep(1000/32-delta);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									delta = getDelta();
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
						};
						Thread updateAIThread = new Thread(updateAI);
						updateAIThread.start();
						isAIThreadRunning = true;
					}
					int delta = getDelta();
					while (run) {
						renderer.update(delta);
						Usages.setUse("update");
						if (!isAIThreadRunning) {
							renderer.updateAI(delta);
							Usages.setUse("update AI");
						}
						if (1000/64-delta > 0)
							try {
								Thread.sleep(1000/64-delta);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						delta = getDelta();
					}
				}
			};
			
			Thread updateThread = new Thread(update);
			updateThread.start();
	        isLogicThreadRunning = true;
	    }
		

		// Mise à jour graphiquetant que la fenêtre est ouverte
		while (!Display.isCloseRequested()) {
			Usages.setNewLoop();
			if (Keyboard.isKeyDown(Key.KEY_F5))
				this.setFullScreen(!fullScreen);
			if(!isLogicThreadRunning) {
				int delta = getDelta();
				getRenderer().update(delta);
				Usages.setUse("update");
				getRenderer().updateAI(delta);
				Usages.setUse("update AI");
			}
			getRenderer().render();
			Usages.setUse("render");
			updateFPS();
			Display.sync(60);
			Usages.setUse("wait");
			Display.update();
			Usages.setUse("display");
		}

		run = false;
		KELogger.logInfo(WINDOW_NAME + " termined!");
		KELogger.logInfo("This game use Kermekx Engine, Developed by Kevin MESSIAEN and Paul DUMONT.");
		Display.destroy();
		SoundManager.killALData();
		AL.destroy();

	}

	/**
	 * Met la fenêtre en mode pein écran/fenêtrer
	 * 
	 * @param fullScreen
	 *            vrai pour le plein écran, faux pour le fenêtrer
	 */
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

	/**
	 * Calcule le temps entre chaque boucle de mise à jours
	 * 
	 * @return temps d'écart en millisecondes
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Renvoie le temps du système
	 * 
	 * @return temps du système en millisecondes
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Mise à jour du calcule de FPS
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Usages.log();
			KELogger.logInfo("FPS : " + fps);
			lastFPS = getTime();
			fps = 0;
		}
		fps++;
	}

	/**
	 * Change la scene à afficher par le moteur Graphique
	 * 
	 * @param scene
	 *            à afficher
	 */
	public void setScene(Scene scene) {
		getRenderer().setScene(scene);
	}

	public Renderer getRenderer() {
		return renderer;
	}

}
