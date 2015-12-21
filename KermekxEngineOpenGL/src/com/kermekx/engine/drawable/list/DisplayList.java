package com.kermekx.engine.drawable.list;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDeleteLists;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Rectangle;
import java.util.Collection;

import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.position.Vector;

public class DisplayList {

	private final Vector position;
	private final Vector size;
	private final Rectangle bounds;
	private int listID;

	public DisplayList(Collection<Drawable> drawables, Vector position, Vector size) {
		this.position = position;
		this.size = size;
		bounds = new Rectangle((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
		init(drawables);
	}

	private void init(Collection<Drawable> drawables) {
		listID = glGenLists(1);

		glNewList(listID, GL_COMPILE);

		for (Drawable drawable : drawables) {
			float[] color = drawable.getColor();
			glColor3f(color[0], color[1], color[2]);

			int texture = drawable.getTextureId();
			if (texture != -1)
				;
			glBindTexture(GL_TEXTURE_2D, texture);
			int i = 0;
			float angle = drawable.getRotation();
			if (angle != 0) {
				glPushMatrix();
				Vector position = drawable.getPosition();
				glTranslatef(position.getX(), position.getY(), 0);
				glRotatef(angle, 0, 0, 1);
				glTranslatef(-position.getX(), -position.getY(), 0);
			}

			glBegin(GL_TRIANGLES);
			for (Vector vertex : drawable.getVertex()) {
				if (texture != -1 && i % 2 == 0)
					glTexCoord2f(vertex.getX(), vertex.getY());
				else
					glVertex2f(vertex.getX(), vertex.getY());
				i++;
			}
			glEnd();
			if (texture != -1)
				glBindTexture(GL_TEXTURE_2D, 0);
			if (angle != 0)
				glPopMatrix();
		}

		glEndList();
	}

	public void delete() {
		glDeleteLists(listID, 1);
	}
	
	public boolean should(Rectangle bounds) {
		return this.bounds.intersects(bounds);
	}

	public int getListID() {
		return listID;
	}
}
