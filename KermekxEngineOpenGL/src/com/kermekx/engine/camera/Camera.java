package com.kermekx.engine.camera;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

import com.kermekx.engine.position.Matrix;
import com.kermekx.engine.position.Vector;

public class Camera {

	private Vector position;
	private Vector size;
	private boolean update = true;

	public Camera() {
		position = new Vector(0f, 0f);
		size = new Vector(Display.getWidth(), Display.getHeight());
	}

	public Camera(int x, int y, int width, int height) {
		position = new Vector(x, y);
		size = new Vector(Display.getWidth(), Display.getHeight());
	}

	public void setViewModel() {
		if(!update && size.getX() == Display.getWidth() && size.getY() == Display.getHeight())
			return;
		size.setX(Display.getWidth());
		size.setY(Display.getHeight());

		glViewport(0, 0, (int) size.getX(), (int) size.getY());

		glMatrixMode(GL_MODELVIEW);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(position.getX() - size.getX() / 2, position.getX() + size.getX() / 2, position.getY() + size.getY() / 2,
				position.getY() - size.getY() / 2, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		update = false;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2),
				(int) size.getX(), (int) size.getY());
	}
	
	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
		update = true;
	}

	public Vector getSize() {
		return size;
	}

	public void setSize(Vector size) {
		this.size = size;
		update = true;
	}

	public void translate(float tx, float ty) {
		position = Matrix.translation(position, tx, ty);
		update = true;
	}

}
