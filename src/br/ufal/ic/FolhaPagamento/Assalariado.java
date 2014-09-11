package br.ufal.ic.FolhaPagamento;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public abstract class Assalariado extends Empregado{
	
	public Assalariado(String nome, String endereco, int id, double SalarioFixo) {
		super(nome, endereco, id);
		
		this.salarioBruto = SalarioFixo;
	}
	
	public abstract void registrarVenda(Date date, double valor);
}
