package MainPackage;

import java.io.IOException;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class PagarPercurso extends Behaviour{
	private AUInfo aui;
	private AEInfo aei;
	
	public PagarPercurso(AUInfo aui,AEInfo aei){
		this.aui=aui;
		this.aei=aei;
	}
	@Override
	public void action() {
		String paymentDetails;
		ACLMessage entry=null,payment=null;
		entry.addReceiver(aei.getAgent());
		entry.setContent("Destination-"+aui.getDestino().getAENum());
		myAgent.send(entry);
		
		ACLMessage msg=null;
		while(msg==null) {msg=myAgent.receive();}
		paymentDetails=msg.getContent();
		aui.setPreco(Double.parseDouble(paymentDetails));		
		
		payment.addReceiver(aei.getAgent());
		try {
			payment.setContentObject(aui);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		myAgent.send(payment);
		
	}

	@Override
	public boolean done() {
		return false;
	}
	
}
