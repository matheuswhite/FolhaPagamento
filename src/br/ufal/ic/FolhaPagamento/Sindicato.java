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
	
	public void addAssociado(Empregado empregado) {
		
		if(!empregado.isSindicato()) {
			int id = gerarId(this.limiteAssociados);
		
			this.associados.put(id, empregado);
			
			empregado.AssociarAoSindicato(true, id);
		}
		else {
			System.out.println("Empregado já associado!");
		}
	}
	
	protected boolean findAssociado(int i) {
		boolean find = false;
		
		if(!this.associados.isEmpty()) {
			if(this.associados.get(Integer.valueOf(i)) != null) {
				find = true;
			}
		}
		
		return find;
	}
	
	
	public void setTaxa(double taxa, int matricula) {
		if(this.associados.containsKey(matricula)) {
			Empregado empregado = this.associados.get(matricula);
			this.taxaFixa.put(empregado, taxa);
		}
	}
	
	public void cobrarTaxaFixa() {
		for(int x = 0; x < this.associados.size(); ++x) {
			if(this.associados.containsKey(x)) {
				Empregado empregado = this.associados.get(x); 
				empregado.salarioLiquido = empregado.salarioBruto - this.taxaFixa.get(empregado);
			}
		}
	}
	
	public void cobrarTaxaFixaIndividual(Empregado empregado) {
		empregado.salarioLiquido = empregado.salarioBruto - this.taxaFixa.get(empregado);
	}
	
	public void cobrarTaxaExtra(double valor, int matricula) {
		Empregado empregado = this.associados.get(matricula);
		empregado.salarioProximoMes -= valor;
		empregado.debitoProximoMes = true;
	}
}
