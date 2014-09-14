package br.ufal.ic.FolhaPagamento;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Assalariado extends Empregado{
	
	public Assalariado(String nome, String endereco, int id) {
		super(nome, endereco, id);
	}
	
	public void setSalarioBruto(double salarioFixo) {
		this.salarioBruto = salarioFixo;
	}
}
