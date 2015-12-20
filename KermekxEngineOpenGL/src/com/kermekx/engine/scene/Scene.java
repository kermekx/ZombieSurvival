package com.kermekx.engine.scene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.kermekx.engine.camera.Camera;
import com.kermekx.engine.drawable.Drawable;

public abstract class Scene {

	private Camera camera = new Camera();
	private List<Drawable> drawables = new ArrayList<Drawable>();

	public void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}
	
	public void addDrawable(Collection<Drawable> drawables) {
		this.drawables.addAll(drawables);
	}
	
	

	public List<Drawable> getDrawables() {
		return drawables;
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
