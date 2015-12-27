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

	/**
	 * Vecteur 2d de la position du centre de la camera
	 */
	private Vector position;
	/**
	 * Vecteur 2d de la taille de la camera Qui est toujours la taille de la
	 * fenêtre
	 */
	private Vector size;

	/**
	 * Crée une camera de position (0;0) et de la taille de la fenêtre
	 */
	public Camera() {
		position = new Vector(0f, 0f);
		size = new Vector(Display.getWidth(), Display.getHeight());
	}

	/**
	 * Crée une camera en fonction des paramètre
	 * 
	 * @param x
	 *            coordonné X du centre de la camera
	 * @param y
	 *            coordonné Y du centre de la camera
	 */
	public Camera(int x, int y) {
		position = new Vector(x, y);
		size = new Vector(Display.getWidth(), Display.getHeight());
	}

	/**
	 * Met à jour la matrice de vue si : La camera à été changer (update ==
	 * true) Ou la taille de la fenêtre à été changer
	 */
	public void setViewModel() {
		// if (!update && size.getX() == Display.getWidth() && size.getY() ==
		// Display.getHeight())
		// return;
		size.setX(Display.getWidth());
		size.setY(Display.getHeight());

		glViewport(0, 0, (int) size.getX(), (int) size.getY());

		glMatrixMode(GL_MODELVIEW);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(position.getX() - size.getX() / 2, position.getX() + size.getX() / 2, position.getY() + size.getY() / 2,
				position.getY() - size.getY() / 2, 10, -10);
		glMatrixMode(GL_MODELVIEW);
	}

	public void setViewModelHUD() {
		// if (!update && size.getX() == Display.getWidth() && size.getY() ==
		// Display.getHeight())
		// return;
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 1920, 1080, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}

	/**
	 * Renvoie un rectangle équivalent à la hitbox de la camera
	 * 
	 * @return Bounds de la camera
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2),
				(int) size.getX(), (int) size.getY());
	}

	/**
	 * Renvoie le vecteur de la position du centre de la camera
	 * 
	 * @return centre de la camera
	 */
	public Vector getPosition() {
		return position;
	}

	/**
	 * Change la position du centre de la camera
	 * 
	 * @param position
	 *            centre de la camera
	 */
	public void setPosition(Vector position) {
		this.position = position;
	}

	/**
	 * Renvoie la taille de la camera
	 * 
	 * @return taille de la camera
	 */
	public Vector getSize() {
		return size;
	}

	/**
	 * effectue une translation sur la position d la camera camera
	 * 
	 * @param tx
	 *            translation sur l'axe X
	 * @param ty
	 *            translation sur l'axe Y
	 */
	public void translate(float tx, float ty) {
		position = Matrix.translation(position, tx, ty);
	}

}
