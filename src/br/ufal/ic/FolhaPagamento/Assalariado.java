package br.ufal.ic.FolhaPagamento;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Assalariado extends Empregado{
	private final double comissao = 0.3;
	private Map<Date, List<Double> > vendas;
	private double Salario2Semanas;
	private boolean comissionado;
	
	public Assalariado(String nome, String endereco, int id, double SalarioFixo) {
		super(nome, endereco, id);
		
		this.salarioBruto = SalarioFixo;
		
		vendas = new HashMap<Date, List<Double> >();
		
		this.calcular_Salario2Semanas();
	}
	
	public void calcular_Salario2Semanas() {
		this.Salario2Semanas = this.salarioBruto / 2;
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
}
