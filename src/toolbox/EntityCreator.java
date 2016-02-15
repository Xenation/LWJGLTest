package toolbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import renderDisplay.Loader;
import terrains.Terrain;
import textures.ModelTexture;

public class EntityCreator {
	
	public Vector3f def_pos = new Vector3f(0, 0, 0);
	public Vector3f def_rot = new Vector3f(0, 0, 0);
	public float def_scale = 1;
	
	public float def_reflect = 0.1f;
	public float def_shineDamp = 10;
	
	private Loader loader;
	
	public EntityCreator(Loader loader) {
		this.loader = loader;
	}
	
	
	/// From Scratch
	public Entity createEntity(String name) {
		ModelData data = OBJFileLoader.loadOBJ(name);
		TexturedModel texturedModel = new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture(name), def_reflect, def_shineDamp));
		return new Entity(texturedModel, def_pos, def_rot.x, def_rot.y, def_rot.z, def_scale);
	}
	public Entity createEntity(String name, Vector3f position) {
		ModelData data = OBJFileLoader.loadOBJ(name);
		TexturedModel texturedModel = new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture(name), def_reflect, def_shineDamp));
		return new Entity(texturedModel, position, def_rot.x, def_rot.y, def_rot.z, def_scale);
	}
	public Entity createEntity(String name, Vector3f position, Vector3f rotation, float scale) {
		ModelData data = OBJFileLoader.loadOBJ(name);
		TexturedModel texturedModel = new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture(name), def_reflect, def_shineDamp));
		return new Entity(texturedModel, position, rotation.x, rotation.y, rotation.z, scale);
	}
	public Entity createEntity(String name, Vector3f position, Vector3f rotation, float scale, float reflectivity, float shineDamper) {
		ModelData data = OBJFileLoader.loadOBJ(name);
		TexturedModel texturedModel = new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture(name), reflectivity, shineDamper));
		return new Entity(texturedModel, position, rotation.x, rotation.y, rotation.z, scale);
	}
	
	
	/// From Model
	public Entity createEntity(TexturedModel textModel, Vector3f position, Vector3f rotation, float scale) {
		return new Entity(textModel, position, rotation.x, rotation.y, rotation.z, scale);
	}
	
	/// From Scratch to Batch
	public List<Entity> createEntities(String name, Vector3f rotation, float scale, float reflectivity, float shineDamper, Vector3f zone1, Vector3f zone2, int amount) {
		ModelData data = OBJFileLoader.loadOBJ(name);
		TexturedModel texturedModel = new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture(name), reflectivity, shineDamper));
		List<Entity> entities = new ArrayList<Entity>();
		for (int i = 0; i < amount; i++) {
			entities.add(new Entity(texturedModel, new Vector3f((float) Math.random()*(zone2.x - zone1.x) + zone1.x, (float) Math.random()*(zone2.y - zone1.y) + zone1.y, (float) Math.random()*(zone2.z - zone1.z) + zone1.z), rotation.x, rotation.y, rotation.z, scale));
		}
		return entities;
	}
	public List<Entity> createEntities(String name, float scale, float reflectivity, float shineDamper, Vector3f zone1, Vector3f zone2, int[] rotRanges, int amount) {
		ModelData data = OBJFileLoader.loadOBJ(name);
		TexturedModel texturedModel = new TexturedModel(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(loader.loadTexture(name), reflectivity, shineDamper));
		List<Entity> entities = new ArrayList<Entity>();
		for (int i = 0; i < amount; i++) {
			entities.add(new Entity(texturedModel, new Vector3f((float) Math.random()*(zone2.x - zone1.x) + zone1.x, (float) Math.random()*(zone2.y - zone1.y) + zone1.y, (float) Math.random()*(zone2.z - zone1.z) + zone1.z), (float) Math.random()*rotRanges[0], (float) Math.random()*rotRanges[1], (float) Math.random()*rotRanges[2], scale));
		}
		return entities;
	}
	
	/// From Model to Batch
	public List<Entity> createEntities(TexturedModel textModel, int scale, int amount, Vector3f zone1, Vector3f zone2, int[] rotRanges) {
		List<Entity> entities = new ArrayList<Entity>();
		for (int i = 0; i < amount; i++) {
			entities.add(new Entity(textModel, new Vector3f((float) Math.random()*(zone2.x - zone1.x) + zone1.x, (float) Math.random()*(zone2.y - zone1.y) + zone1.y, (float) Math.random()*(zone2.z - zone1.z) + zone1.z), (float) Math.random()*rotRanges[0], (float) Math.random()*rotRanges[1], (float) Math.random()*rotRanges[2], scale));
		}
		return entities;
	}
	
	/// Chunk Gen
	public HashMap<Terrain, List<List<Entity>>> createChunk(int x, int z) {
		HashMap<Terrain, List<List<Entity>>> chunkList = new HashMap<Terrain, List<List<Entity>>>();
		List<List<Entity>> entityLists = new ArrayList<List<Entity>>();
		Terrain terr = new Terrain(x, z, loader, new ModelTexture(loader.loadTexture("grassTile")));
		int[] roty = {0, 360, 0};
		entityLists.add(createEntities("lowPolyTree", def_scale, def_reflect, def_shineDamp, new Vector3f(x*800, 0, z*800), new Vector3f(x*800+800, 0, z*800+800), roty, 200));
		chunkList.put(terr, entityLists);
		return chunkList;
	}
}
