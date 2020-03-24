package server.game.elements;

import java.time.Duration;
import java.time.Instant;

import javax.annotation.ManagedBean;

import org.springframework.web.context.annotation.RequestScope;

/**
 * The game engine. The engine will take the tanks and start the battle.
 * Each tank is a clone of the tank in the cache, so multiple battles can take place with the same tanks.
 * @author stef
 *
 */
@RequestScope
@ManagedBean
public class GameEngine {

	private TankCache tankCache = TankCache.getTankCache();

	public String startGame(int id1, int id2) {

		System.out.println("Prepping game");
		Instant starttime = Instant.now();

		Tank tank1 = null;
		Tank tank2 = null;
		try {
			tank1 = (Tank) tankCache.getTank(id1).clone();
			tank2 = (Tank) tankCache.getTank(id2).clone();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		tank1.setOtherTank(tank2);
		tank2.setOtherTank(tank1);

		Thread thread1 = new Thread(tank1);
		thread1.start();
		Thread thread2 = new Thread(tank2);
		thread2.start();

		System.out.println("Game started");
		tank1.receiveTurn(1);

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Instant endDateTime = Instant.now();
		
		StringBuilder response = new StringBuilder();
		response.append(tank1.getGameResult())
			.append("\n")
			.append(tank2.getGameResult())
			.append("\n")
			.append("The game lasted " + Duration.between(starttime, endDateTime).toMillis() + " milisecons!");
		
		
		return response.toString();
	}

}
