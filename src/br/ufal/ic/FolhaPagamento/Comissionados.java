package br.ufal.ic.FolhaPagamento;

import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Comissionados extends Assalariado {
	
	private double comissao;
	private double salario2Semanas;
	private final double TAXA_POR_DIA = 0.1;
	private boolean primeiraSemana = true;
	private Map<GregorianCalendar, List<Double> > vendas;
	
	
	public Comissionados(String nome, String endereco, int id, double salarioFixo) {
		super(nome, endereco, id, salarioFixo);
		
		vendas = new HashMap<GregorianCalendar, List<Double> >();
		comissao = 0.0;
		salario2Semanas = 0.0;
	}
	
	
	
	
	/* Gets e Sets*/
	
	public boolean isPrimeiraSemana() {
		return this.primeiraSemana;
	}
	
	public void setPrimeiraSemana(boolean primeiraSemana) {
		this.primeiraSemana = primeiraSemana;
	}
	
	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	
	public double getComissao() {
		return this.comissao;
	}
	
	public List<Double> getVendas(GregorianCalendar cal) {
		return this.vendas.get(cal);
	}
	
	public Map<GregorianCalendar, List<Double> > getVendasTotal() {
		return this.vendas;
	}
	
	/* Gets e Sets */
	
	
	
	
	public void registrarVenda(GregorianCalendar cal, double valor) {
		List<Double> aux;
		
		try {
			aux = this.vendas.get(cal);
			aux.add(valor);
			vendas.put(cal, aux);
		}
		catch ( NullPointerException e ) {
			aux = new LinkedList<Double>();
			aux.add(valor);
			vendas.put(cal, aux);
		}
	}

	@Override
	public void calcularSalarioLiquido(GregorianCalendar cal) {
		
		List<Double> temp = this.getVendas(cal);
		double valorTotalVendas = 0.0;
		
		for(int i = 0; i < temp.size(); ++i) {
			valorTotalVendas += temp.get(i);
		}
		
		super.calcularSalarioLiquido(cal);
		
		this.salarioLiquido += this.comissao * valorTotalVendas;
	}
	
	//toda vez que rodar a folha de pagamento executar esse metodo
	public void calcularSalario2Semanas(GregorianCalendar cal) {
		int dia = cal.get(Calendar.DATE);
		
		this.calcularSalarioLiquido(cal);
		
		
		if(this.pontos[dia] >= 8) {
			this.salario2Semanas = (this.salarioLiquido/2) * this.TAXA_POR_DIA;
		}
		else {
			this.salario2Semanas = (this.salarioLiquido/2) * this.TAXA_POR_DIA * this.TAXA_POR_HORA * this.pontos[dia];
		}
		
	}
	
}
