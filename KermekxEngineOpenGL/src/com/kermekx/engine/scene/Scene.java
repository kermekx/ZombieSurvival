package com.kermekx.engine.scene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.kermekx.engine.camera.Camera;
import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.list.DisplayList;
import com.kermekx.engine.hud.HUD;

public abstract class Scene {

	/**
	 * Camera d'affichage de la scene
	 */
	private Camera camera = new Camera();

	/**
	 * liste des drawable de la scene à afficher
	 */
	private List<Drawable>[] drawables = new List[20];

	/**
	 * liste des display de la scene à afficher
	 */
	private List<DisplayList> displayLists = new ArrayList<DisplayList>();

	private List<HUD> huds = new ArrayList<HUD>();

	public Scene() {
		for (int i = 0; i < drawables.length; i++) {
			drawables[i] = new ArrayList<Drawable>();
		}
	}

	/**
	 * ajoute un drawable à la scene
	 * 
	 * @param drawable
	 *            drawable à ajouter
	 */
	public void addDrawable(Drawable drawable) {
		drawables[(int) drawable.getPosition().getZ() + 10].add(drawable);
	}

	/**
	 * ajoute une liste de drawables à la scene
	 * 
	 * @param drawables
	 *            drawables à ajouter
	 */
	public void addDrawable(Collection<Drawable> drawables) {
		for (Drawable drawable : drawables)
			addDrawable(drawable);
	}
	
	public void removeDrawable(Drawable drawable) {
		drawables[(int) drawable.getPosition().getZ() + 10].remove(drawable);
	}

	/**
	 * renvoie la liste des drawables de la scene
	 * 
	 * @return liste des drawables
	 */
	public List<Drawable>[] getDrawables() {
		return drawables;
	}

	/**
	 * ajoute un display à la scene
	 * 
	 * @param dl
	 *            display à ajouter
	 */
	public void addDisplayList(DisplayList dl) {
		displayLists.add(dl);
	}

	/**
	 * ajoute une liste de displays à la scene
	 * 
	 * @param dl
	 *            liste de displays à ajouter
	 */
	public void addDisplayList(Collection<DisplayList> dl) {
		displayLists.addAll(dl);
	}

	/**
	 * renvoye la liste des displays de la scene
	 * 
	 * @return liste des displays
	 */
	public List<DisplayList> getDisplayLists() {
		return displayLists;
	}

	public void addHud(HUD hud) {
		huds.add(hud);
	}

	public List<HUD> getHuds() {
		return huds;
	}

	/**
	 * renvoye la camera de la scene
	 * 
	 * @return camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * change la camera de la scene
	 * 
	 * @param camera
	 *            camera
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	/**
	 * met à jour la scene
	 * 
	 * @param delta
	 *            temps en millisecondes depuis la dernière mise à jour
	 */
	public void update(int delta) {
		for (List<Drawable> dl : drawables)
			for (Drawable d : dl)
				d.update(delta);
		for (HUD hud : huds)
			hud.update(delta);
	}

	/**
	 * renvoie si la touche est appuier
	 * 
	 * @param key
	 *            touche demander
	 * @return vrai si la touche demander est appuier, faux sinon
	 */
	public boolean keyPressed(int key) {
		return Keyboard.isKeyDown(key);
	}

}
