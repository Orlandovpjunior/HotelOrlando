package br.edu.ufcg.p2lp2.hotelcalifornia.entities.pagamento;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

import java.text.DecimalFormat;

public class Cartao extends Forma {
	
	public Cartao(int id, int parcelas, double valor) {
		super("CARTAO DE CREDITO", id, 0, parcelas, valor);
	}
	
	public Cartao(int id, double desconto) {
		super("CARTAO DE CREDITO", id, desconto, 1, 0);
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public String getInfo() {
		double percentualDeDesconto = this.percentualDesconto * 100;
		return String.format("[%d] Forma de pagamento: %s (%.0f%% de desconto em pagamentos)", this.id, this.tipo, percentualDeDesconto);
	}
	
	public String toString() {
		double valorreal = this.valor - (this.valor * this.percentualDesconto);
		double valorparcela = valorreal / this.parcelas;
		DecimalFormat df = new DecimalFormat("###,###.00");
		return this.getInfo() + "\nTotal Efetivamente Pago: R$" + df.format(this.valor) + " em " + this.parcelas + "x de R$" + df.format(valorparcela);
	}

	
	public double getPercentualDesconto() {
		return this.percentualDesconto;
	}

	
	public void setPercentualDesconto(double pD) {
		if (pD > 0) {
			this.percentualDesconto = pD;
		} else {
			throw new HotelCaliforniaException("DESCONTO INVÁLIDO");
		}
	}
	
	public double getValor() {
		return this.valor;
	}
	
	public void setValor(double novoValor) {
		this.valor = novoValor;
	}

}