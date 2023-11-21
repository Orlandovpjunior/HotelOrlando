package br.edu.ufcg.p2lp2.hotelcalifornia.entities.reservas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.Usuario;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.areacomum.AreaComum;

public class ReservaAuditorio extends Reservas{
	private Usuario usuario;
	private int numPessoas;
	private AreaComum auditorio;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	
	public ReservaAuditorio(String idCliente, LocalDateTime dataInicio, LocalDateTime dataFim, Usuario usuario,AreaComum auditorio,int numPessoas,String tipo) {
		super(idCliente,tipo);
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.usuario = usuario;
		this.numPessoas = numPessoas;
		this.auditorio = auditorio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getNumPessoas() {
		return numPessoas;
	}

	public void setNumPessoas(int numPessoas) {
		this.numPessoas = numPessoas;
	}

	public AreaComum getAuditorio() {
		return auditorio;
	}

	public void setAuditorio(AreaComum auditorio) {
		this.auditorio = auditorio;
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

	public int estadia(LocalDateTime dataInicio, LocalDateTime dataFim) {
		int qtdeEstadia = qtdeDiarias(dataInicio, dataFim);
		return qtdeEstadia + 1;
	}

	@Override
	public double valorReserva() {
		AreaComum a = this.auditorio;
		LocalDateTime datainicio = this.dataInicio;
		LocalDateTime datafim = this.dataFim;
		return this.numPessoas * this.estadia(datainicio, datafim) * a.getValorPessoa();
	}

	@Override
	public String toString() {
		AreaComum a = this.auditorio;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataInicioFormatada = this.getDataInicio().format(formatter);
		String dataFimFormatada = this.getDataFim().format(formatter);
		if (!this.isStatusPago()) {
			if (a.getValorPessoa() == 0) {
				return String.format("[%d] Reserva de AUDITORIO em favor de:\n%s\nDetalhes da reserva:\n- Periodo: %s %s ate %s %s\n- Qtde. de Convidados: %d pessoa(s)\nValor por pessoa: Grátis\nVALOR TOTAL DA RESERVA: Grátis x%d (diarias) => Grátis\nSITUACAO DO PAGAMENTO: REALIZADO.\n",
						this.getContador(), this.usuario.toString(), dataInicioFormatada, a.formataHora(auditorio.getDataInicio()), dataFimFormatada, a.formataHora(auditorio.getDataFim()), this.numPessoas, estadia(this.getDataInicio(), this.getDataFim()), this.valorReserva());
			}
			return String.format("[%d] Reserva de AUDITORIO em favor de:\n%s\nDetalhes da reserva:\n- Periodo: %s %s ate %s %s\n- Qtde. de Convidados: %d pessoa(s)\nValor por pessoa: R$%.2f\nVALOR TOTAL DA RESERVA: R$%.2f x%d (diarias) => R$%.2f\nSITUACAO DO PAGAMENTO: PENDENTE.\n",
					this.getContador(), this.usuario.toString(), dataInicioFormatada, a.formataHora(auditorio.getDataInicio()), dataFimFormatada, a.formataHora(auditorio.getDataFim()), this.numPessoas, a.getValorPessoa(), a.getValorPessoa() * this.numPessoas, estadia(this.getDataInicio(), this.getDataFim()), this.valorReserva());
		} else {
				if (a.getValorPessoa() == 0) {
					return String.format("[%d] Reserva de AUDITORIO em favor de:\n%s\nDetalhes da reserva:\n- Periodo: %s %s ate %s %s\n- Qtde. de Convidados: %d pessoa(s)\nValor por pessoa: Grátis\nVALOR TOTAL DA RESERVA: Grátis x%d (diarias) => Grátis\nSITUACAO DO PAGAMENTO: REALIZADO.\n",
							this.getContador(), this.usuario.toString(), dataInicioFormatada, a.formataHora(auditorio.getDataInicio()), dataFimFormatada, a.formataHora(auditorio.getDataFim()), this.numPessoas, estadia(this.getDataInicio(), this.getDataFim()), this.valorReserva());
				}
				return String.format("[%d] Reserva de AUDITORIO em favor de:\n%s\nDetalhes da reserva:\n- Periodo: %s %s ate %s %s \n- Qtde. de Convidados: %d pessoa(s)\nValor por pessoa: R$%.2f\nVALOR TOTAL DA RESERVA: R$%.2f x%d (diarias) => R$%.2f\nSITUACAO DO PAGAMENTO: REALIZADO.\n",
						this.getContador(), this.usuario.toString(), dataInicioFormatada, a.formataHora(auditorio.getDataInicio()), dataFimFormatada, a.formataHora(auditorio.getDataFim()), this.numPessoas, a.getValorPessoa(), a.getValorPessoa() * this.numPessoas, estadia(this.getDataInicio(), this.getDataFim()), this.valorReserva());

		}
	}


}
