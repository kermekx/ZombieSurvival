package com.kermekx.engine.scene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.kermekx.engine.camera.Camera;
import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.list.DisplayList;

public abstract class Scene {

	private Camera camera = new Camera();
	private List<Drawable> drawables = new ArrayList<Drawable>();
	private List<DisplayList> displayLists = new ArrayList<DisplayList>();
	
	public void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}
	
	public void addDrawable(Collection<Drawable> drawables) {
		this.drawables.addAll(drawables);
	}
	
	public List<Drawable> getDrawables() {
		return drawables;
	}
	
	public void addDisplayList(DisplayList dl) {
		displayLists.add(dl);
	}
	
	public void addDisplayList(Collection<DisplayList> dl) {
		displayLists.addAll(dl);
	}
	
	public List<DisplayList> getDisplayLists() {
		return displayLists;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public void update(int delta) {
		for(Drawable d : drawables)
			d.update(delta);
	}

	public boolean keyPressed(int key) {
		return Keyboard.isKeyDown(key);
	}

}
