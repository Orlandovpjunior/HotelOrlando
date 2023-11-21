package br.edu.ufcg.p2lp2.hotelcalifornia.entities.pagamento;

import br.edu.ufcg.p2lp2.hotelcalifornia.entities.reservas.Reservas;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.Usuario;

public class PagamentoComPIX extends Pagamento {
	
	private String cpf;
	private String banco;
	
	public PagamentoComPIX(Forma forma, Reservas reserva, Usuario cliente, String nomeTitular, String cpf, String banco) {
		super(forma, reserva, cliente, nomeTitular);
		this.cpf = cpf;
		this.banco = banco;
		this.reserva.setStatusPago(true);
	}
	
	public String toString() {
		return this.reserva.toString() + "\n" + this.forma.toString();
	}

}