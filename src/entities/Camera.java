package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private float distanceFromPlayer = 50;
	private float facingAngle = 0;
	private float fadeDistance = 20;
	private Vector3f localOffset = new Vector3f();
	
	private static final Vector3f DEF_POS = new Vector3f(0, 5, 0);
	private static final float DEF_PITCH = 0;
	private static final float DEF_YAW = 180;
	private static final float DEF_ROLL = 0;
	
	private Vector3f position;
	private float pitch;
	private float yaw;
	private float roll;
	
	private Player player;
	
	public float speedMult = 0.25f;
	
	public Camera(Player player) {
		this.player = player;
		this.position = new Vector3f(DEF_POS);
		this.pitch = DEF_PITCH;
		this.yaw = DEF_YAW;
		this.roll = DEF_ROLL;
	}
	
	public void move() {
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizDistance = calculateHorizontalDistance();
		float vertiDistance = calculateVerticalDistance();
		calculateCameraPosition(horizDistance, vertiDistance);
		
		
		/*/// Rotation
		float DY = Mouse.getDY();
		float DX = Mouse.getDX();
		
		this.pitch -= DY / 5;
		
		this.yaw += DX / 5;
		
		if (pitch > 90)
			pitch = 90;
		else if (pitch < -90)
			pitch = -90;
		
		if (yaw > 360 || yaw < -360)
			yaw = 0;
		
		/// Mouse Grab
		if (Mouse.isInsideWindow() && !Mouse.isGrabbed()) {
			Mouse.setGrabbed(true);
		} else if (!Mouse.isInsideWindow() && Mouse.isGrabbed()) {
			Mouse.setGrabbed(false);
		}
		
		/// Movement
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
			position.y += 0.8f * speedMult;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= 0.8f * speedMult;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) {
			speedMult += 0.05f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) {
			speedMult -= 0.05f;
		}
		
		/// Debug
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
		}*/
	}
	
	public void setPosition(float x, float y, float z) {
		position.x = x;
		position.y = y;
		position.z = z;
	}
	
	public void setRotation(float pitch, float yaw, float roll) {
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
	}
	
	public void setLocalOffset(float offX, float offY, float offZ) {
		this.localOffset.set(offX, offY, offZ);
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
	
	private void calculateCameraPosition(float horizDistance, float vertiDistance) {
		player.setRY(facingAngle);
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(player.getRY())));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(player.getRY())));
		position.x = player.getPosition().x + localOffset.x - offsetX;
		position.y = player.getPosition().y + localOffset.y + vertiDistance;
		position.z = player.getPosition().z + localOffset.z - offsetZ;
		this.yaw = 180 - player.getRY();
	}
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer -= zoomLevel;
		if (zoomLevel != 0) {
			if (distanceFromPlayer < fadeDistance) {
				player.render = false;
			} else {
				player.render = true;
			}
			if (distanceFromPlayer < 0) {
				distanceFromPlayer = 0;
			}
		}
	}
	
	private void calculatePitch() {
		if (Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.3f;
			pitch -= pitchChange;
		}
		if (pitch > 90)
			pitch = 90;
		else if (pitch < -90)
			pitch = -90;
	}
	
	private void calculateAngleAroundPlayer() {
		if (Mouse.isButtonDown(1)) {
			float angleChange = Mouse.getDX() * 0.5f;
			facingAngle -= angleChange;
		}
	}
	
}
