package br.ufal.ic.FolhaPagamento;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Empregado {
	private int id;
	private String nome;
	private String endereco;
	
	private double salarioBruto;
	private double salarioLiquido;
	private Map<String, Double> salarioTotal;
	
	private Map<Integer, Date> pontoInicio;
	private Map<Integer, Date> pontoFim;
	private int numPontos;
	
	public Empregado(String nome, String endereco, int id) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.salarioBruto = 0.0;
		this.salarioLiquido = 0.0;
		this.numPontos = 0;
		
		this.pontoInicio = new HashMap<Integer, Date>();
		this.pontoFim = new HashMap<Integer , Date>();
		
		this.salarioTotal = new HashMap<String, Double>();
	} 
	
	public double getSalarioTotal(String mes) {
		return this.salarioTotal.get(mes);
	}
	
	public void setSalarioTotal(String mes, double valor) {
		this.salarioTotal.put(mes, Double.valueOf(valor));
	}
	
	public void baterPonto(Date dateInicio, Date dateFim) {
		this.pontoInicio.put(numPontos, dateInicio);
		this.pontoFim.put(numPontos, dateFim);
		this.numPontos++;
	}
}
