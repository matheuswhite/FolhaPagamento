package br.ufal.ic.FolhaPagamento.Acoes;

import br.ufal.ic.FolhaPagamento.*;

public class Remover implements Acoes{
	private Empregado empregado;
	
	public Remover(Empregado empregado) {
		this.empregado = empregado;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		pagamento.getEmpregados().remove(empregado);
		
		System.out.println("Redo: Remover empregado");
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		pagamento.getEmpregados().add(empregado);
		
		System.out.println("Undo: Remover Empregado");
	}

}
