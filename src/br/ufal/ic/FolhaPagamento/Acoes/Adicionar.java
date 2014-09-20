package br.ufal.ic.FolhaPagamento.Acoes;

import br.ufal.ic.FolhaPagamento.*;

public class Adicionar implements Acoes {
	private Empregado empregado;
	
	public Adicionar(Empregado empregado) {
		this.empregado = empregado;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		// TODO Auto-generated method stub
		
	}

}
