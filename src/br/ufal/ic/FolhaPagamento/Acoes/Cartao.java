package br.ufal.ic.FolhaPagamento.Acoes;

import java.util.Date;

import br.ufal.ic.FolhaPagamento.*;

public class Cartao implements Acoes{
	private Cartao cartaoAntigo;
	private Cartao cartao;
	
	private Empregado empregado;
	private Date inicio;
	private Date fim;
	
	public Cartao(Cartao cartaoAntigo, Cartao cartao) {
		this.cartaoAntigo = cartaoAntigo;
		this.cartao = cartao;
	}
	
	public Cartao(Empregado empregado, Date inicio, Date fim) {
		this.empregado = empregado;
		this.inicio = inicio;
		this.fim = fim;
	}
	
	@Override
	public void refaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(cartao.empregado);
		
		Empregado temp = pagamento.getEmpregados().get(index);
		
		temp.baterPonto(this.cartao.inicio, this.cartao.fim);
	}

	@Override
	public void desfaz(Pagamento pagamento) {
		int index = pagamento.getEmpregados().indexOf(cartaoAntigo.empregado);
		
		Empregado temp = pagamento.getEmpregados().get(index);
		
		temp.baterPonto(this.cartaoAntigo.inicio, this.cartaoAntigo.fim);
		
	}

}
