package server.game.elements;

import java.time.Duration;
import java.time.Instant;

import javax.annotation.ManagedBean;

import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@ManagedBean
public class GameEngine {

	private TankCache tankCache = TankCache.getTankCache();

	public String startGame(int id1, int id2) {

		System.out.println("Prepping game");
		Instant starttime = Instant.now();

		Tank tank1 = tankCache.getTank(id1);
		Tank tank2 = tankCache.getTank(id2);

		tank1.setOtherTank(tank2);
		tank2.setOtherTank(tank1);

		Thread thread1 = new Thread(tank1);
		thread1.start();
		Thread thread2 = new Thread(tank2);
		thread2.start();

		System.out.println("Game started");
		tank1.receiveTurn(1);

		try {
			Thread.sleep(1000);

			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Instant endDateTime = Instant.now();
		return "The game lasted "
				+ Duration.between(starttime, endDateTime).toMillis()
				+ " milisecons!";
	}

}
