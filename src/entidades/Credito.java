package entidades;

import java.util.Date;

public class Credito extends Cartao{
	
	private double limite;
	private double limiteDisponivel;
	
	public Credito(String numero, Date validade, double limite, Pessoa titular) {
		super(numero, validade, titular, true);
		this.limite = limite;
		this.limiteDisponivel = limite;
	}
	
	public Credito(String numero, Date validade, double limite, Pessoa titular, 
			double limiteDisponivel) {
		super(numero, validade, titular, true);
		this.limite = limite;
		this.limiteDisponivel = limiteDisponivel;
	}



	public boolean verificaLimite(double valor) {
		if(this.limiteDisponivel >= valor){
			return true;
		}
		else {
			return false;
		}	
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public double getLimiteDisponivel() {
		return limiteDisponivel;
	}

	public void setLimiteDisponivel(double limiteDisponivel) {
		this.limiteDisponivel = limiteDisponivel;
	}
	
	public boolean fazerCompra(double v) {
		if(verificaLimite(v) == true) {
			limiteDisponivel -= v;
			System.out.println("\nCompra em processamento. Aguarde 3 dias para verificar em sua fatura.");
			return true;
		}
		else {
			System.out.println("O limite do cartão não permite a compra.");
			return false;
		}
	}
}
