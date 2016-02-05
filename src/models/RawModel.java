package models;

public class RawModel {
	
	private int vaoID;
	private int vextexCount;
	
	public RawModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vextexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVextexCount() {
		return vextexCount;
	}
	
}
