package br.ufal.ic.FolhaPagamento;

import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import br.ufal.ic.FolhaPagamento.Acoes.*;

public class ListAcoes {
	private Stack<Acoes> conteudo;
	private Stack<Acoes> conteudoAux;
	
	public ListAcoes() {		
		conteudo = new Stack<Acoes>();
		conteudoAux = new Stack<Acoes>();
	}
	
	public void add(Acoes object) {
		conteudo.push(object);
	}

	public void undo(Pagamento pagamento) {
		try {
			Acoes acao = conteudo.pop();
			acao.desfaz(pagamento);
			conteudoAux.push(acao);
		}
		catch (StackOverflowError ex) {
			System.out.println("OverFlow da pilha" + ex);
		}
		catch (EmptyStackException ex) {
			System.out.println("Pilha vazia" + ex);
		}
	}
	
	public void redo(Pagamento pagamento) {
		try {
			Acoes acao = conteudoAux.pop();
			acao.refaz(pagamento);
			conteudo.push(acao);
		}
		catch (StackOverflowError ex) {
			System.out.println("OverFlow da pilha" + ex);
		}
		catch (EmptyStackException ex) {
			System.out.println("Pilha vazia" + ex);
		}
	}
}
