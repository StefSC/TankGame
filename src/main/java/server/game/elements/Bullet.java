package server.game.elements;

import java.awt.Point;

public class Bullet implements Runnable {

	private float damage;

	private Tank target;
	private Tank shooter;

	public Bullet(Tank target, Tank shooter, float damage) {
		this.target = target;
		this.shooter = shooter;
		this.damage = damage;
	}

	@Override
	public void run() {
		boolean isHit = isTargetHit();
		if (isHit) {
			this.target.getHit(computeDamageWithRange());
		}
	}

	private float computeDamageWithRange() {
		Point shooterLocation = this.shooter.getLocation();
		Point targetLocation = this.target.getLocation();

		float distance = (float) Math
				.sqrt((targetLocation.x - shooterLocation.x)
						* (targetLocation.x - shooterLocation.x)
						+ (targetLocation.y - shooterLocation.y)
						* (targetLocation.y - shooterLocation.y));
		if (distance == 0) {
			distance = 1;
		}
		return this.damage / distance;
	}

	private boolean isTargetHit() {
		boolean isHit = false;
		Point shooterLocation = this.shooter.getLocation();
		Point targetLocation = this.target.getLocation();
		Point aim = this.shooter.getAim();
		int targetHit = shooterLocation.x * targetLocation.y
				+ shooterLocation.y * aim.x + targetLocation.x * aim.y
				- shooterLocation.x * aim.y - shooterLocation.y
				* targetLocation.x - targetLocation.y * aim.x;
		if (targetHit == 0) {
			isHit = true;
		}
		return isHit;
	}

}
