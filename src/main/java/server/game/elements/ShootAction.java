package server.game.elements;

public class ShootAction implements Action {

	public void doAction(Tank tank) {
		shoot(tank);
	}

	private void shoot(Tank tank) {
		for(Weapon weapon : tank.getWeapons()) {
			weapon.shoot(tank.getAim());
			System.out.println(tank.getName() + " shot using " + weapon.getName());
		}
	}

}
