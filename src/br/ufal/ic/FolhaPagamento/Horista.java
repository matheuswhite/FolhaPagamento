package br.ufal.ic.FolhaPagamento;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	
	//@SuppressWarnings("deprecation")
	public void setHorasTrabalhadas() {
		Calendar cal = new GregorianCalendar();
		Calendar calFim = new GregorianCalendar();
		
		cal.setTime(pontoInicio);
		calFim.setTime(pontoFim);
		
		this.horasTabalhadas = cal.get(Calendar.HOUR_OF_DAY) - calFim.get(Calendar.HOUR_OF_DAY);
		
		//this.horasTabalhadas = this.pontoInicio.getHours() - this.pontoFim.getHours();
	}
	
	public void setSalarioBruto() {
		this.setHorasTrabalhadas();
		this.setHorasExtras();
		
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
