package server.game.elements;

import java.util.ArrayList;

/**
 * Tank shoot action. When the tank shoots, the bullet will be in a new thread.
 * @author stef
 *
 */
public class ShootAction implements Action {

	private Tank target;

	public ShootAction(Tank tank) {
		this.target = tank;
	}

	public void doAction(Tank tank) {
		shoot(tank);
	}

	/**
	 * Start the thread(s) of the shot bullets.
	 * @param tank
	 */
	private void shoot(Tank tank) {
		ArrayList<Thread>bullets = new ArrayList<>(tank.getWeapons().size());
		for (Weapon weapon : tank.getWeapons()) {
			Thread bullet = new Thread(new Bullet(this.target, tank, weapon.getDamage()));
			bullets.add(bullet);
			bullet.start();
			System.out.println(tank.getName() + " shot using "
					+ weapon.getName());
		}
		for(Thread bullet : bullets) {
			try {
				bullet.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
