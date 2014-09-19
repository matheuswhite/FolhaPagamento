package br.ufal.ic.FolhaPagamento;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Comissionados extends Assalariado {
	private double comissao;
	private Map<Date, List<Double> > vendas;
	private double salario2Semanas;
	private boolean primeiraSemana = true;
	
	public Comissionados(String nome, String endereco, int id) {
		super(nome, endereco, id);
		
		vendas = new HashMap<Date, List<Double> >();
	}
	
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
	
	public void setSalarioFixo(double salarioFixo) {
		super.setSalarioBruto(salarioFixo);
	}
	
	public double getSalarioFixo() {
		return this.salarioBruto;
	}
	
	public void calcular_Salario2Semanas() {
		this.salario2Semanas = this.salarioBruto / 2;
	}
	
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
		
		this.calcularComissao(valor);
		this.calcular_Salario2Semanas();
	}
	
	public void calcularComissao(double valor) {
		this.salarioBruto += valor * this.comissao;
	}


	@Override
	protected void calcularSalarioLiquido() {
		this.salarioLiquido += this.salario2Semanas;
		if(this.debitoProximoMes) {
			this.salarioLiquido += this.salarioProximoMes;
		}
	}
}
