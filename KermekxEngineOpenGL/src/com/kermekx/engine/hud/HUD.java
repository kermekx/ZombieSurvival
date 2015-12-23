package com.kermekx.engine.hud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.drawable.list.DisplayList;

public abstract class HUD {
	
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

	public abstract void update(int delta);

}
