package br.ufc.behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;

public class VendedorB extends CyclicBehaviour{
	
	private MessageTemplate temp;
	private int price;
	public VendedorB(Agent a) {

		super(a);
	}
	
	
	public void setTemp(MessageTemplate mt){
		this.temp = mt;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public int getPrice(){
		
		return this.price;
	}
	
	@Override
	public void action() {
		
		myAgent.addBehaviour(new ContractNetResponder(myAgent, this.temp){

			@Override
			protected ACLMessage handleCfp(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
				System.out.println("Agente "+myAgent.getLocalName()+": CFP recebido de "+cfp.getSender().getName()+". Ação é "+cfp.getContent());
				System.out.println("conv: "+ cfp.getConversationId());
				
//				if (proposal > 2) {
					// We provide a proposal
					System.out.println("Agente "+myAgent.getLocalName()+": Propondo "+ getPrice());
					ACLMessage propose = cfp.createReply();
					propose.setPerformative(ACLMessage.PROPOSE);
					propose.setContent(String.valueOf(getPrice()));
					return propose;
//				}
//				else {
					// We refuse to provide a proposal
//					System.out.println("Agent "+myAgent.getLocalName()+": Refuse");
//					throw new RefuseException("evaluation-failed");
//				}
			}

			@Override
			protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose,ACLMessage accept) throws FailureException {
				System.out.println("Agent "+myAgent.getLocalName()+": Proposal accepted");
				
				if (performAction()) {
					System.out.println("Agent "+myAgent.getLocalName()+": Action successfully performed");
					ACLMessage inform = accept.createReply();
					inform.setPerformative(ACLMessage.INFORM);
					return inform;
				}
				else {
					System.out.println("Agent "+myAgent.getLocalName()+": Action execution failed");
					throw new FailureException("unexpected-error");
				}	
			}

			protected void handleRejectProposal(ACLMessage cfp, ACLMessage propose, ACLMessage reject) {
				System.out.println("Agent "+myAgent.getLocalName()+": Proposal rejected");
			}

			
			private boolean performAction() {
				// Simulate action execution by generating a random number
				return (Math.random() > 0.2);
			}

			
		});
	
		block();
	}
	
}
