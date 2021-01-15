package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Voo {

	private String cidadeOrigem, cidadeDestino;
	private Date dataPartida;
	private int numeroDePassageirosMaximo, numeroDePassageirosAtual, numeroVoo;
	private double preco;
	
	public Voo(String cidadeOrigem, String cidadeDestino, Date dataPartida, int numeroDePassageirosMaximo,
			int numeroVoo, double preco) {
		this.cidadeOrigem = cidadeOrigem;
		this.cidadeDestino = cidadeDestino;
		this.dataPartida = dataPartida;
		this.numeroDePassageirosMaximo = numeroDePassageirosMaximo;
		this.numeroVoo = numeroVoo;
		this.preco = preco;
		this.numeroDePassageirosAtual = 0;
	}

	public void comprarPassagem(int nAdultos, int nCriancas, Pessoa p) {
		if(this.verificaLotacao(nAdultos + nCriancas)) {
			System.out.println("Número de assentos que deseja comprar é maior do que o disponível.");
			return;
		}
		
		Cartao c = p.getCartao();
		
		if(c == null) {
			System.out.println("Não é possível fazer uma compra através de um cadastro sem cartão");
			return;
		}
		
		if(c.fazerCompra(nAdultos * preco + nCriancas * (preco / 2))) {
			numeroDePassageirosAtual += nAdultos + nCriancas;
			imprimirBilhete(nAdultos, nCriancas, p);
			return;
		}
	}
	
	private void imprimirBilhete(int nAdultos, int nCriancas, Pessoa p) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("...\nPASSAGEM COMPRADA COM SUCESSO!");
		System.out.println("------------------------------------------------");
		System.out.printf("|	BILHETE: %.0f                           \n", Math.random() * 1000);
		System.out.println("|	Voo: " + this.numeroVoo + "                              ");
		System.out.println("|	Origem: " + this.cidadeOrigem + "                        ");
		System.out.println("|	Destino: " + this.cidadeDestino + "	                       ");
		System.out.println("|	Data: " + sdf1.format(this.dataPartida) + "                 ");
		System.out.println("|	Nome do comprador: " + p.getNome() + "               ");
		System.out.printf("|	Número de assentos (Adulto): %d         \n", nAdultos);
		System.out.printf("|	Número de assentos (Criança): %d        \n", nCriancas);
		System.out.println("------------------------------------------------\n");
	}

	public boolean verificaLotacao(int n) {
		if ((numeroDePassageirosAtual + n) > numeroDePassageirosMaximo) {
			return true;
		}
		return false;
	}
	
	public void imprimeVoo() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("\n-------------------------------------------------------------------------");
		System.out.printf("Voo: %d\nOrigem: %s\nDestino: %s\nData de partida: " + sdf.format(dataPartida) + 
				"\nAssentos disponíveis: %d", numeroVoo, cidadeOrigem, cidadeDestino, numeroDePassageirosMaximo - numeroDePassageirosAtual);
		System.out.println("\n-------------------------------------------------------------------------");
	}

	public String getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(String cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public String getCidadeDestino() {
		return cidadeDestino;
	}

	public void setCidadeDestino(String cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public int getNumeroDePassageirosMaximo() {
		return numeroDePassageirosMaximo;
	}

	public void setNumeroDePassageirosMaximo(int numeroDePassageirosMaximo) {
		this.numeroDePassageirosMaximo = numeroDePassageirosMaximo;
	}

	public int getNumeroDePassageirosAtual() {
		return numeroDePassageirosAtual;
	}

	public void setNumeroDePassageirosAtual(int numeroDePassageirosAtual) {
		this.numeroDePassageirosAtual = numeroDePassageirosAtual;
	}

	public int getNumeroVoo() {
		return numeroVoo;
	}

	public void setNumeroVoo(int numeroVoo) {
		this.numeroVoo = numeroVoo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}
