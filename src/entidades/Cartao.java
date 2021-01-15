package entidades;

import java.util.Date;

public abstract class Cartao {
	private String numero;
	private Date validade;
	@SuppressWarnings("unused")
	private Pessoa titular;
	private boolean isCredito;
	
	public boolean isCredito() {
		return isCredito;
	}

	public void setCredito(boolean isCredito) {
		this.isCredito = isCredito;
	}

	public Cartao(String numero, Date validade, Pessoa titular, boolean isCredito) {
		this.numero = numero;
		this.validade = validade;
		this.titular = titular;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public abstract boolean fazerCompra(double v);
}
