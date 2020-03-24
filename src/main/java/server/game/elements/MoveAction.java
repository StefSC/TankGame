package server.game.elements;

import java.awt.Point;

public class MoveAction implements Action {

	public void doAction(Tank tank) {
		move(tank);
		System.out.println(tank.getName() + " moved to " + tank.getLocation().x + tank.getLocation().y);
	}

	private void move(Tank tank) {

		int direction = (int) (Math.random()*9);
		switch (direction) {
		case 0:
			moveNorthWest(tank);
			break;
		case 1:
			moveNorth(tank);
			break;
		case 2:
			moveNorthEast(tank);
			break;
		case 3:
			moveEast(tank);
			break;
		case 4:
			moveSouthEast(tank);
			break;
		case 5:
			moveSouth(tank);
			break;
		case 6:
			moveSouthWest(tank);
			break;
		case 7:
			moveWest(tank);
			break;
		case 8:
			idle();
			break;
		default:
			System.out.println("Idle");
			break;
		}
	}
	
	private void moveNorth(Tank tank) {
		if(tank.getLocation().y == 2) {
			return;
		}
		tank.getLocation().y++;
		tank.getAim().y++;
		System.out.println("Moving north");
	}
	
	private void moveSouth(Tank tank) {
		if(tank.getLocation().y == -2) {
			return;
		}
		tank.getLocation().y--;
		tank.getAim().y--;
		System.out.println("Moving south");
	}
	
	private void moveEast(Tank tank) {
		if(tank.getLocation().x ==2) {
			return;
		}
		tank.getLocation().x++;
		tank.getAim().x++;
		System.out.println("Moving east");
	}
	
	private void moveWest(Tank tank) {
		if(tank.getLocation().x == -2) {
			return;
		}
		tank.getLocation().x--;
		tank.getAim().x--;
		System.out.println("Moving west");
	}
	
	private void moveNorthWest(Tank tank) {
		moveNorth(tank);
		moveWest(tank);
	}
	
	private void moveNorthEast(Tank tank) {
		moveNorth(tank);
		moveEast(tank);
	}
	
	private void moveSouthWest(Tank tank) {
		moveSouth(tank);
		moveWest(tank);
	}
	
	private void moveSouthEast(Tank tank) {
		moveSouth(tank);
		moveEast(tank);
	}
	
	private void idle() {
		System.out.println("Sitting idle...");
	}
	
}
