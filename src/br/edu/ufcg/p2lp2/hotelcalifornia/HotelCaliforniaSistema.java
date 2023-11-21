package br.edu.ufcg.p2lp2.hotelcalifornia;

import java.time.LocalDateTime;
import java.time.LocalTime;

import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservasSessionController;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.pagamento.ControllerPagamento;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.ControllerUsuarios;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.areacomum.ControllerAreas;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.quartos.ControllerQuartos;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.refeicao.ControllerRefeicao;


public class HotelCaliforniaSistema {
	private ControllerQuartos controllerQuartos;
	private ReservasSessionController controllerReserva;
	private ControllerUsuarios controllerUsuarios;
	private ControllerRefeicao controllerRefeicao;
	private ControllerPagamento controllerPagamento;
	private ControllerAreas controllerAreas;


	public HotelCaliforniaSistema() {
		this.controllerQuartos = ControllerQuartos.getInstance();
		this.controllerQuartos.init();
		this.controllerUsuarios = ControllerUsuarios.getInstance();
		this.controllerUsuarios.init();
		this.controllerRefeicao = ControllerRefeicao.getInstance();
		this.controllerRefeicao.init();
		this.controllerReserva = ReservasSessionController.getInstance();
		this.controllerReserva.init();
		this.controllerAreas = ControllerAreas.getInstance();
		this.controllerAreas.init();
		this.controllerPagamento = ControllerPagamento.getInstance();
		this.controllerPagamento.init();
	}

	public String cadastrarUsuario(String idAutenticacao, String nome, String tipoUsuario, Long documento) {
		return this.controllerUsuarios.cadastrarUsuario(idAutenticacao, nome, tipoUsuario, documento);
	}

	public String exibirUsuario(String idUsuario) {
		return this.controllerUsuarios.exibirUsuario(idUsuario);
	}

	public String[] listarUsuarios() {
		return this.controllerUsuarios.listarUsuarios();
	}

	public String atualizarUsuario(String idAutenticacao, String idUsuario, String novoTipoUsuario) {
		return this.controllerUsuarios.atualizarUsuario(idAutenticacao, idUsuario, novoTipoUsuario);
	}

	public String disponibilizarQuartoSingle(String idAutenticacao,int idQuartoNum, double precoBase,double precoPorPessoa) {
		return this.controllerQuartos.disponibilizarQuartoSingle(idAutenticacao, idQuartoNum, precoBase,precoPorPessoa);
	}

	public String disponibilizarQuartoDouble(String idAutenticacao,int idQuartoNum, double precoBase, double precoPorPessoa, String[] pedidos) {
		return this.controllerQuartos.disponibilizarQuartoDouble(idAutenticacao, idQuartoNum, precoBase,precoPorPessoa, pedidos);
	}

	public String disponibilizarQuartoFamily(String idAutenticacao,int idQuartoNum,double precoBase, double precoPorPessoa, String[] pedidos, int qtdMaxPessoas) {
		return this.controllerQuartos.disponibilizarQuartoFamily(idAutenticacao, idQuartoNum, precoBase,precoPorPessoa, pedidos, qtdMaxPessoas);
	}

	public String exibirQuarto(int idQuartoNum) {
		return this.controllerQuartos.exibirQuarto(idQuartoNum);
	}

	public String[] listarQuartos() {
		return this.controllerQuartos.listarQuartos();
	}

