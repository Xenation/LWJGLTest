package engineTester;

import java.util.HashMap;
import java.util.List;

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
		EntityCreator creator = new EntityCreator(loader);
		
		//Entities
		//Entity entity = creator.createEntity("perso3", new Vector3f(0, 0, -50));
		
		int[] yRotRange = {0, 360, 0};
		//Vector3f origin = new Vector3f(0, 0, 0);
		Vector3f chunkm1m1 = new Vector3f(-800, 0, -800);
		Vector3f chunk11 = new Vector3f(800, 0, 800);
		
		List<Entity> trees = creator.createEntities("lowPolyTree", 1, 0.1f, 10, chunkm1m1, chunk11, yRotRange, 600);
		
		TexturedModel texturedGrass = new TexturedModel(OBJFileLoader.loadOBJ("grassModel").load(loader), new ModelTexture(loader.loadTexture("grassTexture"), 0.1f, 10));
		texturedGrass.getTexture().setHasTransparency(true);
		texturedGrass.getTexture().setUseFakeLighting(true);
		List<Entity> grasses = creator.createEntities(texturedGrass, 3, 400, chunkm1m1, chunk11, yRotRange);
		
		TexturedModel texturedFern = new TexturedModel(OBJFileLoader.loadOBJ("fern").load(loader), new ModelTexture(loader.loadTexture("fern"), 0.1f, 10));
		texturedFern.getTexture().setHasTransparency(true);
		List<Entity> ferns = creator.createEntities(texturedFern, 1, 200, chunkm1m1, chunk11, yRotRange);
		
		//Light
		Light light = new Light(new Vector3f(0, 500, 0), new Vector3f(1, 1, 1));
		
		//Light
		Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grassTile")));
		Terrain terrain2 = new Terrain(-1, 0, loader, new ModelTexture(loader.loadTexture("grassTile")));
		Terrain terrain3 = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grassTile")));
		Terrain terrain4 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("grassTile")));
		
		//Player
		Player player = new Player(new TexturedModel(OBJFileLoader.loadOBJ("chr_fox").load(loader), new ModelTexture(loader.loadTexture("chr_fox"))), new Vector3f(0, 0, 0), 0, 0, 0, 1);
		
		//Camera
		Camera camera = new Camera(player);
		camera.setLocalOffset(0, 8, 0);
		
		MasterRenderer renderer = new MasterRenderer();
		camera.setPosition(0, 30, 30);
		camera.setRotation(20, 0, 0);
		
		while (!Display.isCloseRequested()) {
			camera.move();
			player.move();
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processTerrain(terrain3);
			renderer.processTerrain(terrain4);
			renderer.processEntity(player);
			
			for (Entity tree : trees) {
				renderer.processEntity(tree);
			}
			
			for (Entity grass : grasses) {
				renderer.processEntity(grass);
			}
			
			for (Entity fern : ferns) {
				renderer.processEntity(fern);
			}
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
	
}
