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
	private Map<Date, List<Double> > vendas;
	
	
	public Comissionados(String nome, String endereco, int id, double salarioFixo) {
		super(nome, endereco, id, salarioFixo);
		
		vendas = new HashMap<Date, List<Double> >();
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
	
	public Map<Date, List<Double> > getVenda() {
		return this.vendas;
	}
	
	/* Gets e Sets */
	
	public void registrarVenda(Date date, double valor) {
		List<Double> aux;
		if(vendas.get(date) != null) {
			aux = vendas.get(date);
			aux.add(valor);
			vendas.put(date, aux);
		}
		else {
			aux = new LinkedList<Double>();
			aux.add(valor);
			vendas.put(date, aux);
		}
	}

	public void calcularSalarioLiquido(Date date) {
		
		List<Double> temp = this.vendas.get(date);
		double valorTotalVendas = 0.0;
		
		for(int i = 0; i < temp.size(); ++i) {
			valorTotalVendas += temp.get(i);
		}
		
		this.calcularSalarioLiquido();
		
		this.salarioLiquido += this.comissao * valorTotalVendas;
	}
	
	//toda vez que rodar a folha de pagamento executar esse metodo
	public void calcular_Salario2Semanas(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		int dia = cal.get(Calendar.DATE);
		
		this.calcularSalarioLiquido(date);
		
		
		if(this.pontos[dia] >= 8) {
			this.salario2Semanas = (this.salarioLiquido/2) * this.TAXA_POR_DIA;
		}
		else {
			this.salario2Semanas = (this.salarioLiquido/2) * this.TAXA_POR_DIA * this.TAXA_POR_HORA * this.pontos[dia];
		}
		
	}
}
