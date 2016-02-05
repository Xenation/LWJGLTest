package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public float speedMult = 0.25f;
	
	public Camera() {
		
	}
	
	public void move() {
		int DY = Mouse.getDY();
		int DX = Mouse.getDX();
		
		this.pitch -= DY / 5;
		
		this.yaw += DX / 5;
		
		if (Mouse.isInsideWindow() && !Mouse.isGrabbed()) {
			Mouse.getDX();
			Mouse.getDY();
			Mouse.setGrabbed(true);
		} else if (!Mouse.isInsideWindow() && Mouse.isGrabbed()) {
			Mouse.setGrabbed(false);
		}
		
		
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			position.x += (float)Math.sin(Math.toRadians(yaw)) * speedMult;
			position.z -= (float)Math.cos(Math.toRadians(yaw)) * speedMult;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.x -= (float)Math.sin(Math.toRadians(yaw)) * speedMult;
			position.z += (float)Math.cos(Math.toRadians(yaw)) * speedMult;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += (float)Math.sin(Math.toRadians(yaw+90)) * speedMult;
			position.z -= (float)Math.cos(Math.toRadians(yaw+90)) * speedMult;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			position.x += (float)Math.sin(Math.toRadians(yaw-90)) * speedMult;
			position.z -= (float)Math.cos(Math.toRadians(yaw-90)) * speedMult;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += (float)Math.cos(Math.toRadians(pitch)) * speedMult;
			position.z -= (float)Math.sin(Math.toRadians(pitch)) * speedMult;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.x += (float)Math.sin(Math.toRadians(pitch)) * speedMult;
			position.y -= (float)Math.cos(Math.toRadians(pitch)) * speedMult;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) {
			pitch = 0;
			yaw = 0;
			roll = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) {
			yaw = 10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) {
			yaw = -10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) {
			pitch = 10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) {
			pitch = -10;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	
	
}
