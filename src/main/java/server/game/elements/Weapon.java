package server.game.elements;

/**
 * Class for defining a weapon.
 * @author stef
 *
 */
public class Weapon {
	
	private String name;
	private float damage;
	private float range;
	
	public Weapon(String name, float damage, float range) {
		this.name = name;
		this.damage = damage;
		this.range = range;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}
	
}
