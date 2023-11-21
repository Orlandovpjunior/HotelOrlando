package br.edu.ufcg.p2lp2.hotelcalifornia.entities.pagamento;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.ControllerUsuarios;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.Usuario;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservasSessionController;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.reservas.Reservas;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

public class ControllerPagamento {
	
	private Map<Integer, Forma> formas;
	private int idAtual;
	private ControllerUsuarios usuarios;
	private ReservasSessionController reservas;
	private static ControllerPagamento instance;
	
	
	public ControllerPagamento() {
		this.formas = new HashMap<Integer, Forma>();
		this.idAtual = 1;
		this.usuarios = ControllerUsuarios.getInstance();
		this.reservas = ReservasSessionController.getInstance();
	}
	
	public static ControllerPagamento getInstance() {
		if (instance == null) {
			instance = new ControllerPagamento();
		}
		return instance;
	}
	
	public static void resetInstance(){
		instance = null;
	}
	
	public void init() {
		this.formas.clear();
		this.formas = new HashMap<Integer, Forma>();
		this.idAtual = 1;
		
	}
	
	public String disponibilizarFormaDePagamento(String idAutenticacao, String formaPagamento, double percentualDesconto) {
		if (!this.usuarios.verificaId(idAutenticacao)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}
		if (idAutenticacao.contains("ADM")) {
			if (formaPagamento.toLowerCase().contains("pix")) {
				Forma pagamento = new PIX(this.idAtual, percentualDesconto);
				if (this.verificaForma(pagamento)) {
					throw new HotelCaliforniaException("FORMA DE PAGAMENTO JA EXISTE");
				} else {
					this.formas.put(idAtual, pagamento);
					this.idAtual += 1;
					return pagamento.getInfo();
				}
			} else if (formaPagamento.toLowerCase().contains("din")) {
				Forma pagamento = new Dinheiro(this.idAtual, percentualDesconto);
				if (this.verificaForma(pagamento)) {
					throw new HotelCaliforniaException("FORMA DE PAGAMENTO JA EXISTE");
				} else {
					this.formas.put(idAtual, pagamento);
					this.idAtual += 1;
					return pagamento.getInfo();
				}
			} else if (formaPagamento.toLowerCase().contains("cart")) {
				Forma pagamento = new Cartao(this.idAtual, percentualDesconto);
				if (this.verificaForma(pagamento)) {
					throw new HotelCaliforniaException("FORMA DE PAGAMENTO JA EXISTE");
				} else {
					this.formas.put(idAtual, pagamento);
					this.idAtual += 1;
					return pagamento.getInfo();
				}
			} else {
				throw new HotelCaliforniaException("FORMA INVÁLIDA");
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA FORMA DE PAGAMENTO");
		}
		
	}
	
	public String alterarFormaDePagamento(String idAutenticacao, int idFormaPagamento, String formaPagamento, double percentualDesconto) {
		if (!this.formas.containsKey(idFormaPagamento)) {
			throw new IllegalArgumentException("FORMA DE PAGAMENTO NÃO EXISTE");
		}
		if (idAutenticacao.contains("ADM")) {
			if (formaPagamento.toLowerCase().contains("pix") || formaPagamento.toLowerCase().contains("cart") || formaPagamento.toLowerCase().contains("din")) {
				Forma forma = this.formas.get(idFormaPagamento);
				forma.setPercentualDesconto(percentualDesconto);
				return forma.getInfo();
			} else {
				throw new HotelCaliforniaException("TIPO DE PAGAMENTO NÃO EXISTE");
			}
		} else {
			throw new HotelCaliforniaException("USUÁRIO INVÁLIDO");
		}
	}
	/**
	 * Ainda sobre trabalho - Francisco
	 * @param idFormaPagamento
	 * @return
	 */
	public String exibirFormaPagamento(int idFormaPagamento) {
		if (this.formas.containsKey(idFormaPagamento)) {
			Forma pagamento = this.formas.get(idFormaPagamento);
			return pagamento.getInfo();
		} else {
			throw new HotelCaliforniaException("FORMA DE PAGAMENTO NÃO EXISTE");
		}
	}
	
	public String[] listarFormasPagamentos() {
		String[] pagamentos = new String[this.formas.size()];
		int i = 0;
		for (int n: this.formas.keySet()) {
			pagamentos[i] = this.formas.get(n).getInfo();
			i += 1;
		}
		return pagamentos;
		
	}
	
	private boolean verificaForma(Forma forma) {
		for (Integer i: this.formas.keySet()) {
			if (forma.equals(this.formas.get(i))) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public String  pagarDinheiro(String idCliente, long idReserva, String nomeTitular) {
		Usuario usuario = this.usuarios.getUsuario(idCliente);
		Reservas reserva = this.reservas.getReserva(idReserva);
		if (!idCliente.equals(reserva.getIdCliente())) {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA PAGAR A SUA RESERVA");
		} else if (reserva.isStatusPago()) {
			throw new HotelCaliforniaException("RESERVA JA FOI PAGA");
		} else {
			Forma forma = new Dinheiro(this.idAtual, 0.10, reserva.valorReserva());
			Pagamento pagamento = new PagamentoComDinheiro(forma, reserva, usuario, nomeTitular);
			return pagamento.toString();
		}
	}
	
	public String pagarCartao(String idCliente, long idReserva, String nomeTitular, String numCartao, String validade, String codSeguranca, int qtdParcelas) {
		Usuario usuario = this.usuarios.getUsuario(idCliente);
		Reservas reserva = this.reservas.getReserva(idReserva);
		if (!idCliente.equals(reserva.getIdCliente())) {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA PAGAR A SUA RESERVA");
		} else if (reserva.isStatusPago()) {
			throw new HotelCaliforniaException("RESERVA JA FOI PAGA");
		} else {
			Forma forma = new Cartao(this.idAtual, qtdParcelas, reserva.valorReserva());
			Pagamento pagamento = new PagamentoComCartao(forma, reserva, usuario, nomeTitular, numCartao, validade, codSeguranca);
			return pagamento.toString();
		}
	}
	
	public String pagarPIX(String idCliente, long idReserva, String nomeTitular, String cpf, String banco) {
		Usuario usuario = this.usuarios.getUsuario(idCliente);
		Reservas reserva = this.reservas.getReserva(idReserva);
		if (!idCliente.equals(reserva.getIdCliente())) {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA PAGAR A SUA RESERVA");
		} else if (reserva.isStatusPago()) {
			throw new HotelCaliforniaException("RESERVA JA FOI PAGA");
		} else {
			Forma forma = new PIX(this.idAtual, 0.05, reserva.valorReserva());
			Pagamento pagamento = new PagamentoComPIX(forma, reserva, usuario, nomeTitular, cpf, banco);
			return pagamento.toString();
		}
	}

}