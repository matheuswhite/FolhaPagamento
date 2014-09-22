package br.ufal.ic.FolhaPagamento;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Sindicato {
	private Map<Integer,Empregado> associados;
	private final int limiteAssociados = 2000;
	private Map<Empregado, Double> taxaFixa;
	
	public Sindicato() {
		this.associados = new HashMap<Integer, Empregado>();
		this.taxaFixa = new HashMap<Empregado, Double>();
	}
	
	
	
	
	public void delAssociado(int matricula) {
		try {
			Empregado empregado = this.associados.get(matricula);
			taxaFixa.remove(empregado);
			associados.remove(matricula);
		}
		catch (NullPointerException e) {
			System.out.println("Empregado não encontrado!\nMude o numero de matricula. " + e.getMessage());
		}
	}
	
	
	public int gerarId(int limiteAssociados) {
		int id = 0;
		boolean exit = false;
		Random gerador = new Random();
		
		while(!exit) {
			id = gerador.nextInt(limiteAssociados);
			
			if(!findAssociado(id)) {
				exit = true;
			}
		}
		
		return id;
	}
	
	public void addAssociado(Empregado empregado, double taxa) {
		
		int id = gerarId(this.limiteAssociados);
		
		try {
			this.associados.put(id, empregado);
		}
		catch (Exception ex) {
			System.out.println("Exception na HashMap");
		}
		
		empregado.AssociarAoSindicato(true, id);
		
		this.setTaxaFixa(taxa, id);
	}
	
	
	protected boolean findAssociado(int i) {
		boolean find = false;
		
		try {
			Empregado empregado = this.associados.get(i);
			if(empregado != null) {
				find = true;
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Empregado nao encontrado! " + e.getMessage());
		}
		
		return find;
	}
	
	
	public void setTaxaFixa(double taxa, int matricula) {
		try {
			Empregado empregado = this.associados.get(matricula);
			this.taxaFixa.put(empregado, taxa);
			empregado.taxaFixa -= taxa;
		}
		catch ( NullPointerException e) {
			System.out.println("Empregado nao Encontrado! " + e.getMessage());
		}	
	}
	
	public void cobrarTaxaExtra(double valor, int matricula, int mes) {
		try {
			Empregado empregado = this.associados.get(matricula);
			empregado.salarioProximoMes[mes] -= valor;
			empregado.debitoMes[mes] = true;
		}
		catch (NullPointerException e) {
			System.out.println("Empregado não encontrado! " + e.getMessage());
		}
		
	}
	
}
