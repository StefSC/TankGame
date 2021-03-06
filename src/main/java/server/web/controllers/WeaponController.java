package server.web.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import server.game.elements.Tank;
import server.game.elements.TankCache;
import server.game.elements.Weapon;

/**
 * @author stef
 *
 */
@RestController
public class WeaponController {
	
	private TankCache tankCache = TankCache.getTankCache();
	
	
	/**
	 * Endpoint for adding a weapon to a tank
	 * @param weapon
	 * @param id
	 */
	@PostMapping("/tank/{id}/add/weapon")
	public void addWeapon(@RequestBody Weapon weapon, @PathVariable(name = "id") int id) {
		Tank tank = this.tankCache.getTank(id);
		tank.addWeapon(weapon);
	}

}
