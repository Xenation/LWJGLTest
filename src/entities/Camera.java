package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private static final Vector3f DEF_POS = new Vector3f(0, 5, 0);
	private static final float DEF_PITCH = 0;
	private static final float DEF_YAW = 180;
	private static final float DEF_ROLL = 0;
	
	private Vector3f position;
	private float pitch;
	private float yaw;
	private float roll;
	
	public float speedMult = 0.25f;
	
	public Camera() {
		this.position = new Vector3f(DEF_POS);
		this.pitch = DEF_PITCH;
		this.yaw = DEF_YAW;
		this.roll = DEF_ROLL;
	}
	
	public void move() {
		int DY = Mouse.getDY();
		int DX = Mouse.getDX();
		
		this.pitch -= DY / 5;
		
		this.yaw += DX / 5;
		
		if (pitch > 90)
			pitch = 90;
		else if (pitch < -90)
			pitch = -90;
		
		if (yaw > 360 || yaw < -360)
			yaw = 0;
		
		if (Mouse.isInsideWindow() && !Mouse.isGrabbed()) {
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
			position.y += 0.2f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= 0.2f;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) {
			resetRotation();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) {
			yaw = DEF_YAW + 10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) {
			yaw = DEF_YAW - 10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) {
			pitch = DEF_PITCH + 10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) {
			pitch = DEF_PITCH - 10;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD0)) {
			resetPosition();
		}
	}
	
	public void reset() {
		resetPosition();
		resetRotation();
	}
	
	public void resetPosition() {
		position.x = DEF_POS.x;
		position.y = DEF_POS.y;
		position.z = DEF_POS.z;
	}
	
	public void resetRotation() {
		pitch = DEF_PITCH;
		yaw = DEF_YAW;
		roll = DEF_ROLL;
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
