package br.edu.ufcg.p2lp2.hotelcalifornia.entities.quartos;

import java.time.LocalDateTime;
import java.util.Arrays;

public class QuartoDouble extends Quartos {

	private String pedidos[];
	private String tipo;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	public QuartoDouble(int numQuarto,double precoBase, double precoPorPessoa, int qtdMaxPessoas, String[] pedidos) {
		super(numQuarto, precoBase, precoPorPessoa, qtdMaxPessoas);
		this.pedidos = pedidos;
		this.tipo = "Double";
		this.qtdMaxPessoas = 2;
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

	public String[] getPedidos() {
		return pedidos;
	}

	public void setPedidos(String[] pedidos) {
		this.pedidos = pedidos;
	}

	public boolean isReservado() {
		return isReservado;
	}

	public void setReservado(boolean isReservado) {
		this.isReservado = isReservado;
	}
	
	@Override
	public String toString() {
		System.out.println(this.calculaDiaria());
		return String.format("[%d] Quarto Double (custo basico: R$%.2f por pessoa: R$%.2f >>> R$%.2f diária). Pedidos: %s" , numQuarto,precoBase,precoPorPessoa,this.calculaDiaria(),Arrays.toString(pedidos));
	}

	@Override
	public void setDates(LocalDateTime dataInicio, LocalDateTime dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	@Override
	public void qtdMaxPessoas(int qtdMaxPessoas) {
		this.qtdMaxPessoas = 2;
		
	}
  
}
