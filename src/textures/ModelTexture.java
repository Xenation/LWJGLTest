package textures;

public class ModelTexture {
	
	private int textureID;
	
	private float reflectivity = 0;
	private float shineDamper = 1;
	
	private boolean hasTransparency = false;
	private boolean useFakeLighting = false;
	
	public ModelTexture(int id) {
		this.textureID = id;
	}
	
	public ModelTexture(int id, float reflectivity, float shineDamper) {
		this.textureID = id;
		this.reflectivity = reflectivity;
		this.shineDamper = shineDamper;
	}
	
	public boolean hasTransparency() {
		return hasTransparency;
	}

	public void setHasTransparency(boolean hasTransparency) {
		this.hasTransparency = hasTransparency;
	}

	public boolean isUseFakeLighting() {
		return useFakeLighting;
	}

	public void setUseFakeLighting(boolean useFakeLighting) {
		this.useFakeLighting = useFakeLighting;
	}

	public int getID() {
		return textureID;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}
	
	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}
	
}
