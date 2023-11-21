package br.edu.ufcg.p2lp2.hotelcalifornia.entities.refeicao;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Refeicao {
    private final long idRefeicao;
    private final String titulo;
    private final String tipo;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private double valor;
    private boolean disponibilidade;

	public Refeicao(long idRefeicao, String tipo, String titulo,  LocalTime horarioInicio, LocalTime horarioFim, double valor, boolean disponibilidade) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.valor = valor;
        this.disponibilidade = disponibilidade;
        this.idRefeicao = idRefeicao;
    }

    public String  formataHoraComH(LocalTime hora){
        return (hora.toString()).replace(":","h");
    }

    public void setRefeicao(LocalTime horarioInicio, LocalTime horarioFim, double valor, boolean disponibilidade){
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.valor = valor;
        this.disponibilidade = disponibilidade;

    }

    public String getTitulo() {
        return titulo;
    }
    
    public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public LocalTime getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(LocalTime horarioFim) {
		this.horarioFim = horarioFim;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String getTipo() {
		return tipo;
	}

    public String formataHora(LocalTime data) {
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormatada = formatterHora.format(data);
        return horaFormatada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Refeicao refeicao = (Refeicao) o;
        return Objects.equals(titulo, refeicao.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }

    @Override
    public String toString() {
        String status;
        String tipoRefeicao = "";
        if (this.tipo.equals("CAFE_DA_MANHA")) tipoRefeicao = "Cafe-da-manha";
        if (this.tipo.equals("ALMOCO")) tipoRefeicao = "Almoco";
        if (this.tipo.equals("JANTAR")) tipoRefeicao = "Jantar";
        if (this.disponibilidade) status = "VIGENTE";
        else status = "INDISPONIVEL";
        return "[" + this.idRefeicao + "] " + tipoRefeicao + ": " + this.titulo + " (" + formataHoraComH(this.horarioInicio) + " as "  + formataHoraComH(this.horarioFim) +
                "). Valor por pessoa: R$" + String.format("%.2f", this.valor) + ". " +  status + ".\n";
    }
}