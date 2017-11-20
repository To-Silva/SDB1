package MainPackage;

import jade.util.leap.Serializable;

import java.util.Random;

public class AUInfo implements Serializable{
	private int coords[];
	private AEInfo origem,destino;
	private double preco;
	
	public AUInfo (AEInfo o,AEInfo d,int c[]){
		origem=o;
		destino=d;
		coords=c;
	}
	
	public AEInfo getDestino(){
		return this.destino;
	}
	
	public double getPercentagem(){
		int ocoords[],dcoords[];
		
		ocoords=origem.getCoords();
		dcoords=destino.getCoords();
		double dist=Math.sqrt((coords[0]-ocoords[0])*(coords[0]-ocoords[0])+(coords[1]-ocoords[1])*(coords[1]-ocoords[1]));
		double fullDist=Math.sqrt((dcoords[0]-ocoords[0])*(dcoords[0]-ocoords[0])+(dcoords[1]-ocoords[1])*(dcoords[1]-ocoords[1]));
		
		return (dist/fullDist);
	}
	
	public void setPreco(double p){
		preco=p;
	}
	
}
