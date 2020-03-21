package server.web.controllers;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import server.game.elements.Tank;
import server.game.elements.TankCache;

@RestController
public class GameController {
	
	private TankCache tankCache = TankCache.getTankCache();
	
	@GetMapping("/game/start/{id1}vs{id2}")
	public String startGame(@PathVariable(value = "id1") int id1, @PathVariable(value = "id2") int id2) {
		//TODO make tanks different threads that start their actions
		System.out.println("Prepping game");
		Instant starttime = Instant.now();
		Tank tank1 = tankCache.getTank(id1);
		Tank tank2 = tankCache.getTank(id2);
		startGame(tank1, tank2);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Instant endDateTime = Instant.now();
		return "The game lasted " + Duration.between(starttime, endDateTime).toMillis() + " milisecons!";
	}

	private void startGame(Tank tank1, Tank tank2) {
		System.out.println("Game started");
		CountDownLatch latch = new CountDownLatch(1);
		tank1.setLatch(latch);
		tank2.setLatch(latch);
		new Thread(tank1).start();
		new Thread(tank2).start();
		latch.countDown();
	}

}
