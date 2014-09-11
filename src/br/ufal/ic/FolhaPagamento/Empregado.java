package br.ufal.ic.FolhaPagamento;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Empregado {
	private int id;
	private String nome;
	private String endereco;
	private boolean sindicato;
	private int matriculaSindicato;
	
	protected double salarioBruto;
	protected double salarioLiquido;
	protected double salarioProximoMes;
	
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
		
		this.salarioProximoMes = 0;
		
		this.sindicato = false;
	} 
	
	
	
	
	public boolean isSindicato() {
		return this.sindicato;
	}
	
	public void setSindicato(boolean sindicato) {
		this.sindicato = sindicato;
	}
	
	public int getMatricula() {
		return this.matriculaSindicato;
	}
	
	public void setMatricula(int matricula) {
		this.matriculaSindicato = matricula;
	}
	
	
	
	
	
	public void baterPonto(Date dateInicio, Date dateFim) {
		this.pontoInicio.put(numPontos, dateInicio);
		this.pontoFim.put(numPontos, dateFim);
		this.numPontos++;
	}
}
