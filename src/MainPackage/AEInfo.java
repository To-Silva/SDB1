package MainPackage;

import jade.core.AID;

public class AEInfo {
	private int AENum,coords[];
	private float nivel;
	AID agent;
	
	public int[] getCoords() {
		return coords;
	}

	public int getAENum() {
		return AENum;
	}

	public AID getAgent(){
		return this.agent;
	}
}
