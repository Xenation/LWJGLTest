package engineTester;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.*;
import models.*;
import objConverter.OBJFileLoader;
import renderDisplay.*;
import terrains.Terrain;
import textures.ModelTexture;
import toolbox.EntityCreator;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		EntityCreator creator = new EntityCreator(loader, new Random(1199));
		
		//Entities
		//Entity entity = creator.createEntity("perso3", new Vector3f(0, 0, -50));
		
		int[] yRotRange = {0, 360, 0};
		//Vector3f origin = new Vector3f(0, 0, 0);
		
		TexturedModel texturedTree = new TexturedModel(OBJFileLoader.loadOBJ("lowPolyTree").load(loader), new ModelTexture(loader.loadTexture("lowPolyTree"), 0.1f, 10));
		//texturedTree.getTexture().setHasTransparency(true);
		//texturedTree.getTexture().setUseFakeLighting(true);
		
		TexturedModel texturedFern = new TexturedModel(OBJFileLoader.loadOBJ("fern").load(loader), new ModelTexture(loader.loadTexture("fern"), 0.1f, 10));
		texturedFern.getTexture().setHasTransparency(true);
		texturedFern.getTexture().setNumberOfRows(2);
		
		//Light
		Light light = new Light(new Vector3f(0, 500, 0), new Vector3f(1, 1, 1));
		
		//Light
		Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grassTile")), "heightmap");
		//Terrain terrain2 = new Terrain(-1, 0, loader, new ModelTexture(loader.loadTexture("grassTile")), "heightmap");
		//Terrain terrain3 = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grassTile")), "heightmap");
		//Terrain terrain4 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("grassTile")), "heightmap");
		
		List<Entity> trees = creator.populateTerrain("lowPolyTree", 1, terrain, yRotRange, 400);
		List<Entity> ferns = creator.populateTerrain(texturedFern, 4, 2, terrain, yRotRange, 75);
		//List<Entity> grasses = creator.populateTerrainNorm(texturedTree, 0.5f, terrain, 100);
		
		for (Entity tree : trees) {
			tree.setCollider(new Collider(tree, 5, 20, 5));
		}
		
		//Player
		Player player = new Player(new TexturedModel(OBJFileLoader.loadOBJ("chr_fox").load(loader), new ModelTexture(loader.loadTexture("chr_fox"))), new Vector3f(0, 0, 0), 0, 0, 0, .5f);
		player.setCollider(new Collider(player, 4, 0, 4));
		
		Entity entity = new Entity(new TexturedModel(OBJFileLoader.loadOBJ("tree").load(loader), new ModelTexture(loader.loadTexture("tree"))), new Vector3f(10, 0, -5), 0, 0, 0, 1);
		entity.setCollider(new Collider(entity, 6, 6, 6));
		
		//Camera
		Camera camera = new Camera(player);
		camera.setLocalOffset(0, 8, 0);
		
		MasterRenderer renderer = new MasterRenderer();
		
		camera.setPosition(0, 30, 30);
		camera.setRotation(20, 0, 0);
		
		EntityPool pool = new EntityPool();
		pool.add(player);
		pool.add(entity);
		pool.add(trees);
		pool.add(ferns);
		//pool.add(grasses);
		
		System.err.println(Math.toDegrees(terrain.getNormalOfTerrain(1, 1).x) + ", " + Math.toDegrees(terrain.getNormalOfTerrain(1, 1).y) + ", " + Math.toDegrees(terrain.getNormalOfTerrain(1, 1).z));
		
		entity.setRotation((float) Math.toDegrees(terrain.getNormalOfTerrain(1, 1).x), (float) Math.toDegrees(terrain.getNormalOfTerrain(1, 1).y), (float) Math.toDegrees(terrain.getNormalOfTerrain(1, 1).z));
		
		while (!Display.isCloseRequested()) {
			player.move(terrain, pool);
			camera.move();
			
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain2);
			//renderer.processTerrain(terrain3);
			//renderer.processTerrain(terrain4);
			
			renderer.processPool(pool);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
	
}
