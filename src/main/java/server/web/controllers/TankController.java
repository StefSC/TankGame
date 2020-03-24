package server.web.controllers;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server.game.elements.Tank;
import server.game.elements.TankCache;

/**
 * @author stef
 *
 */
@RestController()
public class TankController {
	
	private TankCache tankCache = TankCache.getTankCache();
	private final AtomicInteger counter = new AtomicInteger();
	
	/**
	 * Endpoint for creating a new tank
	 * @param id
	 * @param health
	 * @return
	 */
	@GetMapping("/tank/new")
	public Tank getNewTank(@RequestParam(value = "id", defaultValue = "1") String id,
			@RequestParam(value = "health", defaultValue = "100") int health) {
		this.tankCache.addTank(counter.incrementAndGet(), new Tank(counter.get(), id, health));
		return tankCache.getTank(counter.get());
	}
	
	
	/**
	 * Endpoint for getting the tank with id
	 * @param id
	 * @return
	 */
	@GetMapping("/tank/{id}")
	public Tank getTank(@PathVariable(value = "id") int id) {
		return this.tankCache.getTank(id);
	}
	
	//TODO update tank
	
	@GetMapping("/tank/{id}/save")
	public Tank saveTank(@PathVariable(value = "id") int id) {
		//TODO save tank in database
		return this.tankCache.getTank(id);
	}
	
	@GetMapping("/tanks/load")
	public Tank loadTanks() {
		//TODO load tanks from database
		return this.tankCache.getTank(1);
	}

}
