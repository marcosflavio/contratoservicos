package br.ufc.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class LocalizarVendedores extends OneShotBehaviour {

	/**
	 * @author marcosf
	 */
	
	private static final long serialVersionUID = 1L;
	private ServiceDescription sd;
	private String pedido;

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public void setService(ServiceDescription sd) {
		this.sd = sd;
	}

	public LocalizarVendedores(Agent ag) {
		super(ag);
	
	}
	

	@Override
	public void action() {

		DFAgentDescription dfd = new DFAgentDescription();
		dfd.addServices(this.sd);
		try {

			DFAgentDescription[] resultado = DFService.search(myAgent, dfd);

			if (resultado.length != 0) {

				ACLMessage msg = new ACLMessage(ACLMessage.CFP);

				for (int i = 0; i < resultado.length; i++) {

					msg.addReceiver(resultado[i].getName());
					System.out.println("bucetinha cabeludinha"+resultado[i].getName());
				}

				msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
				msg.setContent(this.pedido);
				System.out.println("conversationId: " + msg.getConversationId());
				myAgent.send(msg);
				TratarOfertas tratar = new TratarOfertas(myAgent, msg);
				myAgent.addBehaviour(tratar);
			}

		} catch (FIPAException e) {
			e.printStackTrace();
		}

	}

}
