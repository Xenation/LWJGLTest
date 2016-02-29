package toolbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import renderDisplay.Loader;
import terrains.Terrain;
import textures.ModelTexture;

public class EntityCreator {
	
	public float def_scale = 1;
	
	public float def_reflect = 0.1f;
	public float def_shineDamp = 10;
	
	public Random seed;
	
	private Loader loader;
	
	public EntityCreator(Loader loader) {
		this.loader = loader;
	}
	
	public EntityCreator(Loader loader, Random rand) {
		this.loader = loader;
		this.seed = rand;
	}
	
	
	/// From Scratch
	public Entity createEntity(String fileName) {
		return new Entity(new TexturedModel(OBJFileLoader.loadOBJ(fileName).load(loader),
				new ModelTexture(loader.loadTexture(fileName), def_reflect, def_shineDamp)),
				new Vector3f(),
				0, 0, 0, 1);
	}
	public Entity createEntity(String fileName, Vector3f pos, Vector3f rot) {
		return new Entity(new TexturedModel(OBJFileLoader.loadOBJ(fileName).load(loader),
				new ModelTexture(loader.loadTexture(fileName), def_reflect, def_shineDamp)),
				pos,
				rot.x, rot.y, rot.z, 1);
	}
	public Entity createEntity(String fileName, Vector3f pos, Vector3f rot, float scale) {
		return new Entity(new TexturedModel(OBJFileLoader.loadOBJ(fileName).load(loader),
				new ModelTexture(loader.loadTexture(fileName), def_reflect, def_shineDamp)),
				pos,
				rot.x, rot.y, rot.z, scale);
	}
	public Entity createEntity(String fileName, Vector3f pos, Vector3f rot, float scale, float reflect, float shineDamp) {
		return new Entity(new TexturedModel(OBJFileLoader.loadOBJ(fileName).load(loader),
				new ModelTexture(loader.loadTexture(fileName), reflect, shineDamp)),
				pos,
				rot.x, rot.y, rot.z, scale);
	}
	
	
	/// From Model
	public Entity createEntity(TexturedModel textModel, Vector3f position, Vector3f rotation, float scale) {
		return new Entity(textModel, position, rotation.x, rotation.y, rotation.z, scale);
	}
	
	// From String, Terrain to Batch
	public List<Entity> populateTerrain(String name, float scale, Terrain terrain, int[] rotRanges, int amount) {
		TexturedModel texturedModel = new TexturedModel(OBJFileLoader.loadOBJ(name).load(loader), new ModelTexture(loader.loadTexture(name), def_reflect, def_shineDamp));
		List<Entity> entities = new ArrayList<Entity>();
		for (int i = 0; i < amount; i++) {
			float posX = (float) seed.nextFloat()*Terrain.SIZE + terrain.getX()*Terrain.SIZE;
			float posZ = (float) seed.nextFloat()*Terrain.SIZE + terrain.getZ()*Terrain.SIZE;
			float posY = terrain.getHeightOfTerrain(posX, posZ);
			entities.add(new Entity(texturedModel, new Vector3f(posX, posY, posZ), (float) seed.nextFloat()*rotRanges[0], (float) seed.nextFloat()*rotRanges[1], (float) seed.nextFloat()*rotRanges[2], scale));
		}
		return entities;
	}
	
	public List<Entity> populateTerrain(TexturedModel textModel, float scale, Terrain terrain, int[] rotRanges, int amount) {
		List<Entity> entities = new ArrayList<Entity>();
		for (int i = 0; i < amount; i++) {
			float posX = (float) seed.nextFloat()*Terrain.SIZE + terrain.getX()*Terrain.SIZE;
			float posZ = (float) seed.nextFloat()*Terrain.SIZE + terrain.getZ()*Terrain.SIZE;
			float posY = terrain.getHeightOfTerrain(posX, posZ);
			entities.add(new Entity(textModel, new Vector3f(posX, posY, posZ), (float) seed.nextFloat()*rotRanges[0], (float) seed.nextFloat()*rotRanges[1], (float) seed.nextFloat()*rotRanges[2], scale));
		}
		return entities;
	}
	
	public List<Entity> populateTerrain(TexturedModel textModel, int atlasSize, float scale, Terrain terrain, int[] rotRanges, int amount) {
		List<Entity> entities = new ArrayList<Entity>();
		for (int i = 0; i < amount; i++) {
			float posX = (float) seed.nextFloat()*Terrain.SIZE + terrain.getX()*Terrain.SIZE;
			float posZ = (float) seed.nextFloat()*Terrain.SIZE + terrain.getZ()*Terrain.SIZE;
			float posY = terrain.getHeightOfTerrain(posX, posZ);
			entities.add(new Entity(textModel, seed.nextInt(atlasSize), new Vector3f(posX, posY, posZ), (float) seed.nextFloat()*rotRanges[0], (float) seed.nextFloat()*rotRanges[1], (float) seed.nextFloat()*rotRanges[2], scale));
		}
		return entities;
	}
	
	public List<Entity> populateTerrainNorm(TexturedModel textModel, float scale, Terrain terrain, int amount) {
		List<Entity> entities = new ArrayList<Entity>();
		for (int i = 0; i < amount; i++) {
			float posX = (float) seed.nextFloat()*Terrain.SIZE + terrain.getX()*Terrain.SIZE;
			float posZ = (float) seed.nextFloat()*Terrain.SIZE + terrain.getZ()*Terrain.SIZE;
			float posY = terrain.getHeightOfTerrain(posX, posZ);
			entities.add(new Entity(textModel, new Vector3f(posX, posY, posZ), (float) Math.toDegrees(terrain.getNormalOfTerrain(posX, posZ).x), (float) Math.toDegrees(terrain.getNormalOfTerrain(posX, posZ).y), (float) Math.toDegrees(terrain.getNormalOfTerrain(posX, posZ).z), scale));
		}
		return entities;
	}
	
}
