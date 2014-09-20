package br.ufal.ic.FolhaPagamento.Acoes;

import java.util.List;

import br.ufal.ic.FolhaPagamento.Pagamento;

public interface Acoes {
	public void refaz(Pagamento pagamento);
	public void desfaz(Pagamento pagamento);
}
