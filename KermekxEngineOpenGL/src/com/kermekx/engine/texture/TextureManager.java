package com.kermekx.engine.texture;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

public class TextureManager {

	private static final int BYTES_PER_PIXEL = 4;
	private static Map<String, Integer> textures = new HashMap<String, Integer>();

	public static int getTexture(String path) throws IOException {
		if (textures.get(path) != null)
			return textures.get(path);

		BufferedImage image = loadImage(path);

		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); // 4
																													// for
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));
				buffer.put((byte) ((pixel >> 8) & 0xFF));
				buffer.put((byte) (pixel & 0xFF));
				buffer.put((byte) ((pixel >> 24) & 0xFF));
			}
		}

		buffer.flip();
		glEnable(GL_TEXTURE_2D);

		int textureID = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, textureID);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE,
				buffer);
		
		//glBindTexture(GL_TEXTURE_2D, 1);

		return textureID;
	}

	private static BufferedImage loadImage(String path) throws IOException {
		// return ImageIO.read(KermekxEngine.class.getResource(path));
		return ImageIO.read(new File(path));
	}

}
