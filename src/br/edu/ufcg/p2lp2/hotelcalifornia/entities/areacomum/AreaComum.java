package br.edu.ufcg.p2lp2.hotelcalifornia.entities.areacomum;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class AreaComum {
	protected static int aux = 1;
	protected int contador;
	protected String titulo;
	protected String tipo;
	protected LocalTime dataInicio;
	protected LocalTime dataFim;
	protected double valorPessoa;
	protected int numPessoas;
	protected boolean disponivel;
	protected boolean isPago;
	
	public AreaComum(String titulo, String tipo, LocalTime dataInicio, LocalTime dataFim,int numPessoas, double valorPessoa,boolean disponivel) {
		this.titulo = titulo;
		this.tipo = null;
		this.dataInicio = null;
		this.dataFim = null;
		this.valorPessoa = valorPessoa;
		this.numPessoas = numPessoas;
		this.disponivel = disponivel;
		this.contador = aux++;
		this.isPago = false;
	}

	public abstract String toString();
	
	public boolean isPago() {
		return isPago;
	}

	public void setPago(boolean isPago) {
		this.isPago = isPago;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalTime dataFim) {
		this.dataFim = dataFim;
	}

	public double getValorPessoa() {
		return valorPessoa;
	}

	public void setValorPessoa(double valorPessoa) {
		this.valorPessoa = valorPessoa;
	}

	public int getNumPessoas() {
		return numPessoas;
	}

	public void setNumPessoas(int numPessoas) {
		this.numPessoas = numPessoas;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public  String formataHora(LocalTime hora1){
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm");
		String hora = dt.format(hora1) + ":00";
		return hora;
	}

}
