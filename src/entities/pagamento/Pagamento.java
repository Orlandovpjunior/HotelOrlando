package entities.pagamento;

import entities.reservas.Reservas;
import entities.usuarios.Usuario;

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