package com.kermekx.zombiesurvival.scene;

import com.kermekx.engine.color.ColorBuilder;
import com.kermekx.engine.drawable.Rectangle2D;
import com.kermekx.engine.listener.LoadingListener;
import com.kermekx.engine.scene.Scene;

public class LoadingScene extends Scene implements LoadingListener {

	Rectangle2D progressBar = new Rectangle2D(-50, -10, 0, 20, ColorBuilder.RED);

	public LoadingScene() {
		addDrawable(progressBar);
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
	}
	
	@Override
	public void onUpdate(double progress) {
		getDrawables().remove(progressBar);
		progressBar = new Rectangle2D(-50 + (float) progress * 50f, -10, (float) progress * 100f, 20,
				ColorBuilder.RED);
		addDrawable(progressBar);
	}

}
