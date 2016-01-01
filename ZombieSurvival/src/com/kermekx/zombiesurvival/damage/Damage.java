package com.kermekx.zombiesurvival.damage;

import com.kermekx.zombiesurvival.entity.Entity;

public class Damage {
	
	private final Entity damager;
	private final DamageSource source;
	private final float damage;
	
	public Damage(Entity damager, DamageSource source, int damage) {
		this.damager = damager;
		this.source = source;
		this.damage = damage;
	}

	public Entity getDamager() {
		return damager;
	}

	public DamageSource getSource() {
		return source;
	}

	public float getDamage() {
		return damage;
	}
	
}
