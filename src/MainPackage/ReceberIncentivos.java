package MainPackage;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class ReceberIncentivos extends Behaviour {
	private AUInfo aui;
	
	public ReceberIncentivos(AUInfo a){
		aui=a;
	}
	
	@Override
	public void action() {
		
		ACLMessage msg=null;
		while(msg==null) {msg=myAgent.receive();}
	}

	@Override
	public boolean done() {
		return false;
	}
	
}
 