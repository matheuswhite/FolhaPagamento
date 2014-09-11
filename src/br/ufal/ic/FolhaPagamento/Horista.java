package br.ufal.ic.FolhaPagamento;

public class Horista extends Empregado{
	private double salarioPorHora;
	private int horasTabalhadas;
	private int horasExtras;
	
	public Horista(String nome, String endereco, int id, double salarioPorHora) {
		super(nome, endereco, id);
		
		this.salarioPorHora = salarioPorHora;
		this.horasTabalhadas = 0;
	}
	
	public void setSalarioBruto() {
		this.salarioBruto += this.horasTabalhadas * this.salarioPorHora;
		this.salarioBruto += this.horasExtras * 1.5;
	}
	
	public void setHorasExtras() {
		if(this.horasTabalhadas >= 8 && this.horasTabalhadas <= 24) {
			int aux = this.horasTabalhadas - 8;
			this.horasExtras = aux;
		}
	}
}
