package br.ufal.ic.FolhaPagamento.Acoes;

import br.ufal.ic.FolhaPagamento.*;

public class Adicionar implements Acoes {
	private Empregado empregado;
	
	public Adicionar(Empregado empregado) {
		this.empregado = empregado;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		pagamento.getEmpregados().add(empregado);
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		pagamento.getEmpregados().remove(empregado);
	}

}
