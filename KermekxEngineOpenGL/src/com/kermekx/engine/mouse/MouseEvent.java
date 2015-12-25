package com.kermekx.engine.mouse;

import com.kermekx.engine.position.Vector;

public class MouseEvent {
	
	public static final int LEFT_BUTTON = 1;
	public static final int RIGHT_CLICK = 2;
	
	private final Vector position;
	private final int click;
	
	public MouseEvent(Vector position, int click) {
		this.position = position;
		this.click = click;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public float getX() {
		return position.getX();
	}
	
	public float getY() {
		return position.getY();
	}
	
	public int getClick() {
		return click;
	}
	
	public boolean isButtonDown(int boutton) {
		return (click & boutton) != 0;
	}

}
