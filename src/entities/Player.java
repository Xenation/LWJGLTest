package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderDisplay.DisplayManager;
import terrains.Terrain;

public class Player extends Entity {
	
	private static final float DEF_SPEED = 20;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30;
	
	private static final float TERRAIN_HEIGHT = 0;
	
	private boolean isInAir = false;
	
	private Vector3f speedVector = new Vector3f();
	private float speed;
	
	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		this.speed = DEF_SPEED;
	}
	
	public void move(Terrain terrain) {
		checkInputs();
		speedVector.y += GRAVITY * DisplayManager.getFrameTimeSeconds();
		float dx = speedVector.x * DisplayManager.getFrameTimeSeconds() * speed;
		float dz = speedVector.z * DisplayManager.getFrameTimeSeconds() * speed;
		float dy = speedVector.y * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(dx, dy, dz);
		
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if (super.getPosition().y < terrainHeight) {
			speedVector.y = 0;
			isInAir = false;
			super.getPosition().y = terrainHeight;
		}
	}
	
	private void checkInputs() {
		/// Movement
		float speedZ = 0; // Forward
		float speedX = 0; // Right
		float speedY = 0; // Up
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			speedX += (float) Math.sin(Math.toRadians(rY));
			speedZ += (float) Math.cos(Math.toRadians(rY));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			speedX -= (float) Math.sin(Math.toRadians(rY));
			speedZ -= (float) Math.cos(Math.toRadians(rY));
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			speedX += (float) Math.sin(Math.toRadians(rY-90));
			speedZ += (float) Math.cos(Math.toRadians(rY-90));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			speedX -= (float) Math.sin(Math.toRadians(rY-90));
			speedZ -= (float) Math.cos(Math.toRadians(rY-90));
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !isInAir) {
			speedY = JUMP_POWER;
			isInAir = true;
		} else {
			speedY = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			speed = DEF_SPEED * 2;
		} else {
			speed = DEF_SPEED;
		}
		
		speedVector.x = speedX;
		speedVector.z = speedZ;
		speedVector.y += speedY;
	}
	
}
