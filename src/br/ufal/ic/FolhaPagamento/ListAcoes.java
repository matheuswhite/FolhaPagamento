package br.ufal.ic.FolhaPagamento;

import java.util.List;
import java.util.LinkedList;

public class ListAcoes extends LinkedList{
	private List<String> acoes;
	
	public ListAcoes() {
		acoes = new LinkedList<String>();
	}
	
	public void add(String acao) {
		acao.toUpperCase();
		acoes.add(acao);
	}
	
	public void undo() {
		
	}
	
	public void redo() {
		
	}
}
