package br.edu.ufcg.p2lp2.hotelcalifornia.entities.reservas;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.Usuario;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.quartos.Quartos;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.refeicao.Refeicao;

public class ReservaFamily extends Reservas{
	private Quartos quarto;
	private Usuario usuario;
	private Refeicao[] refeicoes;
	private int numQuarto;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	public ReservaFamily(String idCliente, int numQuarto,LocalDateTime dataInicio, LocalDateTime dataFim, Refeicao[] refeicoes, Quartos quarto, Usuario usuario, int numPessoas,String tipo) {
		super(idCliente, tipo);
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.quarto = quarto;
		this.usuario = usuario;
		this.numPessoas = numPessoas;
		this.refeicoes = refeicoes;
		this.numQuarto = numQuarto;
		this.tipo = "QUARTO";
	}
	
	public String getTipo() {
		return tipo;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	
	public boolean isStatusPago() {
		return this.statusPago;
	}

	public void setStatusPago(boolean statusPago) {
		this.statusPago = statusPago;
	}
	
	
	public String exibirQuarto() {
		StringBuilder out = new StringBuilder();
		String q = this.quarto.toString();
		out.append(q.toString());
		return out.toString();
	}
	
	public int estadia(LocalDateTime dataInicio, LocalDateTime dataFim) {
		Duration duration = Duration.between(dataInicio, dataFim);
        long horas = duration.toHours();
        int qtdeEstadia = (int) Math.ceil(horas/22);
        if(qtdeEstadia == 0) {
        	qtdeEstadia += 1;
        }
        return qtdeEstadia;
	} 

	public double valorDiaria() {
		Quartos q = this.quarto;
		return q.getPrecoBase() + (q.getPrecoPorPessoa() * 5);
	}
	
	public double valorRefeicao(Refeicao[] idRefeicoes) {
		double valorRefeicao = 0;
		for (Refeicao refeicao : idRefeicoes) {
			valorRefeicao += refeicao.getValor();
		}
		return valorRefeicao;
	}
	
	public double valorReserva() {
		Quartos q = this.quarto;
		LocalDateTime datainicio = this.dataInicio;
		LocalDateTime datafim = this.dataFim;
		Refeicao[] idrefeicoes = this.refeicoes;
		
		double valorRefeicao = this.valorRefeicao(idrefeicoes);
		
		if(q.isReservado() == false) {
			throw new IllegalArgumentException("QUARTO NÃO ESTÁ RESERVADO!");
		}
		double valor = this.estadia(dataInicio, dataFim) * (q.getPrecoBase() + 5 * q.getPrecoPorPessoa()) + this.estadia(datainicio, datafim) * q.getQtdMaxPessoas() * valorRefeicao;
		return valor;
	}
	
	@Override
	public String getIdCliente() {
		return this.idCliente;	
	}
	
	@Override
	public Quartos getQuarto() {
		return this.quarto;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public int getNumQuarto() {
		return this.numQuarto;
	}

	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}

	public LocalDateTime getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public int getNumPessoas() {
		return this.numPessoas;
	}

	public void setNumPessoas(int numPessoas) {
		this.numPessoas = numPessoas;
	}
	
	public String formataValor(double valor) {
		DecimalFormat decimalFormat;

		if (valor > 1000.00) {
			decimalFormat = new DecimalFormat("#,##0.00");
		} else {
			decimalFormat = new DecimalFormat("0.00");
		}
		return decimalFormat.format(valor);
	}

	@Override
	public String toString() {
		LocalTime horaEntrada = LocalTime.of(14, 0, 0);
		LocalTime horaSaida = LocalTime.of(12, 0, 0);
		LocalDateTime dataInicioFixa = getDataInicio().with(horaEntrada);
		LocalDateTime dataFimFixa = getDataFim().with(horaSaida);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dataInicioFormatada = dataInicioFixa.format(formatter);
		String dataFimFormatada = dataFimFixa.format(formatter);
		
	    if(this.isStatusPago() == false) {
	    	return String.format("[%d] Reserva de quarto em favor de: \n%s\nDetalhes da instalação:\n%s\nDetalhes da reserva:\nPeríodo: %s ate %s\nNo. de Hospedes: %d pessoa(s)\nRefeições incluídas:%s\nVALOR TOTAL DA RESERVA: R$%.2f x%d (diarias) => R$%s\nSITUACAO DO PAGAMENTO: PENDENTE.\n", 
	    			this.getContador(),this.usuario.toString(), exibirQuarto(), dataInicioFormatada, dataFimFormatada, this.quarto.getQtdMaxPessoas(), Arrays.toString(this.refeicoes), valorDiaria() + (valorRefeicao(refeicoes) * this.quarto.getQtdMaxPessoas()), estadia(getDataInicio(), getDataFim()), this.formataValor(this.valorReserva()));
	    }else {
	    	return String.format("[%d] Reserva de quarto em favor de: \n%s\nDetalhes da instalação:\n%s\nDetalhes da reserva:\nPeríodo: %s ate %s\nNo. de Hospedes: %d pessoa(s)\nRefeições incluídas:%s\nVALOR TOTAL DA RESERVA: R$%.2f x%d (diarias) => R$%s\nSITUACAO DO PAGAMENTO: REALIZADO.\n", 
			        this.getContador(),this.usuario.toString(), exibirQuarto(), dataInicioFormatada, dataFimFormatada, this.quarto.getQtdMaxPessoas(),Arrays.toString(this.refeicoes),valorDiaria() + (valorRefeicao(refeicoes) * this.quarto.getQtdMaxPessoas()), estadia(getDataInicio(), getDataFim()), this.formataValor(this.valorReserva()));
	    }
	    
	}

}
