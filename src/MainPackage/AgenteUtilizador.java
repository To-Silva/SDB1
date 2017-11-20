package MainPackage;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Taxi.receiveInfo;


public class AgenteUtilizador extends Agent {
	private AUInfo info;
	private ArrayList<AEInfo> aelist;
	private AEInfo origem,destino;
	private double shortestDist;
	
	public AgenteUtilizador(AUInfo aui){
		this.info=aui;
		int[] coords=new int[2];
		
		Random random = new Random();
		coords[0]=random.nextInt(100 - 0 + 1) + 0;
		coords[1]=random.nextInt(100 - 0 + 1) + 0;
		origem=getClosestAE(coords);
		do{
			coords[0]=random.nextInt(100 - 0 + 1) + 0;
			coords[1]=random.nextInt(100 - 0 + 1) + 0;
			destino=getClosestAE(coords);
		}while(origem.getAENum()!=destino.getAENum());
		info=new AUInfo(origem,destino,coords);
		
		DFAgentDescription dfd =new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("AU");
		sd.setName(this.getAID().toString());
		dfd.addServices(sd);	
        try {  
            DFService.register(this, dfd );  
        }
        catch (FIPAException fe) { fe.printStackTrace(); }
        
        
	}
	
	@Override
	protected void setup() {		
		addBehaviour(new PagarPercurso(info,origem));
		ParallelBehaviour b= new ParallelBehaviour(this,ParallelBehaviour.WHEN_ANY) {
			@Override
			public int onEnd() {
				return 0;
			}
		};
		b.addSubBehaviour(new PercorrerPercurso(info,shortestDist));
		b.addSubBehaviour(new ReceberIncentivos(info));
	}
	
	public AEInfo getClosestAE (int c[]){
		int aeCoords[]=new int[2];
		double dist;
		AEInfo closest,curr;
		
		Iterator<AEInfo> it = aelist.iterator();
		closest=it.next();
		aeCoords=closest.getCoords();
		shortestDist=Math.sqrt((aeCoords[0]-c[0])*(aeCoords[0]-c[0])+(aeCoords[1]-c[1])*(aeCoords[1]-c[1]));
		
		while (it.hasNext()) {
			curr=it.next();
			aeCoords=curr.getCoords();
			dist=Math.sqrt((aeCoords[0]-c[0])*(aeCoords[0]-c[0])+(aeCoords[1]-c[1])*(aeCoords[1]-c[1]));
			if (dist<shortestDist){
				shortestDist=dist;
				closest=curr;
			}
		}		
		
		return closest;
	}
}
