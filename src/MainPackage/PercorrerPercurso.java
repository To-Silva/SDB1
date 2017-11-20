package MainPackage;

import java.util.concurrent.TimeUnit;

import jade.core.behaviours.Behaviour;

public class PercorrerPercurso extends Behaviour{
	private int stride=2;
	private double distance;
	AUInfo aui;
	
	public PercorrerPercurso(AUInfo a,double d){
		this.distance=d;
		this.aui=a;
	}
	
	@Override
	public void action() {
		int d=0;
		while(d<distance){
			d+=stride;
			aui.setCoordinates(translateCoords(aui.getCoordinates()));
			try {
				aui.setMoving(true);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			aui.setMoving(false);
			notify();
		}
	}
	
	public int[] translateCoords(int[] c){
		Double ratio,resx,resy;
		int[] o,d,v,res=new int[2];
		v=new int[2];
		
		o=aui.getOrigem().getCoords();
		d=aui.getDestino().getCoords();
		v[0]=d[0]-o[0];
		v[1]=d[1]-o[1];
		double vmod=Math.sqrt(Math.pow(v[0], 2)+Math.pow(v[1], 2));
		double u[]= new double[2];
		
		u[0]=v[0]/vmod;
		u[1]=v[1]/vmod;
		
		resx=c[0]+stride*u[0];
		resy=c[0]+stride*u[0];
		
		res[0]=resx.intValue();
		res[1]=resy.intValue();
		return res;
	}
	
	@Override
	public boolean done() {
		return false;
	}

}
