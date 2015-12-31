package com.kermekx.zombiesurvival.entity.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.scene.GameScene;

public class DecorationLoader {
	
	private static final String DECORATION_PATH = "assets/chunk/decoration/";
	private static final String DECORATION_FORMAT = ".dec";
	private static final String XY_SEPARATOR = ",";
	
	public static void load(GameScene scene, int x, int y) {
		DecorationBuilder.load();
		
		try {
			Scanner scanner = new Scanner(new File(DECORATION_PATH + x + XY_SEPARATOR + y + DECORATION_FORMAT));

			scanner.useDelimiter(";");
			String data;

			while (scanner.hasNext()) {
				data = scanner.next();
				String[] datas = data.split(":");
				int id = Integer.parseInt(datas[0]);
				datas = datas[1].split(",");
				Vector position = new Vector(x * 16 * 128 + Integer.parseInt(datas[0]), y * 16 * 128 + Integer.parseInt(datas[1]));
				scene.addEntity(DecorationBuilder.createDecoration(id, position, Integer.parseInt(datas[2])));
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
