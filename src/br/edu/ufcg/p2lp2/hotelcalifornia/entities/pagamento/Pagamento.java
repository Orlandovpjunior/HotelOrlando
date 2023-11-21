package br.edu.ufcg.p2lp2.hotelcalifornia.entities.pagamento;

import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.Usuario;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.reservas.Reservas;

public abstract class Pagamento {
	
	protected boolean situacaoPagamento;
	protected Forma forma;
	protected Reservas reserva;
	protected Usuario cliente;
	protected String nomeTitular;
	
	public Pagamento(Forma forma, Reservas reserva, Usuario cliente, String nomeTitular) {
		this.situacaoPagamento = false;
		this.forma = forma;
		this.reserva = reserva;
		this.nomeTitular = nomeTitular;
	}
	
}