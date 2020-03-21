package server.game.elements;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Tank implements Runnable {

	private final long id;
	private final String name;
	private ArrayList<Weapon> weapons;
	private Point location;
	private Point aim;
	private int health;
	private ArrayList<Action> actionsHistory;
	private CountDownLatch latch;

	public Tank(long id, String name, int health) {
		this.id = id;
		this.name = name;
		this.location = new Point(0, 0);
		this.aim = new Point(-1, -1);
		this.health = health;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Point getLocation() {
		return this.location;
	}

	public Point getAim() {
		return this.aim;
	}

	public int getHealth() {
		return this.health;
	}
	
	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	public void addWeapon(Weapon weapon) {
		if (this.weapons == null) {
			this.weapons = new ArrayList<Weapon>();
		}
		this.weapons.add(weapon);
	}

	public ArrayList<Weapon> getWeapons() {
		return this.weapons;
	}

	public void doAction(Action action) {
		if (this.actionsHistory == null) {
			this.actionsHistory = new ArrayList<Action>();
		}
		this.actionsHistory.add(action);
		action.doAction(this);
	}

	public void run() {
		// TODO actual game
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Tank " + name + " started!");
		}
		
		int steps = 20;
		while (steps > 0) {
			int action1 = (int) (Math.random() * 2);
			if (action1 == 0) {
				doAction(new MoveAction());
			}
			if (action1 == 1) {
				doAction(new ShootAction());
			}
			int action2 = (int) (Math.random() * 2);
			if (action2 == 0) {
				doAction(new MoveAction());
			}
			if (action2 == 1) {
				doAction(new ShootAction());
			}
			steps--;
		}
	}

}
