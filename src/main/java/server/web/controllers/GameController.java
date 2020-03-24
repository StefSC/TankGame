package server.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import server.game.elements.GameEngine;

@RestController
public class GameController {
	
	@Autowired
	private GameEngine gameEngine;
	
	@GetMapping("/game/start/{id1}vs{id2}")
	public String startGame(@PathVariable(value = "id1") int id1, @PathVariable(value = "id2") int id2) {
		//TODO make tanks different threads that start their actions
		return this.gameEngine.startGame(id1, id2);
	}

}
