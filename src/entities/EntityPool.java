package entities;

import java.util.ArrayList;
import java.util.List;

public class EntityPool {
	
	private List<Entity> pool;
	
	public EntityPool() {
		this.pool = new ArrayList<Entity>();
	}
	
	public void add(Entity ent) {
		this.pool.add(ent);
	}
	public void add(List<Entity> entities) {
		this.pool.addAll(entities);
	}
	
	public void remove(Entity ent) {
		this.pool.remove(ent);
	}
	
	public void removeIndex(int index) {
		this.pool.remove(index);
	}
	
	public void getIndexOf(Entity ent) {
		this.pool.indexOf(ent);
	}
	
	public List<Entity> getPool() {
		return this.pool;
	}
}
