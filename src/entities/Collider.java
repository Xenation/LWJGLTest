package entities;

import org.lwjgl.util.vector.Vector3f;

public class Collider {
	
	private float x, y, z;
	private float w, h, d;
	
	private Entity entity; 
	
	public Collider(Entity ent) {
		this.x = 3;
		this.y = 3;
		this.z = 3;
		this.w = 6;
		this.h = 6;
		this.d = 6;
		this.entity = ent;
	}
	public Collider(Entity ent, float x, float y, float z, float w, float h, float d) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.h = h;
		this.d = d;
		this.entity = ent;
	}
	public Collider(Entity ent, Vector3f pos, Vector3f size) {
		this.x = pos.x;
		this.y = pos.y;
		this.z = pos.z;
		this.w = size.x;
		this.h = size.y;
		this.d = size.z;
		this.entity = ent;
	}
	
	public boolean isColliding(Collider col) {
		if ((col.getWX() >= this.getWX() + this.w) || (col.getWX() + col.w <= this.getWX())
				|| (col.getWY() >= this.getWY() + this.h) || (col.getWY() + col.h <= this.getWY())
				|| (col.getWZ() >= this.getWZ() + this.d) || (col.getWZ() + col.d <= this.getWZ())) {
			return false;
		}
		return true;
	}
	
	public float getWX() {
		return this.entity.position.x + this.x;
	}
	public float getWY() {
		return this.entity.position.y + this.y;
	}
	public float getWZ() {
		return this.entity.position.z + this.z;
	}
	
}
