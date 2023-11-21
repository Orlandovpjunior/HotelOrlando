package entities.pagamento;

import entities.reservas.Reservas;
import entities.usuarios.Usuario;

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