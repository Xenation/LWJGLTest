package engineTester;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.*;
import models.*;
import renderDisplay.*;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		//Entities
		TexturedModel texturedModel = new TexturedModel(OBJLoader.loadObjModel("perso3", loader), new ModelTexture(loader.loadTexture("perso3"), 0.1f, 10));
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -50), 0, 0, 0, 1);
		
		TexturedModel texturedTree = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader), new ModelTexture(loader.loadTexture("lowPolyTree"), 0.1f, 10));
		List<Entity> trees = new ArrayList<Entity>();
		for (int i = 0; i < 300; i++) {
			trees.add(new Entity(texturedTree, new Vector3f((float) Math.random() * 800, 0, (float) Math.random() * 800), 0, (float) Math.random() * 360, 0, 1));
		}
		
		TexturedModel texturedGrass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader), new ModelTexture(loader.loadTexture("grassTexture"), 0.1f, 10));
		List<Entity> grasses = new ArrayList<Entity>();
		texturedGrass.getTexture().setHasTransparency(true);
		texturedGrass.getTexture().setUseFakeLighting(true);
		for (int i = 0; i < 200; i++) {
			grasses.add(new Entity(texturedGrass, new Vector3f((float) Math.random() * 800, 0, (float) Math.random() * 800), 0, (float) Math.random() * 360, 0, 3));
		}
		
		TexturedModel texturedFern = new TexturedModel(OBJLoader.loadObjModel("fern", loader), new ModelTexture(loader.loadTexture("fern"), 0.1f, 10));
		List<Entity> ferns = new ArrayList<Entity>();
		texturedFern.getTexture().setHasTransparency(true);
		for (int i = 0; i < 100; i++) {
			ferns.add(new Entity(texturedFern, new Vector3f((float) Math.random() * 800, 0, (float) Math.random() * 800), 0, (float) Math.random() * 360, 0, 1));
		}
		
		TexturedModel textDrone = new TexturedModel(OBJLoader.loadObjModel("exampleOBJ", loader), new ModelTexture(loader.loadTexture("whiteTile"), 0.1f, 10));
		Entity drone = new Entity(textDrone, new Vector3f(5, 0, 0), 0, 0, 0, 1);
		
		//Light
		Light light = new Light(new Vector3f(0, 500, 0), new Vector3f(1, 1, 1));
		
		//Light
		Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grassTileDebug")));
		Terrain terrain2 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("whiteTile")));
		
		//Camera
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		
		while (!Display.isCloseRequested()) {
			entity.increaseRotation(0.0f, 0.5f, 0.0f);
			camera.move();
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processEntity(entity);
			renderer.processEntity(drone);
			
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
