package br.ufal.ic.FolhaPagamento.Acoes;

import br.ufal.ic.FolhaPagamento.*;

public class Alterar implements Acoes{
	
	private Empregado empregadoNovo;
	private Empregado empregadoAntigo;
	
	public Alterar(Empregado empregadoAntigo, Empregado empregadoNovo) {
		this.empregadoAntigo = empregadoAntigo;
		this.empregadoNovo = empregadoNovo;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		pagamento.getEmpregados().remove(empregadoAntigo);
		pagamento.getEmpregados().add(empregadoNovo);
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		pagamento.getEmpregados().remove(empregadoNovo);
		pagamento.getEmpregados().add(empregadoAntigo);
	}

}
