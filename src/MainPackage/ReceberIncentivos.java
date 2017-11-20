package MainPackage;

import jade.core.behaviours.Behaviour;

public class ReceberIncentivos extends Behaviour {
	private AUInfo aui;
	
	public ReceberIncentivos(AUInfo a){
		aui=a;
	}
	
	@Override
	public void action() {
		
	}

	@Override
	public boolean done() {
		return false;
	}
	
}
