package br.ufc.agents;

import br.ufc.behaviours.LocalizarVendedores;
import br.ufc.behaviours.SolicitanteB;
import br.ufc.behaviours.TratarOfertas;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Solicitante extends Agent {

	/**
	 * @author marcosf
	 */
	private static final long serialVersionUID = 1L;
	private SolicitanteB behav;
	private LocalizarVendedores buscab;
	
	@Override
	protected void setup() {
		behav = new SolicitanteB(this);
		// Captura argumentos
		Object[] args = getArguments();
		
		
		if (args != null && args.length > 0) {
			String argumento = (String) args[0];

			// Se o argumento � um celular
			if (argumento.equalsIgnoreCase("Celular")) {
				ServiceDescription servico = new ServiceDescription();
				servico.setType("venda celular");
				// busca por um agente que forne�a o servi�o
				busca(servico, "celular");
			}

			if (argumento.equalsIgnoreCase("Geladeira")) {
				ServiceDescription servico = new ServiceDescription();
				servico.setType("venda geladeira");
				// busca por um agente que forne�a o servi�o
				busca(servico, "geladeira");

			}
			if (argumento.equalsIgnoreCase("televis�o")) {
				ServiceDescription servico = new ServiceDescription();
				servico.setType("venda televis�o");
				// busca por um agente que forne�a o servi�o
				busca(servico, "televis�o");
				
			}

			//addBehaviour(behav);
			
		}

	}

	// M�todo para realizar a busca
	protected void busca(final ServiceDescription servico, final String pedido) {

		// A cada minuto tenta buscar por agentes que forne�am o servi�os
		buscab = new LocalizarVendedores(this);
		buscab.setService(servico);
		buscab.setPedido(pedido);
		addBehaviour(buscab);

	}

}
