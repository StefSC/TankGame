package server.game.elements;

import java.awt.Point;

/**
 * @author sstef
 * Action that checks if the target is in the 90 degrees field of view of the tank.
 * If the target is not spotted, then the field of view is rotated 90 degrees clockwise.
 *
 */
public class CheckForTarget implements Action {

	/* (non-Javadoc)
	 * @see server.game.elements.Action#doAction(server.game.elements.Tank)
	 * 
	 */
	@Override
	public void doAction(Tank tank) {
		Point otherTankLocation = tank.getOtherTank().getLocation();
		Point aim = tank.getAim();
		Point location = tank.getLocation();
		System.out.println("Tank is at " + location.x + location.y);
		System.out.println("Aim is at " + aim.x + aim.y);
		System.out.println("Target is at " + otherTankLocation.x + otherTankLocation.y);
		if ((otherTankLocation.x > Math.min(location.x, aim.x) && (otherTankLocation.x < Math
				.max(location.x, aim.x)))
				&& (otherTankLocation.y > Math.min(location.y, aim.y) && (otherTankLocation.y < Math
						.max(location.y, aim.y)))) {
			tank.setTargetSpotted(true);
			System.out.println("Tank " + tank.getName() + " spotted the target!");
			aim.x = otherTankLocation.x;
			aim.y = otherTankLocation.y;

		} else {
			//Rotate clockwise
			//aim in quadrant 1
			if (aim.x > location.x && aim.y > location.y) {
				aim.y -=  2 * (aim.y - location.y);
				System.out.println("Tank " + tank.getName() + " rotating aim to c4!");
			}
			//aim in quadrant 4
			else if (aim.x > location.x && aim.y < location.y) {
				aim.x -=  2 * (aim.x - location.x);
				System.out.println("Tank " + tank.getName() + " rotating aim to c3!");
			}
			//aim in quadrant 3
			else if (aim.x < location.x && aim.y < location.y) {
				aim.y +=  2 * (location.y - aim.y);
				System.out.println("Tank " + tank.getName() + " rotating aim to c2!");
			}
			//aim in quadrant 2
			else if (aim.x < location.x && aim.y > location.y) {
				aim.x +=  2 * (location.x - aim.x);
				System.out.println("Tank " + tank.getName() + " rotating aim to c1!");
			}
		}
	}
}
