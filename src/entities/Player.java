package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderDisplay.DisplayManager;

public class Player extends Entity {
	
	private static final float DEF_SPEED = 20;
	
	private Vector3f speedVector = new Vector3f();
	private float speed;
	
	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		this.speed = DEF_SPEED;
	}
	
	public void move() {
		checkInputs();
		float dx = speedVector.x * DisplayManager.getFrameTimeSeconds() * speed;
		float dz = speedVector.z * DisplayManager.getFrameTimeSeconds() * speed;
		float dy = speedVector.y * DisplayManager.getFrameTimeSeconds() * speed;
		super.increasePosition(dx, dy, dz);
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
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			speedY = 1;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			speedY = -1;
		} else {
			speedY = 0;
		}
		
		speedVector.set(speedX, speedY, speedZ);
	}
	
}
