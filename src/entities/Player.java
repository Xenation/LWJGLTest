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
		/*float distanceFwd = speedVector.z * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distanceFwd * Math.sin(Math.toRadians(rY)));
		float dz = (float) (distanceFwd * Math.cos(Math.toRadians(rY)));
		float distanceRig = speedVector.x * DisplayManager.getFrameTimeSeconds();
		dx -= (float) (distanceRig * Math.sin(Math.toRadians(rY+90)));
		dz -= (float) (distanceRig * Math.cos(Math.toRadians(rY+90)));
		float distanceUp = speedVector.y * DisplayManager.getFrameTimeSeconds();
		float dy = distanceUp;*/
		float dx = speedVector.x * DisplayManager.getFrameTimeSeconds() * speed;
		float dz = speedVector.z * DisplayManager.getFrameTimeSeconds() * speed;
		float dy = speedVector.y * DisplayManager.getFrameTimeSeconds() * speed;
		super.increasePosition(dx, dy, dz);
	}
	
	private void checkInputs() {
		/*if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			this.currentSpeed = RUN_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -RUN_SPEED;
		} else {
			this.currentSpeed = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = TURN_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			this.currentTurnSpeed = -TURN_SPEED;
		} else {
			this.currentTurnSpeed = 0;
		}*/
		
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
