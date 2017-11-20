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
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean done() {
		return false;
	}

}
