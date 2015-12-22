package com.kermekx.zombiesurvival.ia;

import com.kermekx.zombiesurvival.entity.Entity;
import com.kermekx.zombiesurvival.entity.Zombie;
import com.kermekx.zombiesurvival.scene.GameScene;

public class Follow extends BaseIA {

	private Entity follow;
	private LookAt lookAt;
	private GameScene context;
	
	
	public Follow( GameScene context, Entity entity, Entity follow) {
		super(entity);
		this.context = context;
		this.follow = follow;
		lookAt = new LookAt(entity, follow);
		
	}

	@Override
	public void update(int delta) {
		lookAt.update(delta);
		entity.translate(delta * Zombie.MOVEMENT_SPEED, 0);
		
		for(Entity e : ((GameScene)entity.getContext()).getEntities())
			if(entity.contains(e) && !(e instanceof Zombie))
				entity.translate(-delta * (Zombie.MOVEMENT_SPEED + 0.005f), 0);
		
		
	}

}
