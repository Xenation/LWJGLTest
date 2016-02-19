package entities;

import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class Entity {
	
	protected Collider collider;
	
	private TexturedModel model;
	protected Vector3f position;
	protected float rX, rY, rZ;
	protected float scale;
	
	public boolean render;
	
	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		this.model = model;
		this.position = position;
		this.rX = rotX;
		this.rY = rotY;
		this.rZ = rotZ;
		this.scale = scale;
		this.render = true;
		this.collider = null;
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rX += dx;
		this.rY += dy;
		this.rZ += dz;
	}
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setRotation(float rx, float ry, float rz) {
		this.rX = rx;
		this.rY = ry;
		this.rZ = rz;
	}
	
	public void setRX(float rx) {
		this.rX = rx;
	}
	
	public void setRY(float ry) {
		this.rY = ry;
	}
	
	public void setRZ(float rz) {
		this.rZ = rz;
	}

	public float getRX() {
		return rX;
	}

	public float getRY() {
		return rY;
	}

	public float getRZ() {
		return rZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public void setCollider(Collider col) {
		this.collider = col;
	}
	public Collider getCollider() {
		return this.collider;
	}
	
}
