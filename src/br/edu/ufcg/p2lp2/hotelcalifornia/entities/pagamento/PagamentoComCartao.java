package br.edu.ufcg.p2lp2.hotelcalifornia.entities.pagamento;

import br.edu.ufcg.p2lp2.hotelcalifornia.entities.reservas.Reservas;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.Usuario;

public class PagamentoComCartao extends Pagamento {
	
	private String numCartao;
	private String validade;
	private String codSeguranca;
	
	public PagamentoComCartao(Forma forma, Reservas reserva, Usuario cliente, String nomeTitular, String numCartao, String validade, String codSeguranca) {
		super(forma, reserva, cliente, nomeTitular);
		this.numCartao = numCartao;
		this.validade = validade;
		this.codSeguranca = codSeguranca;
		this.reserva.setStatusPago(true);
	}
	
	public String toString() {
		return this.reserva.toString() + "\n" + this.forma.toString();
	}

}