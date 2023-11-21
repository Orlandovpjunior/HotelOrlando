package entities.pagamento;

import java.util.Objects;

public abstract class Forma {
	
	protected String tipo;
	protected int id;
	protected double percentualDesconto;
	protected int parcelas;
	protected double valor;
	protected boolean situacaoDoPagamento;
	
	
	public Forma(String tipo, int id, double pD, int parcelas, double valor) {
		this.tipo = tipo;
		this.id = id;
		this.percentualDesconto = pD;
		this.parcelas = parcelas;
		this.valor = valor;
		this.situacaoDoPagamento = false;
	}

public abstract double getPercentualDesconto();
public abstract void setPercentualDesconto(double pD);
public abstract String getInfo();
public abstract double getValor();
public abstract void setValor(double nv);

@Override
public int hashCode() {
	return Objects.hash(percentualDesconto, tipo);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Forma other = (Forma) obj;
	return Double.doubleToLongBits(percentualDesconto) == Double.doubleToLongBits(other.percentualDesconto)
			&& Objects.equals(tipo, other.tipo);
}




}