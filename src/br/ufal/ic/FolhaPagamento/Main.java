package br.ufal.ic.FolhaPagamento;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

	private Pagamento pagamento;
	private ListAcoes acoes;
	private Scanner scan;
	private final int limiteEmpregados = 2000;
	private GregorianCalendar systemDate;
	private boolean dataSistema;
	
	public Main(Date date) {
		pagamento = new Pagamento();
		acoes = new ListAcoes();
		scan = new Scanner(System.in);
		
		systemDate = new GregorianCalendar();
		systemDate.setTime(date);
		
		dataSistema = true;
	}
	
	public boolean findEmpregado(int id) {
		boolean saida = false;
		
		for(Empregado empregado : pagamento.getEmpregados()) {
			if(empregado.getId() == id) {
				saida = true;
			}
		}
		
		return saida;
	}
	
	public int gerarId(int limiteAssociados) {
		int id = 0;
		boolean exit = false;
		Random gerador = new Random();
		
		while(!exit) {
			id = gerador.nextInt(limiteAssociados);
			
			if(!findEmpregado(id)) {
				exit = true;
			}
		}
		
		return id;
	}
	
	private GregorianCalendar stringToDate(String string) {
		SimpleDateFormat format;
		Date date;
		GregorianCalendar cal = null;
		
		try {
			format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			date = format.parse(string);
			cal = (GregorianCalendar) GregorianCalendar.getInstance();
			cal.setTime(date);
			
			System.out.println("data: " + cal.getTime().toString());
		}
		catch (ParseException ex) {
			System.out.println("Exception na conversao da string para Gregoriancalendar. " + ex);
			ex.printStackTrace();
		}
		
		return cal;
	}
	
	private void fichaGeral(Empregado empregado) {
		System.out.println("Id: " + empregado.getId());
		System.out.println("Nome: " + empregado.getNome());
		System.out.println("Endereço: " + empregado.getEndereco());
		System.out.println("Método de pagamento: " + empregado.getMetodoPagamento());
		System.out.println("Pertence ao Sindicato: " + empregado.isSindicato());
		System.out.println("Taxa Sindical: " + empregado.taxaFixa);
	}
	
	private void fichaEmpregado(Empregado empregado) {
		
		if(empregado instanceof Horista) {
			System.out.println("\nHorista");
			fichaGeral(empregado);
			
			System.out.println("Salario por hora: " + ((Horista) empregado).getSalarioPorHora());
		}
		if(empregado instanceof Comissionados) {
			System.out.println("\nComissionado");
			fichaGeral(empregado);
			
			System.out.println("Salario bruto: " + ((Comissionados) empregado).getSalarioFixo());
			System.out.println("Comissao: " + ((Comissionados) empregado).getComissao());
		}
		else if(empregado instanceof Assalariado) {
			System.out.println("\nAssalariado");
			fichaGeral(empregado);
			
			System.out.println("Salario bruto: " + ((Assalariado) empregado).getSalarioFixo());
		}
	}
	
	private Empregado escolhaDeEmpregado(String string) {
		Empregado empregado = null;
		boolean exit = false;
		
		
		System.out.println("Escolha um empregado " + string + ": \n");
		String aux = "";
		
		if(pagamento.getEmpregados().isEmpty()) {
			System.out.println("Sem empregados cadastrados!\n");
			return null;
		}
		
		while(!exit) {
			for(int i=0; i < pagamento.getEmpregados().size(); ++i) {
				empregado = pagamento.getEmpregados().get(i);
			
				if(empregado instanceof Horista) {
					aux = "Horista";
				}
				if(empregado instanceof Assalariado) {
					aux = "Assalariado";
				}
				if(empregado instanceof Comissionados) {
					aux = "Comissionado";
				}
				
				System.out.println(i + "- " + empregado.getNome() + " / Id: " + empregado.getId() + " / Tipo: " + aux +"\n");
		
			}
			
			try {
				int entrada = scan.nextInt();
				empregado = pagamento.getEmpregados().get(entrada);
				this.fichaEmpregado(empregado);
				
				exit = true;
			}
			catch (NumberFormatException ex) {
				System.out.println("Digite um numero inteiro da lista abaixo. " + ex);
				ex.printStackTrace();
			}
			catch (NullPointerException ex) {
				System.out.println("Empregado não encontrado! " + ex);
				ex.printStackTrace();
			}
			catch (IndexOutOfBoundsException ex) {
				System.out.println("Escolha uma das opções abaixo. " + ex);
				//ex.printStackTrace();
			}
			catch (InputMismatchException ex) {
				System.out.println("Escolha uma das opções abaixo. " + ex);
			}
			finally {
				this.clearBuffer();
			}
		}
		
		return empregado;
	}
	
	private void clearBuffer() {
		scan.nextLine();
	}
	
	private void menuPrincipal() {
		System.out.println("1- Adicionar empregados");
		System.out.println("2- Remover empregado");
		System.out.println("3- Lançar cartao de ponto");
		System.out.println("4- Lançar venda");
		System.out.println("5- Lançar taxa extra");
		System.out.println("6- Alterar empregado");
		System.out.println("7- Rodar folha de pagamento");
		System.out.println("8- Undo");
		System.out.println("9- Redo");
	}
	
	private void addEmpregados() {
		int entrada = 0;
		String nome, endereco, metodo = null;
		double salario, comissao;
		int id;
		boolean exit = false;
		
		System.out.println("Nome:");
		nome = scan.nextLine();
		clearBuffer();
		
		System.out.println("Endereço:");
		endereco = scan.nextLine();
		clearBuffer();
		
		System.out.println("Seu id: ");
		id = this.gerarId(this.limiteEmpregados);
		System.out.println(String.valueOf(id));
		
		while(!exit) {
			System.out.println("Método de pagamento: ");
			System.out.println("1- Cheque pelos correios\t2- Cheque em mãos\t3- Depósito");
		
			try {
				entrada = scan.nextInt();
			
				switch (entrada) {
				case 1:
					metodo = "Cheque pelos correios";
					exit = true;
					break;
				case 2:
					metodo = "Cheque em mãos";
					exit = true;
					break;
				case 3:
					metodo = "Depósito";
					exit = true;
					break;
				default:
					break;
				}
			}
			catch (NumberFormatException ex) {
				System.out.println("Digite um inteiro");
			}
			finally {
				clearBuffer();
				entrada = 0;
			}
		}
		
		exit = false;
		
		while(!exit) {
			System.out.println("Escolha o tipo de empregado");
			System.out.println("1- Horista\t2- Assalariado\t3- Comissionado");
			
			try {
				
				entrada = scan.nextInt();
				
				switch(entrada) {
				case 1:
					
					System.out.println("Salario por hora:");
					salario = scan.nextDouble();
					
					Horista horista = new Horista(nome, endereco, id, salario);
					horista.setMetodoPagamento(metodo);
					fichaEmpregado(horista);
					this.pagamento.AdicionarEmpregado(horista);
					
					exit = true;
					break;
				case 2:
					
					System.out.println("Salario Fixo:");
					salario = scan.nextDouble();
					
					Assalariado assalariado = new Assalariado(nome, endereco, id, salario);
					assalariado.setMetodoPagamento(metodo);
					fichaEmpregado(assalariado);
					this.pagamento.AdicionarEmpregado(assalariado);
					
					exit = true;
					break;
				case 3:
					
					System.out.println("Salario Fixo:");
					salario = scan.nextDouble();
					clearBuffer();
					
					Comissionados comissionados = new Comissionados(nome, endereco, id, salario);
					
					System.out.println("Comissao:");
					comissao = scan.nextDouble();
					comissionados.setComissao(comissao);
					
					comissionados.setMetodoPagamento(metodo);
					fichaEmpregado(comissionados);
					this.pagamento.AdicionarEmpregado(comissionados);
					
					exit = true;
					break;
				}
			}
			catch (NumberFormatException ex) {
				System.out.println("Digite um inteiro para as escolhas e um numero decimal para os salarios e comissão. " + ex);
				ex.getStackTrace();
			}
			catch (InputMismatchException ex) {
				System.out.println(ex);
				System.out.println("digite os numeros decimais com virgula\n Exemplo: 20,0");
			}
			finally {
				clearBuffer();
				entrada = 0;
			}
		}
	}
	
	
	private void delEmpregados() {
		Empregado empregado;
		int entrada = 0;
		boolean exit = false;
		
		empregado = this.escolhaDeEmpregado("a ser removido");
		
		if(empregado == null) {
			return ;
		}
		
		while(!exit) {
			System.out.println("\nDeseja mesmo remover este Empregado?\n1- Sim\t2- Não\n");
			
			try {
				entrada = scan.nextInt();
				
				if(entrada == 1) {
					pagamento.RemoverEmpregado(empregado);
					exit = true;	
				}
				else if (entrada == 2) {
					exit = true;
				}
				else {
					System.out.println("Escolha uma opção acima");
				}	
			}
			catch (NumberFormatException ex){
				System.out.println("Digite um inteiro" + ex);
			}
			finally {
				clearBuffer();
			}
		}
	}
	
	private void cartaoPonto() {
		Empregado empregado;
		String inicio, fim;
		GregorianCalendar cal;
		GregorianCalendar cal2;
		
		empregado = this.escolhaDeEmpregado("para lancar um ponto");
		
		
		if(empregado == null) {
			return ;
		}
		//dd/MM/yyyy HH:mm:ss
		
		System.out.println("Entre com a data inicial:");
		System.out.println("Formato: dd/MM/aaaa HH:mm:ss");
		
		inicio = scan.nextLine();
		cal = stringToDate(inicio);
		this.clearBuffer();
		
		System.out.println("Entre com a data final: \n");
		System.out.println("Formato: dd/MM/aaaa HH:mm:ss");
		
		fim = scan.nextLine();
		cal2 = stringToDate(fim);
		this.clearBuffer();
		
		pagamento.LancarCartaoPonto(empregado, cal, cal2);
	}
	
	private void lancarVenda() {
		Empregado empregado = null;
		double valor = 0.0;
		String data;
		GregorianCalendar cal = null;
		boolean exit = false;
		
		while(!exit) {
			empregado = this.escolhaDeEmpregado("para lançar venda");
		
			if(!(empregado instanceof Comissionados)) {
				System.out.println("Somente comissionados podem lançar vendas.");
			}
			else {
				exit = true;
			}
		}
		
		exit = false;
		
		while(!exit) {
			System.out.println("Digite o valor da venda:");
			
			try {
				valor = scan.nextDouble();
				
				exit = true;
			}
			catch (NumberFormatException ex) {
				System.out.println("Digite um número decimal. " + ex);
			}
			catch (InputMismatchException ex) {
				System.out.println(ex);
				System.out.println("digite os numeros decimais com virgula\n Exemplo: 20,0");
			}
			finally {
				this.clearBuffer();
			}
		}
		
		exit = false;
		
		while(!exit) {
			System.out.println("Entre com a data da venda:");
			System.out.println("Formato: dd/MM/aaaa HH:mm:ss");
			
			data = scan.nextLine();
			cal = stringToDate(data);
			
			if(cal != null) {
				exit = true;
			}
			
			this.clearBuffer();
		}
		
		pagamento.LancarVenda(empregado, valor, cal);
	}
	
	private void taxaExtra() {
		Empregado empregado = null;
		double valor = 0;
		boolean exit = false;
		
		while(!exit) {
			empregado = this.escolhaDeEmpregado("para cobrar taxa extra");
			
			//apagar depois
			//empregado.AssociarAoSindicato(true, 200);
			
			if(empregado.isSindicato()) {
				exit = true;
			}
			else {
				System.out.println("Este empregado não esta cadastrado no sindicato. Escolha outro.");
			}
		}
		
		exit = false;
		
		while(!exit) {
			try {
				System.out.println("Digite o valor da taxa:");
				valor = scan.nextDouble();
				
				exit = true;
			}
			catch (NumberFormatException ex){
				System.out.println("Digite um numero decimal. " + ex);
			}
			catch (InputMismatchException ex) {
				System.out.println("Digite um numero decimal com virgula.\nExemplo: 20,0" + ex);
			}
			finally {
				this.clearBuffer();
			}
		}
		
		systemDate.add(Calendar.MONTH, 1);
		
		pagamento.LancarTaxaExtra(empregado, valor, systemDate);
	}
	
	private void alterarEmpregados() {
		String nome, endereco, metodoPagamento = null;
		int escolha = 0;
		int matricula = 0;
		boolean pertenceSindicato = false;
		boolean exit = false;
		double taxaSindical = 0;
		double salario, comissao;
		
		Empregado temp = this.escolhaDeEmpregado("para ser alterado");
		
		System.out.println("Novo nome: (x0 - para não alterar)");
		nome = scan.nextLine();
		if(nome.contentEquals("x0")) {
			nome = temp.getNome();
		}
		this.clearBuffer();
		
		System.out.println("Novo endereço: (x0 - para não alterar)");
		endereco = scan.nextLine();
		if(endereco.contentEquals("x0")){
			endereco = temp.getEndereco();
		}
		this.clearBuffer();
		
		
		while(!exit) {
			System.out.println("Novo metodo de pagamento:\n1- Cheque pelos correios\t2- Cheque em mãos\t3- Depósito\t4- Para não alterar");
			try {
				
				escolha = scan.nextInt();
				
				switch (escolha) {
				case 1:
					metodoPagamento = "Cheque pelos correios";
					exit = true;
					break;
				case 2:
					metodoPagamento = "Cheque em mãos";
					exit = true;
					break;
				case 3:
					metodoPagamento = "Depósito";
					exit = true;
					break;
				case 4:
					metodoPagamento = temp.getMetodoPagamento();
					exit = true;
					break;
				default:
					System.out.println("Escolha uma das opções abaixo.");
					break;
				}
			}
			catch (NumberFormatException ex) {
				System.out.println("Digite um numero inteiro");
			}
			finally {
				this.clearBuffer();
				escolha = 0;
			}
		}
		
		exit = false;
		
		
		while(!exit) {
			System.out.println("Pertence ao sindicato?\n1- Sim\t2- Nao\t3- Para não alterar");
			
			try {
				escolha = scan.nextInt();
				this.clearBuffer();
				
				switch (escolha) {
				case 1:
					if(!temp.isSindicato()) {
						pertenceSindicato = true;
						
						System.out.println("Digite a taxa sindical: \n");
						taxaSindical = scan.nextDouble();
						
						exit = true;
					}
					else {
						pertenceSindicato = true;
					}
					break;
				case 2:
					exit = true;
					break;
				case 3:
					pertenceSindicato = temp.isSindicato();
					exit = true;
					break;
				default:
					System.out.println("Escolha uma das opcões abaixo.");
					break;
				}
			}
			catch (NumberFormatException ex) {
				System.out.println("Digite um numero. " + ex);
			}
			catch (InputMismatchException ex) {
				System.out.println("Digite o numero decimal com virgula.\nExemplo: 20,00");
			}
			finally {
				this.clearBuffer();
				escolha = 0;
			}
		}
		
		exit = false;
		
		while(!exit) {
			System.out.println("Escolha o novo tipo de empregado:\n1- Horista\t2- Assalariado\t3- Comissionado");
			
			try {
				escolha = scan.nextInt();
				this.clearBuffer();
				
				switch (escolha) {
				case 1:
					
					System.out.println("Salario por hora:");
					salario = scan.nextDouble();
					
					Horista horista = new Horista(nome, endereco, temp.getId(), salario);
					
					pagamento.AlterarEmpregado(nome, endereco, horista, metodoPagamento, pertenceSindicato, matricula, taxaSindical, temp);
					
					this.fichaEmpregado(horista);
					
					exit = true;
					break;
				case 2:
					
					System.out.println("Salario Fixo:");
					salario = scan.nextDouble();
					
					Assalariado assalariado = new Assalariado(nome, endereco, temp.getId(), salario);
					
					pagamento.AlterarEmpregado(nome, endereco, assalariado, metodoPagamento, pertenceSindicato, matricula, taxaSindical, temp);
					
					this.fichaEmpregado(assalariado);
					
					exit = true;
					break;
				case 3:
					
					System.out.println("Salario Fixo:");
					salario = scan.nextDouble();
					clearBuffer();
					
					Comissionados comissionado = new Comissionados(nome, endereco, temp.getId(), salario);
					
					System.out.println("Comissao:");
					comissao = scan.nextDouble();
					comissionado.setComissao(comissao);
					
					pagamento.AlterarEmpregado(nome, endereco, comissionado,
								metodoPagamento, pertenceSindicato, matricula, 
								taxaSindical, temp);
					
					this.fichaEmpregado(comissionado);
					
					exit = true;
					break;
				default:
					System.out.println("Escolha uma das opcões abaixo.");
					break;
				}
			}
			catch (NumberFormatException ex) {
				System.out.println("Digite um numero. " + ex);
			}
			catch (InputMismatchException ex) {
				System.out.println("Digite o numero decimal com virgula.\nExemplo: 20,00. " + ex);
			}
			finally {
				this.clearBuffer();
				escolha = 0;
			}
		}
		
	}
	
	private void RodarFolha() {
		if(!dataSistema) {
			systemDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		else {
			dataSistema = false;
		}
		
		System.out.println("Dia: " + systemDate.getTime().toString());
		pagamento.rodarFolhaPagamento(systemDate);
	}
	
	private void undo() {
		pagamento.getListAcoes().undo(pagamento);
	}
	
	private void redo() {
		pagamento.getListAcoes().redo(pagamento);
	}
	
	public static void main(String[] args) {
		Date date = new Date(System.currentTimeMillis());
		
		Main window = new Main(date);
		int entrada = 0;
		boolean exit = false;
		
		while(!exit) {
			window.menuPrincipal();
			entrada = window.scan.nextInt();
			window.clearBuffer();
			
			switch(entrada) {
			case 1:
				window.addEmpregados();
				break;
			case 2:
				window.delEmpregados();
				break;
			case 3:
				window.cartaoPonto();
				break;
			case 4:
				window.lancarVenda();
				break;
			case 5:
				window.taxaExtra();
				break;
			case 6:
				window.alterarEmpregados();
				break;
			case 7:
				window.RodarFolha();
				break;
			case 8:
				window.undo();
				break;
			case 9:
				window.redo();
				break;
			default:
				exit = true;
				break;
			}
		}
		
	}
}
