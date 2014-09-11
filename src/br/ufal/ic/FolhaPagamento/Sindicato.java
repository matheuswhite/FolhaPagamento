package br.ufal.ic.FolhaPagamento;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Sindicato {
	private Map<Integer,Empregado> associados;
	private final int limiteAssociados = 2000;
	
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
		int i = gerarId(this.limiteAssociados);
		
		this.associados.put(i, empregado);
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
}
