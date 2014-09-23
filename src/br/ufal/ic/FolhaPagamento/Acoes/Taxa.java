package br.ufal.ic.FolhaPagamento.Acoes;

import br.ufal.ic.FolhaPagamento.*;

public class Taxa implements Acoes{

	private Empregado empregado;
	private double valor;
	private int mes;
	
	public Taxa(Empregado empregado, double valor, int mes) {
		this.empregado = empregado;
		this.valor = valor;
		this.mes = mes;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		this.empregado.setSalarioProximoMes(valor, mes);
		this.empregado.setDebitoMes(true, mes);
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		this.empregado.setSalarioProximoMes(valor, mes);
		this.empregado.setDebitoMes(false, mes);
	}

}
