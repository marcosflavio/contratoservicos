package br.ufc.agents;

import br.ufc.common.Operacoes;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class VendedorDeTelevisao extends Agent {

	/**
	 * @author marcosf
	 */
	private static final long serialVersionUID = 1L;
	private Operacoes ope;
	
	@Override
	protected void setup() {
		
		ope = new Operacoes();
		// Descri��o do servi�o
		ServiceDescription servico = new ServiceDescription();
		// Seu servi�o eh vender televis�es
		servico.setType("venda televis�o");
		servico.setName(this.getLocalName());
		ope.registraServico(servico, this);
		ope.recebeMensagens("televis�o", "Ol� tudo bem? Estou vendendo uma Televis�o LG!", this);
		
	}
}
