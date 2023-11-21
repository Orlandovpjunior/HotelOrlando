package entities.quartos;

import java.time.LocalDateTime;
import java.util.Arrays;

public class QuartoFamily extends Quartos{

	private String pedidos[];
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private int qtdMaxPessoas;
	
	public QuartoFamily(int numQuarto, double precoBase, double precoPorPessoa,  int qtdMaxPessoas, String[] pedidos) {
		super(numQuarto, precoBase, precoPorPessoa, qtdMaxPessoas);
		this.pedidos = pedidos;
		this.qtdMaxPessoas = qtdMaxPessoas;
	}
	
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}
	
	public double calculaDiaria() {
		return this.getPrecoBase() + (this.getPrecoPorPessoa() * this.qtdMaxPessoas);	
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

	public boolean isReservado() {
		return isReservado;
	}

	public void setReservado(boolean isReservado) {
		this.isReservado = isReservado;
	}

	public void setPedidos(String[] pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return String.format("[%d] Quarto Family (custo basico: R$%.2f, por pessoa: R$%.2f >>> R$%.2f diária). Capacidade: %02d pessoa(s). Pedidos: %s" , numQuarto,precoBase,precoPorPessoa,calculaDiaria(),qtdMaxPessoas,Arrays.toString(pedidos));
	}

	@Override
	public void setDates(LocalDateTime dataInicio, LocalDateTime dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	@Override
	public void qtdMaxPessoas(int qtdMaxPessoas) {
		this.qtdMaxPessoas = qtdMaxPessoas;
	}
  
}