	public String reservarQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,LocalDateTime dataFim, String[] idRefeicoes) {
		return this.controllerReserva.reservarQuartoSingle(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes);
	}

	public String reservarQuartoDouble(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim,String[] idRefeicoes, String[] pedidos) {
		return this.controllerReserva.reservarQuartoDouble(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes, pedidos);
	}

	public String reservarQuartoFamily(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim,String[] idRefeicoes, String[] pedidos, int numPessoas) {
		return this.controllerReserva.reservarQuartoFamily(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes, pedidos, numPessoas);
	}

	public String exibirReserva(String idAutenticacao, long idReserva) {
		return this.controllerReserva.exibirReserva(idAutenticacao, idReserva);
	}

	public String[] listarReservasAtivasDoCliente(String idAutenticacao, String idCliente) {
		return this.controllerReserva.listarReservasAtivasDoCliente(idAutenticacao, idCliente);
	}

	public String[] listarReservasAtivasDoClientePorTipo(String idAutenticacao, String idCliente, String tipo) {
		return this.controllerReserva.listarReservasAtivasDoClientePorTipo(idAutenticacao, idCliente, tipo);
	}

	public String[] listarReservasAtivasPorTipo(String idAutenticacao,String tipo) {
		return this.controllerReserva.listarReservasAtivasPorTipo(idAutenticacao, tipo);
	}

	public String[] listarReservasAtivas(String idAutenticacao) {
		return this.controllerReserva.listarReservasAtivas(idAutenticacao);
	}

	public String[] listarReservasTodas(String idAutenticacao) {
		return this.controllerReserva.listarReservasAtivas(idAutenticacao);
	}

	public String cancelarReserva(String idCliente, String idReserva) {
		return this.controllerReserva.cancelarReserva(idCliente, Long.parseLong(idReserva));
	}

	public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo, LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel){
		return this.controllerRefeicao.disponibilizarRefeicao(idAutenticacao, tipoRefeicao, titulo, horarioInicio, horarioFinal, valor, disponivel);
	}

	public String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal, double valorPorPessoa, boolean disponivel) {
		return  this.controllerRefeicao.alterarRefeicao(idRefeicao, horarioInicio, horarioFinal, valorPorPessoa, disponivel);
	}

	public String exibirRefeicao(long idRefeicao) {
		return this.controllerRefeicao.exibirRefeicao(idRefeicao);
	}

	public String[] listarRefeicoes(){
		return this.controllerRefeicao.listarRefeicoes();
	}

	public String disponibilizarFormaDePagamento(String idAutenticacao, String formaPagamento, double percentualDesconto) {
		return this.controllerPagamento.disponibilizarFormaDePagamento(idAutenticacao, formaPagamento, percentualDesconto);
	}

	public String alterarFormaDePagamento(String idAutenticacao, int idFormaPagamento, String formaPagamento, double percentualDesconto) {
		return this.controllerPagamento.alterarFormaDePagamento(idAutenticacao, idFormaPagamento, formaPagamento, percentualDesconto);
	}

	public String exibirFormaPagamento(int idFormaPagamento) {
		return this.controllerPagamento.exibirFormaPagamento(idFormaPagamento);
	}

	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDateTime dataInicio,LocalDateTime dataFim, int numPessoas, String idRefeicoes) {
		return this.controllerReserva.reservarRestaurante(idAutenticacao, idCliente, dataInicio, dataFim, numPessoas, idRefeicoes, controllerRefeicao);
	}

	public String disponibilizarAreaComum(String idAutenticacao, String tipoAreaComum, String titulo, LocalTime horarioInicio, LocalTime horarioFim, double valorPessoa, boolean disponivel, int qtdMaxPessoas) {
		return this.controllerAreas.disponibilizarAreaComum(idAutenticacao, tipoAreaComum, titulo, horarioInicio, horarioFim, valorPessoa, disponivel, qtdMaxPessoas);
	}

	public String exibirAreaComum(Long idAreaComum) {
		return this.controllerAreas.exibirAreaComum(idAreaComum);
	}

	public String alterarAreaComum(String idAutenticacao, long idAreaComum, LocalTime novoHorarioInicio, LocalTime novoHorarioFim, double novoPreco, int capacidadeMax,boolean disponivel) {
		return this.controllerAreas.alterarAreaComum(idAutenticacao, idAreaComum, novoHorarioInicio, novoHorarioFim, novoPreco, capacidadeMax, disponivel);
	}

	public String[] listarAreasComuns() {
		return this.controllerAreas.listarAreasComuns();
	}

	public String reservarAuditorio(String idAutenticacao, String idCliente, long idAuditorio, LocalDateTime dataInicio,LocalDateTime dataFim, int qtdMaxPessoas) {
		return this.controllerReserva.reservarAuditorio(idAutenticacao, idCliente, idAuditorio, dataInicio, dataFim, qtdMaxPessoas);
	}
	
	public String pagarReservaComDinheiro(String idCliente, long idReserva, String nomeTitular) {
		return this.controllerPagamento.pagarDinheiro(idCliente, idReserva, nomeTitular);
	}
	
	public String pagarReservaComCartao(String idCliente, long idReserva, String nomeTitular, String numCartao, String validade, String codigoDeSeguranca, int qtdeParcelas) {
		return this.controllerPagamento.pagarCartao(idCliente, idReserva, nomeTitular, numCartao, validade, codigoDeSeguranca, qtdeParcelas);
	}
	
	public String pagarReservaComPix(String idCliente, long idReserva, String nomeTitular, String cpf, String banco) {
		return this.controllerPagamento.pagarPIX(idCliente, idReserva, nomeTitular, cpf, banco);
	}
	
	public String[] listarFormasPagamentos() {
		return this.controllerPagamento.listarFormasPagamentos();
	}

}