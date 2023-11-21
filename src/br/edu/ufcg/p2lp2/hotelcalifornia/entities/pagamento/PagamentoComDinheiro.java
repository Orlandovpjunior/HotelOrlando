package br.edu.ufcg.p2lp2.hotelcalifornia.entities.pagamento;

import br.edu.ufcg.p2lp2.hotelcalifornia.entities.reservas.Reservas;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.Usuario;

public class PagamentoComDinheiro extends Pagamento {
	
	
	public PagamentoComDinheiro(Forma forma, Reservas reserva, Usuario cliente, String nomeTitular) {
		super(forma, reserva, cliente, nomeTitular);
		this.reserva.setStatusPago(true);
	}
	
	public String toString() {
		return this.reserva.toString() + "\n" + this.forma.toString();
	}

}