package server.game.elements;

import java.awt.Point;

public class MoveAction implements Action {

	public void doAction(Tank tank) {
		move(tank.getLocation());
		System.out.println(tank.getName() + " moved to " + tank.getLocation().x + tank.getLocation().y);
	}

	private void move(Point location) {

		int direction = (int) (Math.random()*9);
		switch (direction) {
		case 0:
			moveNorthWest(location);
			break;
		case 1:
			moveNorth(location);
			break;
		case 2:
			moveNorthEast(location);
			break;
		case 3:
			moveEast(location);
			break;
		case 4:
			moveSouthEast(location);
			break;
		case 5:
			moveSouth(location);
			break;
		case 6:
			moveSouthWest(location);
			break;
		case 7:
			moveWest(location);
			break;
		case 8:
			idle();
			break;
		default:
			System.out.println("Idle");
			break;
		}
	}
	
	private void moveNorth(Point location) {
		if(location.y == 2) {
			return;
		}
		location.y++;
		System.out.println("Moving north");
	}
	
	private void moveSouth(Point location) {
		if(location.y == -2) {
			return;
		}
		location.y--;
		System.out.println("Moving south");
	}
	
	private void moveEast(Point location) {
		if(location.x ==2) {
			return;
		}
		location.x++;
		System.out.println("Moving est");
	}
	
	private void moveWest(Point location) {
		if(location.x == -2) {
			return;
		}
		location.x--;
		System.out.println("Moving west");
	}
	
	private void moveNorthWest(Point location) {
		moveNorth(location);
		moveWest(location);
	}
	
	private void moveNorthEast(Point location) {
		moveNorth(location);
		moveEast(location);
	}
	
	private void moveSouthWest(Point location) {
		moveSouth(location);
		moveWest(location);
	}
	
	private void moveSouthEast(Point location) {
		moveSouth(location);
		moveEast(location);
	}
	
	private void idle() {
		System.out.println("Sitting idle...");
	}
	
}
