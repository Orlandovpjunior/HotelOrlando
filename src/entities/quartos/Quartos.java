package entities.quartos;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Quartos {
	protected int numQuarto;
	protected double precoBase;
	protected double precoPorPessoa;
	protected int qtdMaxPessoas;
	protected LocalDateTime dataInicio;
	protected LocalDateTime dataFim;
	protected String pedidos[];
	protected boolean isReservado;
	
	public Quartos(int numQuarto, double precoBase, double precoPorPessoa, int qtdMaxPessoas) {
		this.numQuarto = numQuarto;
		this.precoBase = precoBase;
		this.precoPorPessoa = precoPorPessoa;
		this.qtdMaxPessoas = qtdMaxPessoas;
	}
	
	public abstract void qtdMaxPessoas(int qtdMaxPessoas);
	public abstract void setPedidos(String[] pedidos);
	public abstract void setDates(LocalDateTime dataInicio, LocalDateTime dataFim);
	public abstract double calculaDiaria();

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public int getNumQuarto() {
		return numQuarto;
	}
	
	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}
	
	public double getPrecoBase() {
		return precoBase;
	}
	
	public void setPrecoBase(double precoBase) {
		this.precoBase = precoBase;
	}

	public double getPrecoPorPessoa() {
		return precoPorPessoa;
	}

	public void setPrecoPorPessoa(double precoPorPessoa) {
		this.precoPorPessoa = precoPorPessoa;
	}

	public int getQtdMaxPessoas() {
		return qtdMaxPessoas;
	}

	public void QtdMaxPessoas(int qtdMaxPessoas) {
		this.qtdMaxPessoas = qtdMaxPessoas;
	}
	
	public boolean isReservado() {
		return isReservado;
	}

	public void setReservado(boolean isReservado) {
		this.isReservado = isReservado;
	}

	public String[] getPedidos() {
		return pedidos;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numQuarto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quartos other = (Quartos) obj;
		return numQuarto == other.numQuarto;
	}
}
