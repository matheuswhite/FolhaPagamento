package br.ufal.ic.FolhaPagamento;

public class Empregado {
	private int id;
	private String nome;
	private String endereco;
	private double salarioBruto;
	private double salarioLiquido;
	private Map<Integer, Date> pontoInicio;
	private Map<Integer, Date> pontoFim;
	
	public Empregado(String nome, String endereco, int id) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.salarioBruto = 0.0;
		this.salarioLiquido = 0.0;
	} 
}
