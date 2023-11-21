package br.edu.ufcg.p2lp2.hotelcalifornia.entities.quartos;

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
		this.dataInicio = null;
		this.dataFim = null;
		this.pedidos = null;
		this.isReservado = false;
	}
	
	public abstract void qtdMaxPessoas(int qtdMaxPessoas);
	public abstract void setPedidos(String[] pedidos);
	public abstract LocalDateTime getDataInicio();
	public abstract LocalDateTime getDataFim();
	public abstract void setDates(LocalDateTime dataInicio, LocalDateTime dataFim);
	public abstract double calculaDiaria();
	public abstract boolean isReservado();
	public abstract void setReservado(boolean isReservado);
	public abstract String getTipo();
	public abstract double getPrecoBase();
	public abstract int getQtdMaxPessoas();
	public abstract double getPrecoPorPessoa();

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
