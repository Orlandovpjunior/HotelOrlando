package br.edu.ufcg.p2lp2.hotelcalifornia.entities.reservas;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.refeicao.Refeicao;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.Usuario;

public class ReservaRestaurante extends Reservas{
	
	private Usuario usuario;
	private Refeicao refeicoes;
	private int numPessoas;
	
	public ReservaRestaurante(String idCliente, LocalDateTime dataInicio, LocalDateTime dataFim,Refeicao refeicoes,int numPessoas, Usuario usuario, String tipo) {
		super(idCliente,tipo);
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.numPessoas = numPessoas;
		this.usuario = usuario;
		this.refeicoes = refeicoes;
		this.tipo = "RESTAURANTE";
	}
	

	public int estadia(LocalDateTime dataInicio, LocalDateTime dataFim) {
		int qtdeEstadia = qtdeDiarias(dataInicio, dataFim);
    	return qtdeEstadia + 1;
	}

	@Override
	public double valorReserva() {
		LocalDateTime datainicio = this.dataInicio;
		LocalDateTime datafim = this.dataFim;
		Refeicao idrefeicoes = this.refeicoes;
		double valorRefeicao = this.valorRefeicao(idrefeicoes);
		return this.numPessoas * valorRefeicao * this.estadia(datainicio, datafim);
	}

	public double valorRefeicao(Refeicao idRefeicoes) {
		Refeicao r = refeicoes;
		return r.getValor();
	}


	@Override
	public String toString() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    String dataInicioFormatada = this.dataInicio.format(formatter);
	    String dataFimFormatada = this.dataFim.format(formatter);
		DecimalFormat df = new DecimalFormat("#,###.00");
		String valorRefeicao = df.format(valorRefeicao(refeicoes) * this.numPessoas);
		String valorTotal = df.format(valorReserva());
		if(!this.isStatusPago()) {
			return String.format("[%d] Reserva de RESTAURANTE em favor de:\n%s\nDetalhes da reserva:\n- Periodo: %s %s:00 ate %s %s:00\n- Qtde. de Convidados: %d pessoa(s)\nRefeicao  incluida:%s\nVALOR TOTAL DA RESERVA: R$%s x%d (diarias) => R$%s\nSITUACAO DO PAGAMENTO: PENDENTE.\n",
					this.getContador(),this.usuario.toString(), dataInicioFormatada, this.refeicoes.formataHora(this.refeicoes.getHorarioInicio()), dataFimFormatada, this.refeicoes.formataHora(this.refeicoes.getHorarioFim()), this.numPessoas, refeicoes, valorRefeicao, estadia(this.dataInicio, this.dataFim), valorTotal);
	    }else {
	    	return String.format("[%d] Reserva de RESTAURANTE em favor de:\n%s\nDetalhes da reserva:\n- Periodo: %s %s:00 ate %s %s:00\n- Qtde. de Convidados: %d pessoa(s)\nRefeicao  incluida:%s\nVALOR TOTAL DA RESERVA: R$%s x%d (diarias) => R$%s\nSITUACAO DO PAGAMENTO: REALIZADO.\n",
			        this.getContador(),this.usuario.toString(), dataInicioFormatada, this.refeicoes.formataHora(this.refeicoes.getHorarioInicio()), dataFimFormatada, this.refeicoes.formataHora(this.refeicoes.getHorarioFim()), this.numPessoas, refeicoes, valorRefeicao, estadia(this.dataInicio, this.dataFim), valorTotal);
	    }
	    
	}

}