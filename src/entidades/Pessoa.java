package entidades;

public class Pessoa {
	private String nome, cpf;
	private int idade;
	@SuppressWarnings("unused")
	private boolean isCrianca;
	private Cartao cartao;

	public Pessoa(String nome, int idade, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.isCrianca = isCrianca();
	}

	public Pessoa() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean isCrianca() {
		if (idade < 7) {
			return true;
		}
		return false;
	}
	
	public void setCrianca(boolean isCrianca) {
		this.isCrianca = isCrianca;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
}
