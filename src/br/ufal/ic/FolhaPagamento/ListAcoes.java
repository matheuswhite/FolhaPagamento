package br.ufal.ic.FolhaPagamento;

import java.util.List;
import java.util.Stack;
import java.util.LinkedList;

public class ListAcoes {
	private Stack<String> acoes;
	private Stack<String> aux;
	private List<Empregado> empregado;
	
	public ListAcoes() {
		acoes = new Stack<String>();
		aux = new Stack<String>();
		
		empregado = new LinkedList<Empregado>();
	}
	
	public void add(String acao, Empregado empregado) {
		acao.toUpperCase();
		acoes.push(acao);
		
		this.empregado.add(empregado);
	}
	
	public void add(String acao, Cartao cartao) {
		
	}
	
	public void add(String acao, Venda venda) {
		
	}
	
	public void add(String acao, Taxa taxa) {
		
	}
	
	public void add(String acao, FolhaPagamento folha) {
		
	}
	
	public void undo() {
		
	}
	
	public void redo() {
		
	}
}
