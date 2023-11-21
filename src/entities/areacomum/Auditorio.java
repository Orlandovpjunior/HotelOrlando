package entities.areacomum;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Auditorio extends AreaComum {
	
	private LocalTime dataInicio;
	private LocalTime dataFim;
	private int numPessoas;
	
	public Auditorio(String titulo, String tipo, LocalTime dataInicio, LocalTime dataFim, double valorPessoa,int numPessoas, boolean disponivel) {
		super(titulo, tipo, dataInicio, dataFim, numPessoas, valorPessoa,disponivel);
		this.tipo = "AUDITORIO";
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.numPessoas = numPessoas;
	}
	
	public int getNumPessoas() {
		return numPessoas;
	}

	public void setNumPessoas(int numPessoas) {
		this.numPessoas = numPessoas;
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

	@Override
	public String toString() {
		LocalTime horaEntrada = this.dataInicio;
		LocalTime horaSaida = this.dataFim;
		DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH'h'mm");
		String horaEntradaFormatada = horaEntrada.format(horaFormatter);
		String horaSaidaFormatada = horaSaida.format(horaFormatter);
		if(this.valorPessoa == 0) {
			return String.format("[%d] AUDITORIO: %s (%s as %s). Valor por pessoa: Grátis. Capacidade: %d pessoa(s). VIGENTE.",this.getContador(), this.getTitulo(), horaEntradaFormatada, horaSaidaFormatada, this.getNumPessoas());
		} else if (this.isDisponivel() == true) {
			return String.format("[%d] AUDITORIO: %s (%s as %s). Valor por pessoa: R$%.2f. Capacidade: %d pessoa(s). VIGENTE.",this.getContador(), this.getTitulo(), horaEntradaFormatada, horaSaidaFormatada,this.getValorPessoa(), this.getNumPessoas());
		} else {
			return String.format("[%d] AUDITORIO: %s (%s as %s). Valor por pessoa: R$%.2f. Capacidade: %d pessoa(s). INDISPONIVEL.",this.getContador(), this.getTitulo(), horaEntradaFormatada, horaSaidaFormatada,this.getValorPessoa(), this.getNumPessoas());
		}
	}
}