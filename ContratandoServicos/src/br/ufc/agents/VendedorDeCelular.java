package br.ufc.agents;

import br.ufc.behaviours.VendedorB;
import br.ufc.common.Operacoes;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class VendedorDeCelular extends Agent {

	/**
	 * @author marcosf
	 */
	private static final long serialVersionUID = 1L;

	private Operacoes ope;
	private VendedorB behav;
	@Override
	protected void setup() {
		
		Object[] args = getArguments();
		behav = new VendedorB(this);
		
		String str = (String) args[0];
		
		Integer price = Integer.parseInt(str);
		
		behav.setPrice(price);
		
		
		ope = new Operacoes();
		// Descrição do serviço
		ServiceDescription servico = new ServiceDescription();
//		// Seu serviço eh vender celular
		servico.setType("venda celular");
		servico.setName(this.getLocalName());
//	
		ope.registraServico(servico, this);
//		ope.recebeMensagens("celular", "Olá, temos diversos celulares para a venda!", this);
//	
		
		System.out.println("Agent "+getLocalName()+" waiting for CFP...");
		MessageTemplate template = MessageTemplate.and(
				MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
				MessageTemplate.MatchPerformative(ACLMessage.CFP) );
		behav.setTemp(template);
		addBehaviour(behav);
	}

}
