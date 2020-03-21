package server.game.elements;

import java.util.HashMap;

import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
public class TankCache {
	
	private static TankCache INSTANCE;
	private HashMap<Integer, Tank> tankCache;
	
	private TankCache() {
	}
	
	public static TankCache getTankCache() {
		if(INSTANCE == null) {
			INSTANCE = new TankCache();
		}
		return INSTANCE;
	}
	
	public void addTank(Integer id, Tank tank) {
		if(this.tankCache == null) {
			this.tankCache = new HashMap<Integer, Tank>();
		}
		this.tankCache.put(id, tank);
	}
	
	public Tank getTank(Integer id) {
		System.out.println("There are " + this.tankCache.keySet().size() + " tanks!");
		System.out.println("Returning tank with id " + id);
		return this.tankCache.getOrDefault(id, null);
	}

}
