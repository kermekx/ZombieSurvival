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
	 * liste des drawable de la scene � afficher
	 */
	private List<Drawable> drawables = new ArrayList<Drawable>();
	
	/**
	 * liste des display de la scene � afficher
	 */
	private List<DisplayList> displayLists = new ArrayList<DisplayList>();
	
	private List<HUD> huds = new ArrayList<HUD>();
	
	/**
	 * ajoute un drawable � la scene
	 * @param drawable drawable � ajouter
	 */
	public void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}
	
	/**
	 * ajoute une liste de drawables � la scene
	 * @param drawables drawables � ajouter
	 */
	public void addDrawable(Collection<Drawable> drawables) {
		this.drawables.addAll(drawables);
	}
	
	/**
	 * renvoie la liste des drawables de la scene
	 * @return liste des drawables
	 */
	public List<Drawable> getDrawables() {
		return drawables;
	}
	
	/**
	 * ajoute un display � la scene
	 * @param dl display � ajouter
	 */
	public void addDisplayList(DisplayList dl) {
		displayLists.add(dl);
	}
	
	/**
	 * ajoute une liste de displays � la scene
	 * @param dl liste de displays � ajouter
	 */
	public void addDisplayList(Collection<DisplayList> dl) {
		displayLists.addAll(dl);
	}
	
	/**
	 * renvoye la liste des displays de la scene
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
	 * @return camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * change la camera de la scene
	 * @param camera camera
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	/**
	 * met � jour la scene
	 * @param delta temps en millisecondes depuis la derni�re mise � jour
	 */
	public void update(int delta) {
		for(Drawable d : drawables)
			d.update(delta);
		for(HUD hud : huds)
			hud.update(delta);
	}

	/**
	 * renvoie si la touche est appuier
	 * @param key touche demander
	 * @return vrai si la touche demander est appuier, faux sinon
	 */
	public boolean keyPressed(int key) {
		return Keyboard.isKeyDown(key);
	}

}
