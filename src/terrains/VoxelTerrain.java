package terrains;

import models.RawModel;
import models.TexturedModel;
import renderDisplay.Loader;

public class VoxelTerrain {
	
	private static final int SIZE = 128;
	private static final int MAX_HEIGHT = 64;
	private static final int MAX_PIXEL_COLOR = 256 * 256 * 256;
	
	private float x;
	private float z;
	private TexturedModel tileModel;
	
	private float[][] heights;
	
	public VoxelTerrain(int gridX, int gridZ, Loader loader, TexturedModel tile) {
		this.x = gridX * SIZE;
		this.z = gridZ * SIZE;
		this.tileModel = tile;
	}
	
	private RawModel generateTerrain(Loader loader) {
		
		int VERTEX_COUNT = SIZE;
		//heights = new float[VERTEX_COUNT][VERTEX_COUNT];
		
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count * 2];
		int[] indices = new int[6 * (VERTEX_COUNT-1) * (VERTEX_COUNT-1)];
		int vertexPointer = 0;
		for(int i = 0; i < VERTEX_COUNT; i++){
			for(int j = 0; j < VERTEX_COUNT; j++){
				vertices[vertexPointer * 3] = (float) j / ((float) VERTEX_COUNT - 1) * SIZE;
				//float height = getHeight(j, i, image);
				//heights[j][i] = height;
				vertices[vertexPointer * 3 + 1] = 0;
				vertices[vertexPointer * 3 + 2] = (float) i / ((float) VERTEX_COUNT - 1) * SIZE;
				//Vector3f normal = calculateNormal(j, i, image);
				normals[vertexPointer * 3] = 1;
				normals[vertexPointer * 3 + 1] = 1;
				normals[vertexPointer * 3 + 2] = 1;
				textureCoords[vertexPointer * 2] = (float) j / ((float) VERTEX_COUNT - 1);
				textureCoords[vertexPointer * 2 + 1] = (float) i / ((float) VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz = 0; gz < VERTEX_COUNT-1; gz++){
			for(int gx = 0; gx < VERTEX_COUNT-1; gx++){
				int topLeft = (gz*VERTEX_COUNT) + gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1) * VERTEX_COUNT) + gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
	
}
