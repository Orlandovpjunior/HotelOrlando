package entities.reservas;

import java.time.Duration;
import java.time.LocalDateTime;

import entities.quartos.Quartos;
import entities.refeicao.Refeicao;

public abstract class Reservas {
	protected static int aux = 1;
	protected int contador;
	protected String idCliente;
	protected int numQuarto;
	protected LocalDateTime dataInicio;
	protected LocalDateTime dataFim;
	protected Refeicao[] refeicoes;
	protected String tipo;
	protected Quartos quarto;
	protected boolean statusPago;
	protected int numPessoas;

	public Reservas(String idCliente, String tipo) {
		this.idCliente = idCliente;
	    this.contador = aux++;
	    this.tipo = tipo;
	}

	public abstract double valorReserva();

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public int getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public Refeicao[] getRefeicoes() {
		return refeicoes;
	}

	public void setRefeicoes(Refeicao[] refeicoes) {
		this.refeicoes = refeicoes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Quartos getQuarto() {
		return quarto;
	}

	public void setQuarto(Quartos quarto) {
		this.quarto = quarto;
	}

	public boolean isStatusPago() {
		return statusPago;
	}

	public void setStatusPago(boolean statusPago) {
		this.statusPago = statusPago;
	}

	public int getNumPessoas() {
		return numPessoas;
	}

	public void setNumPessoas(int numPessoas) {
		this.numPessoas = numPessoas;
	}

	protected int qtdeDiarias(LocalDateTime data1, LocalDateTime data2) {
		LocalDateTime dataInicio = LocalDateTime.of(data1.getYear(), data1.getMonth(), data1.getDayOfMonth(), 00, 00);
		LocalDateTime dataFinal = LocalDateTime.of(data2.getYear(), data2.getMonth(), data2.getDayOfMonth(), 00, 00);
		int diarias = (int)Duration.between(dataInicio, dataFinal).toDays();
		return  diarias;
	}
}
