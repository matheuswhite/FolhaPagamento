package br.ufal.ic.FolhaPagamento;

import java.util.Calendar;
import java.util.Date;

public class Horista extends Empregado{
	private double salarioPorHora;
	private int horasTabalhadas;
	private int horasExtras;
	
	public Horista(String nome, String endereco, int id) {
		super(nome, endereco, id);
	}
	
	public void setSalarioPorHora(double salarioPorHora) {
		this.salarioPorHora = salarioPorHora;
		this.setHorasTrabalhadas();
	}
	
	public double getSalarioPorHora() {
		return this.salarioPorHora;
	}
	
	@SuppressWarnings("deprecation")
	public void setHorasTrabalhadas() {
		this.horasTabalhadas = this.pontoInicio.getHours() - this.pontoFim.getHours();
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
