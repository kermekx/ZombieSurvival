package com.kermekx.engine.drawable;

import java.awt.Rectangle;

import com.kermekx.engine.position.Vector;

public interface Drawable {

	/**
	 * Demande la liste des vertices d'affichage
	 * 
	 * @return liste de vecteur de vetices
	 */
	public Vector[] getVertex();

	/**
	 * Renvoie la couleur d'affichage
	 * 
	 * @return vecteur de 3 flotants de la couleur
	 */
	public float[] getColor();

	/**
	 * renvoie l'identifiant de la texture
	 * 
	 * @return id de la texture
	 */
	public int getTextureId();

	/**
	 * Change la texture d'affichage
	 * 
	 * @param textureId
	 *            id de la texture
	 */
	public void setTexture(int textureId);

	/**
	 * Renvoie l'angle de rotation de la figure en degres
	 * 
	 * @return angle en degres
	 */
	public float getRotation();

	/**
	 * Change l'angle de rotation de la figure en degres
	 * 
	 * @param angle
	 *            rotaion en degres
	 */
	public void setRotation(float angle);

	/**
	 * renvoie le vecteur position de la figure
	 * 
	 * @return position de la figure
	 */
	public Vector getPosition();

	/**
	 * Renvoie si le drawable devrai être afficher en fonction des bounds de la
	 * camera
	 * 
	 * @param bounds
	 *            Rectangle de la camera
	 * @return vrai si l'affichage devrai etre fait, faux sinon
	 */
	public boolean shouldRender(Rectangle bounds);

	/**
	 * met à jour le drawable en fonction de delta
	 * 
	 * @param delta
	 *            temps depuis le dernier appel en millisecondes
	 */
	public void update(int delta);

	/**
	 * effectue une translation sur la position du drawable en fonction de
	 * l'angle de ce drawable
	 * 
	 * @param tx
	 *            translation sur l'axe X du drawable (horizontalement)
	 * @param ty
	 *            translation sur l'axe Y du drawable (verticalement)
	 */
	public void translate(float tx, float ty);

}
