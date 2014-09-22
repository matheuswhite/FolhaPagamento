package br.ufal.ic.FolhaPagamento.Acoes;

import br.ufal.ic.FolhaPagamento.*;

public class Cartao implements Acoes{
	private Cartao cartaoAntigo;
	private Cartao cartao;
	
	private Empregado empregado;
	private int ponto;
	private int dia;
	
	public Cartao(Cartao cartaoAntigo, Cartao cartao) {
		this.cartaoAntigo = cartaoAntigo;
		this.cartao = cartao;
	}
	
	public Cartao(Empregado empregado, int ponto, int dia) {
		this.empregado = empregado;
		this.ponto = ponto;
		this.dia = dia;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(cartao.empregado);
		
		Empregado temp = pagamento.getEmpregados().get(index);
		
		temp.baterPonto(this.cartao.ponto, this.dia);
		
		System.out.println("Redo: Lançar cartao de ponto");
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(cartaoAntigo.empregado);
		
		Empregado temp = pagamento.getEmpregados().get(index);
		
		temp.baterPonto(this.cartaoAntigo.ponto, this.dia);
		
		System.out.println("Undo: Lançar cartao de ponto");
	}

}
