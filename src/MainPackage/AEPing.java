package MainPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class AEPing extends Behaviour {

	private AUInfo aui;
	private HashMap<AEInfo,Boolean> ael;
	
	public AEPing(AUInfo a,ArrayList<AEInfo> al){
		aui=a;
		for (AEInfo ae : al) {
		   ael.put(ae,false);
		}
	}
	
	@Override
	public void action() {
		while(aui.getMovement()&&aui.getPercentagem()<(3/4)){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//send ping to all close AEs
		
		for(AEInfo ae : ael.keySet()){
			if (inside(ae.getAPE(),aui.getCoordinates())&&(!ael.get(ae))){
				ACLMessage ping=new ACLMessage(ACLMessage.INFORM);
				ping.addReceiver(ae.getAgent());
				ping.setContent("PING");
				myAgent.send(ping);
			}
		}
		
	}

	public boolean inside(APE ape,int coords[]){
		return ( Math.pow(coords[0] - ape.getCenterX(),2) + Math.pow(coords[1] - ape.getCenterY(),2) < Math.pow(ape.getRadius(),2) );
	}
	
	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
