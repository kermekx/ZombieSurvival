package com.kermekx.zombiesurvival.ai;

import com.kermekx.engine.position.Vector;
import com.kermekx.zombiesurvival.entity.Entity;

public class LookAt extends BaseAI {

	private Entity lookAt;

	public LookAt(Entity entity, Entity lookAt) {
		super(entity);
		this.lookAt = lookAt;
	}

	@Override
	public void update(int delta) {
		Vector entityP = entity.getPosition();
		Vector lookAtP = lookAt.getPosition();
		entity.setRotation(
				(float) -Math.toDegrees(Math.atan2(lookAtP.getX() - entityP.getX(), lookAtP.getY() - entityP.getY())) + 90);
	}

}
