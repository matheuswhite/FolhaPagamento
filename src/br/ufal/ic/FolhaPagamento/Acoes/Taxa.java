package br.ufal.ic.FolhaPagamento.Acoes;

import br.ufal.ic.FolhaPagamento.*;

public class Taxa implements Acoes{

	private Empregado empregado;
	private double valor;
	
	public Taxa(Empregado empregado, double valor) {
		this.empregado = empregado;
		this.valor = valor;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		this.empregado.setSalarioProximoMes(valor, false);
		this.empregado.setDebitoProximoMes(true);
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		this.empregado.setSalarioProximoMes(valor, true);
		this.empregado.setDebitoProximoMes(false);
	}

}
