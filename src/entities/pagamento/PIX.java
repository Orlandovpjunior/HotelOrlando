package entities.pagamento;

import java.text.DecimalFormat;

import exception.HotelCaliforniaException;

public class PIX extends Forma {
	
	
	public PIX(int id, double percentualDesconto, double valor) {
		super("PIX", id, percentualDesconto, 1, valor);
	}
	
	public PIX(int id, double percentualDesconto) {
		super("PIX", id, percentualDesconto, 1, 0);
	}

	@Override
	public double getPercentualDesconto() {
		return this.percentualDesconto;
	}

	@Override
	public void setPercentualDesconto(double pD) {
		if (pD > 0) {
			this.percentualDesconto = pD;
		} else {
			throw new HotelCaliforniaException("DESCONTO INVÁLIDO");
		}
	}
	
	public String getInfo() {
		double percentualDeDesconto = this.percentualDesconto * 100;
		return String.format("[%d] Forma de pagamento: %s (%.0f%% de desconto em pagamentos)", this.id, this.tipo, percentualDeDesconto);
	}
	
	public String toString() {
		double valorreal = this.valor - (this.valor * this.percentualDesconto);
		DecimalFormat df = new DecimalFormat("###,###.00");
		return this.getInfo() + "\nTotal Efetivamente Pago: R$" + df.format(valorreal) + " em 1x de R$" + df.format(valorreal); 
	}
	
	public double getValor() {
		return this.valor;
	}
	
	public void setValor(double novoValor) {
		this.valor = novoValor;
	}


}