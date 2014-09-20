package br.ufal.ic.FolhaPagamento;

import java.util.Date;

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
		Acoes acao = conteudo.pop();
		acao.desfaz(pagamento);
		conteudoAux.push(acao);
	}
	
	public void redo(Pagamento pagamento) {
		Acoes acao = conteudoAux.pop();
		acao.refaz(pagamento);
		conteudo.push(acao);
	}
}
