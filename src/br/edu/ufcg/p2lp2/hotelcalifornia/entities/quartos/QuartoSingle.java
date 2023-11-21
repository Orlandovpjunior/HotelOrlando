package br.edu.ufcg.p2lp2.hotelcalifornia.entities.quartos;

import java.time.LocalDateTime;

public class QuartoSingle extends Quartos{
	
	private String tipo;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	
	public QuartoSingle(int numQuarto,double precoBase, double precoPorPessoa, int qtdMaxPessoas) {
		super(numQuarto, precoBase, precoPorPessoa, qtdMaxPessoas);
		this.tipo = "Single";
		this.qtdMaxPessoas = 1;
		this.dataInicio = null;
		this.dataFim = null;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	
	public String getTipo() {
		return tipo;
	}

	public double calculaDiaria() {
		return this.getPrecoBase() + (this.getPrecoPorPessoa() * this.getQtdMaxPessoas());	
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


	public void setQtdMaxPessoas(int qtdMaxPessoas) {
		this.qtdMaxPessoas = qtdMaxPessoas;
	}

	public boolean isReservado() {
		return isReservado;
	}

	public void setReservado(boolean isReservado) {
		this.isReservado = isReservado;
	}
	
	@Override
	public void setDates(LocalDateTime dataInicio, LocalDateTime dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	@Override
	public String toString() {
	    return String.format("[%d] Quarto Single (custo basico: R$%.2f; por pessoa: R$%.2f >>> R$%.2f diária)", numQuarto, precoBase, precoPorPessoa, calculaDiaria());
	}

	@Override
	public void setPedidos(String[] pedidos) {
	}
	
	@Override
	public void qtdMaxPessoas(int qtdMaxPessoas) {
		this.qtdMaxPessoas = 1;
	}

}
