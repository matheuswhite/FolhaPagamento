package br.ufal.ic.FolhaPagamento;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Sindicato {
	private Map<Integer,Empregado> associados;
	private final int limiteAssociados = 2000;
	
	//mudar para hashmap
	private double taxaFixa = 100.00;
	
	public Sindicato() {
		this.associados = new HashMap<Integer, Empregado>();
	}
	
	
	
	
	protected int gerarId(int limiteAssociados) {
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
			
			empregado.setSindicato(true);
			empregado.setMatricula(id);
		}
		else {
			// erro msg
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
	
	public void cobrarTaxaFixa() {
		for(int x = 0; x < this.associados.size(); ++x) {
			if(this.associados.containsKey(x)) {
				Empregado empregado = this.associados.get(x); 
				empregado.salarioLiquido = empregado.salarioBruto - this.taxaFixa;
			}
		}
	}
	
	public void cobrarTaxaExtra(double valor, int matricula) {
		Empregado empregado = this.associados.get(matricula);
		empregado.salarioProximoMes -= valor;
		empregado.debitoProximoMes = true;
	}
}
