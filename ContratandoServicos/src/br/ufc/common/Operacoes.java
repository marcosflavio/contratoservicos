package br.ufc.common;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class Operacoes {

	public void recebeMensagens(String mensagem, String resp, Agent agente){
		
		agente.addBehaviour(new CyclicBehaviour(agente) {
	
			private static final long serialVersionUID = 5104003219172219851L;

			@Override
			public void action() {

				ACLMessage msg = agente.receive();
				
				if(msg != null){
					if(msg.getContent().equalsIgnoreCase(mensagem)){
						ACLMessage reply = msg.createReply();
						reply.setContent(resp);
						myAgent.send(reply);
					}
				}else {
					block();
				}
				
			}
		});
		
	}
	
	
	public void receberMensagensCFP(){
		
		
	}
	
	
	public void registraServico(ServiceDescription sd, Agent agente){
		
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.addServices(sd);
		
		try{
			DFService.register(agente, dfd);
			
		}catch(FIPAException e){
			e.printStackTrace();
		}
		
	}
	
	
}
