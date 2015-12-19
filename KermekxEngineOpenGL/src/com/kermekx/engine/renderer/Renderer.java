package com.kermekx.engine.renderer;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Rectangle;

import com.kermekx.engine.camera.Camera;
import com.kermekx.engine.drawable.Drawable;
import com.kermekx.engine.position.Vector;
import com.kermekx.engine.scene.Scene;

public class Renderer {

	private Scene scene;

	public void render() {
		if (scene == null)
			return;
		
		Rectangle bounds = scene.getCamera().getBounds();
		
		scene.getCamera().setViewModel();
		glClear(GL_COLOR_BUFFER_BIT);
		for (Drawable drawable : scene.getDrawables()) {
			if (drawable.shouldRender(bounds)) {
				float[] color = drawable.getColor();
				glColor3f(color[0], color[1], color[2]);
				glColor3f(1, 1, 1);

				int texture = drawable.getTextureId();
				if (texture != -1);
					glBindTexture(GL_TEXTURE_2D, texture);
				int i = 0;
				float angle = drawable.getRotation();
				if (angle != 0) {
					glPushMatrix();
					Vector position = drawable.getPosition();
					glTranslatef(position.getX(), position.getY(), 0);
					glRotatef( angle, 0, 0, 1 );
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
		}
		glFlush();
	}

	public void setScene(Scene scene) {
		if (scene.getCamera() == null)
			scene.setCamera(new Camera());
		this.scene = scene;
	}

	public void update(int delta) {
		if(scene != null)
			scene.update(delta);
	}

}
