package server.game.elements;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author sstef
 * Tank class. After the game is started, this class will run in a separate thread.
 * The "AI" of the tank is represented by the run command. The "AI" decides what actions the tank should do.
 * The actions executed by the tank are stored in the actionsHistory field. (Wanted to store them in mongo
 * and create a service for retrieval).
 * A tank can do an actions if the turnQueue has the value 1.
 * If the turnQueue has value 0 it means that the tank was destroyed.
 * A value of 2 means that the other tank was eliminated.
 * 
 */
public class Tank implements Runnable {

	private final long id;
	private final String name;
	private ArrayList<Weapon> weapons;
	private Point location;
	private Point aim;
	private boolean targetSpotted = false;
	private float health;
	private ArrayList<Action> actionsHistory;
	private String gameResult;

	// If in the queue we have 0 it means we lost
	// If in the queue we have 1 it means we continue
	// If in the queue we have 2 it means we won
	private BlockingQueue<Integer> turnQueue = new LinkedBlockingQueue<>(1);
	private Tank otherTank;

	public Tank(long id, String name, int health) {
		this.id = id;
		this.name = name;
		this.location = new Point(0, 0);
		this.aim = new Point(-2, -2);
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

	public float getHealth() {
		return this.health;
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

	public void setTargetSpotted(boolean spotted) {
		this.targetSpotted = spotted;
		this.aim = this.otherTank.location;
	}

	public void setOtherTank(Tank tank) {
		this.otherTank = tank;
	}
	
	public Tank getOtherTank() {
		return this.otherTank;
	}

	public void doAction(Action action) {
		if (this.actionsHistory == null) {
			this.actionsHistory = new ArrayList<Action>();
		}
		this.actionsHistory.add(action);
		action.doAction(this);
	}
	
	public String getGameResult() {
		return this.gameResult;
	}

	public void receiveTurn(Integer won) {
		this.turnQueue.add(won);
	}

	public void clearTurnBecauseOfWin(Integer reson) {
		this.turnQueue.clear();
		this.turnQueue.add(reson);
	}

	public void run() {
		// TODO actual game

		try {

			while (true) {

				Integer won = this.turnQueue.take();
				if (won != null && won == 1) {

					int action1 = (int) (Math.random() * 2);
					if (this.targetSpotted == false) {
						doAction(new MoveAction());
					} else if(action1 == 1) {
						doAction(new ShootAction(this.otherTank));
					}
					doAction(new CheckForTarget());
					this.otherTank.receiveTurn(1);
				} else {
					if (won == 2) {
						this.gameResult = "Tank " + this.name + "won in " + this.actionsHistory.size() + " turns!";
						System.out.println("Tank " + this.name + " won in "+ this.actionsHistory.size() +" turns!");
					} else if (won == 0) {
						this.gameResult = "Tank " + this.name + " lost";
					}
					break;
				}

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void getHit(float damage) {
		if (this.health > 0) {
			this.health = this.health - damage;
			System.out.println("Tank " + this.name + " got hit and has "
					+ this.health + " left!");
			if (this.health <= 0) {
				// clear the other tanks actions and tell it that it won
				this.otherTank.clearTurnBecauseOfWin(2);
				// stop future actions
				this.turnQueue.add(0);
			}
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Tank clone = new Tank(getId(), getName(), (int)this.health);
		clone.weapons = this.weapons;
		clone.location = (Point) this.location.clone();
		clone.aim = (Point) this.aim.clone();
		return clone;
	}
	
	
	
}
