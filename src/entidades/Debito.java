package entidades;

import java.util.Date;

public class Debito extends Cartao{
	
	private double saldo;
	
	public Debito(String numero, Date validade, double saldo, Pessoa titular) {
		super(numero, validade, titular, false);
		this.saldo = saldo;
	}

	public boolean verificaSaldo(double valor) {
		if(this.saldo >= valor) {
			return true;
		}
		else {
			return false;
		}	
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public boolean fazerCompra(double v) {
		if(verificaSaldo(v)) {
			saldo -= v;
			System.out.println("Compra realizada com sucesso.");
			return true;
		}else {
			System.out.println("O saldo da conta não permite a compra.");
			return false;
		}
	}
}
