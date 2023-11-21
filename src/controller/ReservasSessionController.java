package controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.areacomum.AreaComum;
import entities.quartos.Quartos;
import entities.refeicao.Refeicao;
import entities.reservas.*;
import entities.usuarios.Usuario;
import exception.HotelCaliforniaException;

public class ReservasSessionController {
	private HashMap<Long, Reservas> reservas;
	private long contador = 1;
	private static ReservasSessionController instance;
	private ControllerUsuarios controllerusuarios;
	private ControllerQuartos controllerQuartos;
	private ControllerRefeicao controllerRefeicao;
	private ControllerAreas controllerAreas;

	public ReservasSessionController() {
		this.reservas = new HashMap<Long, Reservas>();
		this.controllerusuarios = ControllerUsuarios.getInstance();
		this.controllerQuartos = ControllerQuartos.getInstance();
		this.controllerRefeicao = ControllerRefeicao.getInstance();
		this.controllerAreas = ControllerAreas.getInstance();
	}

	public static ReservasSessionController getInstance() {
        if (instance == null) {
            instance = new ReservasSessionController();
        }
        return instance;
    }

	public static void resertInstance() {
		instance = null;
	}

	public void init() {
		this.reservas.clear();
		this.reservas = new HashMap<Long, Reservas>();
		this.controllerusuarios = ControllerUsuarios.getInstance();
		this.controllerQuartos = ControllerQuartos.getInstance();
		this.controllerRefeicao = ControllerRefeicao.getInstance();
		this.controllerAreas = ControllerAreas.getInstance();
	}


	private boolean isReservaSobreposta(LocalDateTime inicio1, LocalDateTime fim1, LocalDateTime inicio2,LocalDateTime fim2) {
		return !((fim1.isBefore(inicio2) || fim1.isEqual(inicio2))|| (fim2.isBefore(inicio1) || fim2.isEqual(inicio1)));
	}

	private boolean periodoMinino(LocalDateTime dataInicio) {
		LocalDateTime dataAtual = LocalDateTime.now();
		if(dataInicio.minusDays(1).isBefore(dataAtual)) {
			return false;
		}
		return true;
	}

	public String reservarQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] idRefeicoes) {
		StringBuilder out = new StringBuilder();
		Quartos q = controllerQuartos.getQuartos().get(numQuarto);
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		Usuario userClient = controllerusuarios.getUsuarios().get(idCliente);
		Refeicao[] arrRefeicao = controllerRefeicao.getRefeicoesPorId(idRefeicoes);

		if (q == null) {
			throw new HotelCaliforniaException("QUARTO NÃO EXISTE!");
		}
		
		if (u == null) {
			throw new HotelCaliforniaException("USUÁRIO NÃO EXISTE!");
		}
		
		if (userClient == null) {
			throw new HotelCaliforniaException("CLIENTE NÃO EXISTE!");
		}

		if(periodoMinino(dataInicio) == false) {
			throw new HotelCaliforniaException("NECESSARIO ANTECEDENCIA MINIMA DE 01 (UM) DIA");
		}

		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);
		if (matcher.find() && dataInicio.isBefore(dataFim) && dataInicio.toLocalTime().isAfter(LocalTime.of(13, 59))
				&& dataFim.toLocalTime().isBefore(LocalTime.of(12, 1))) {
			if (q.getDataInicio() == null || !isReservaSobreposta(q.getDataInicio(), q.getDataFim(), dataInicio, dataFim)) {
				Reservas novaReserva = new ReservaSingle(idCliente, numQuarto, dataInicio, dataFim, arrRefeicao, q, userClient, "QUARTO");
				reservas.put(this.contador++, novaReserva);
				q.setDates(dataInicio, dataFim);
				q.setReservado(true);
				out.append(novaReserva.toString());
			} else {
				throw new HotelCaliforniaException("JA EXISTE RESERVA PARA ESTA DATA");
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
		}
		return out.toString();
	}


	public String reservarQuartoDouble(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,LocalDateTime dataFim, String[] idRefeicoes, String [] pedidos) {
		StringBuilder out = new StringBuilder();
		Quartos q = controllerQuartos.getQuartos().get(numQuarto);
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		Usuario userClient = controllerusuarios.getUsuarios().get(idCliente);
		Refeicao[] arrRefeicao = controllerRefeicao.getRefeicoesPorId(idRefeicoes);

		if (q == null) {
			throw new HotelCaliforniaException("QUARTO NÃO EXISTE!");
		}
		
		if (u == null) {
			throw new HotelCaliforniaException("USUÁRIO NÃO EXISTE!");
		}
		
		if (userClient == null) {
			throw new HotelCaliforniaException("CLIENTE NÃO EXISTE!");
		}

		if(periodoMinino(dataInicio) == false) {
			throw new HotelCaliforniaException("NECESSARIO ANTECEDENCIA MINIMA DE 01 (UM) DIA");
		}

		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);

		if (matcher.find() && dataInicio.isBefore(dataFim) && dataInicio.toLocalTime().isAfter(LocalTime.of(13, 59))
				&& dataFim.toLocalTime().isBefore(LocalTime.of(12, 1))) {
			if (q.getDataInicio() == null || !isReservaSobreposta(q.getDataInicio(), q.getDataFim(), dataInicio, dataFim)) {
				Reservas novaReserva = new ReservaDouble(idCliente, numQuarto, dataInicio, dataFim, arrRefeicao, q, userClient,"QUARTO");
				reservas.put(this.contador++, novaReserva);
				q.setDates(dataInicio, dataFim);
				q.setReservado(true);
				q.setPedidos(pedidos);
				out.append(novaReserva.toString());
			} else {
				throw new HotelCaliforniaException("JA EXISTE RESERVA PARA ESTA DATA");
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
		}
		return out.toString();
	}

	public String reservarQuartoFamily(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos, int numPessoas) {
		StringBuilder out = new StringBuilder();

		Quartos q = controllerQuartos.getQuartos().get(numQuarto);
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		Usuario u1 = controllerusuarios.getUsuarios().get(idCliente);
		Refeicao[] arrRefeicao = controllerRefeicao.getRefeicoesPorId(idRefeicoes);

		if (q == null) {
			throw new HotelCaliforniaException("QUARTO NÃO EXISTE!");
		}
		if (u == null) {
			throw new HotelCaliforniaException("USUÁRIO NÃO EXISTE!");
		} else if (u1 == null) {
			throw new HotelCaliforniaException("CLIENTE NÃO EXISTE!");
		}

		if(numPessoas > 5) {
			throw new HotelCaliforniaException("CAPACIDADE EXCEDIDA");
		}

		if(periodoMinino(dataInicio) == false) {
			throw new HotelCaliforniaException("NECESSARIO ANTECEDENCIA MINIMA DE 01 (UM) DIA");
		}

		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);

		if (matcher.find() && dataInicio.isBefore(dataFim) && dataInicio.toLocalTime().isAfter(LocalTime.of(13, 59))
				&& dataFim.toLocalTime().isBefore(LocalTime.of(12, 1))) {
			if (q.getDataInicio() == null || !isReservaSobreposta(q.getDataInicio(), q.getDataFim(), dataInicio, dataFim)) {
				Reservas novaReserva = new ReservaFamily(idCliente, numQuarto, dataInicio, dataFim, arrRefeicao, q, u1, numPessoas,"QUARTO");
				reservas.put(this.contador++, novaReserva);
				q.setDates(dataInicio, dataFim);
				q.setReservado(true);
				q.setPedidos(pedidos);
				q.qtdMaxPessoas(numPessoas);
				out.append(novaReserva.toString());
			} else {
				throw new HotelCaliforniaException("JA EXISTE RESERVA PARA ESTA DATA");
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
		}
		return out.toString();
	}


	public String exibirReserva(String idAutenticacao, long idReserva) {
		StringBuilder out = new StringBuilder();
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		if (u == null) {
			throw new HotelCaliforniaException("USUÁRIO NÃO EXISTE!");
		}
		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER|CLI)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);

		if (matcher.find()){
			Reservas r = this.reservas.get(idReserva);
			if(r == null) {
				throw new HotelCaliforniaException("RESERVA NAO ENCONTRADA");
			}
			out.append(r.toString());
		}else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO EXIBIR/LISTAR RESERVA(S) DO CLIENTE");
		}
		return out.toString();
	}

	public String[] listarReservasAtivasDoCliente(String idAutenticacao, String idCliente) {
		ArrayList <String> arrReservas = new ArrayList<>();
		LocalDateTime dataAtual = LocalDateTime.now();
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		if (u == null) {
			throw new HotelCaliforniaException("USUÁRIO NÃO EXISTE!");
		}
		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER|CLI)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);

		if (matcher.find()) {
			for (Map.Entry<Long, Reservas> entry : reservas.entrySet()) {
				Long id = entry.getKey();
				Reservas r = this.reservas.get(id);
				if(r.getIdCliente().equals(idCliente) && (r.getDataFim().isAfter(dataAtual))) {
					arrReservas.add(r.toString());
				}else {
					throw new HotelCaliforniaException("RESERVA NAO ENCONTRADA");
				}
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO EXIBIR/LISTAR RESERVA(S) DO CLIENTE");
		}
		String[] array = arrReservas.toArray(new String[arrReservas.size()]);
		if(array.length == 0) {
			throw new HotelCaliforniaException("RESERVA NAO ENCONTRADA");
		}
		return array;
	}

	public String[] listarReservasAtivasDoClientePorTipo(String idAutenticacao, String idCliente, String tipo) {
		ArrayList <String> arrReservas = new ArrayList<>();
		LocalDateTime dataAtual = LocalDateTime.now();
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		if (u == null) {
			throw new HotelCaliforniaException("USUÁRIO NÃO EXISTE!");
		}
		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER|CLI)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);

		if (matcher.find()) {
			for (Map.Entry<Long, Reservas> entry : reservas.entrySet()) {
				Long key = entry.getKey();
				Reservas r = this.reservas.get(key);
				if(r.getIdCliente().equals(idCliente) && (r.getTipo().equals(tipo)) && (r.getDataFim().isAfter(dataAtual))) {
					arrReservas.add(r.toString());
				}
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO EXIBIR/LISTAR RESERVA(S) DO CLIENTE");
		}

		String[] array = arrReservas.toArray(new String[arrReservas.size()]);
		if(array.length == 0) {
			throw new HotelCaliforniaException("RESERVA NAO ENCONTRADA");
		}
		return array;
	}

	public String[] listarReservasAtivasPorTipo(String idAutenticacao, String tipo) {
		ArrayList <String> arrReservas = new ArrayList<>();
		LocalDateTime dataAtual = LocalDateTime.now();
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		if (u == null) {
			throw new HotelCaliforniaException("USUÁRIO NÃO EXISTE!");
		}
		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER|CLI)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);

		if (matcher.find()) {
			for (Map.Entry<Long, Reservas> entry : reservas.entrySet()) {
				Reservas val = entry.getValue();
				if(val.getTipo().equals(tipo) && (val.getDataFim().isAfter(dataAtual))) {
					arrReservas.add(val.toString());
				}
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO EXIBIR/LISTAR RESERVA(S) DO CLIENTE");
		}
		String[] array = arrReservas.toArray(new String[arrReservas.size()]);
		if(array.length == 0) {
			throw new HotelCaliforniaException("RESERVA NAO ENCONTRADA");
		}
		return array;
	}

	public String[] listarReservasAtivas(String idAutenticacao) {
		ArrayList <String> arrReservas = new ArrayList<>();
		LocalDateTime dataAtual = LocalDateTime.now();
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		if (u == null) {
			throw new HotelCaliforniaException("USUÁRIO NÃO EXISTE!");
		}
		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER|CLI)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);

		if (matcher.find()) {
			for (Map.Entry<Long, Reservas> entry : reservas.entrySet()) {
				Reservas val = entry.getValue();

				if(val.getDataFim().isAfter(dataAtual)) {
					arrReservas.add(val.toString());
				}
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO EXIBIR/LISTAR RESERVA(S) DO CLIENTE");
		}
		String[] array = arrReservas.toArray(new String[arrReservas.size()]);
		if(array.length == 0) {
			throw new HotelCaliforniaException("RESERVA NAO ENCONTRADA");
		}
		return array;
	}

	public String[] listarReservasTodas(String idAutenticacao) {
		ArrayList <String> arrReservas = new ArrayList<>();
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		if (u == null) {
			throw new HotelCaliforniaException("USUÁRIO NÃO EXISTE!");
		}
		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);

		if (matcher.find()) {
			for (Map.Entry<Long, Reservas> entry : reservas.entrySet()) {
				Reservas val = entry.getValue();
				arrReservas.add(val.toString());
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO EXIBIR/LISTAR RESERVA(S) DO CLIENTE");
		}
		String[] array = arrReservas.toArray(new String[arrReservas.size()]);
		if(array.length == 0) {
			throw new HotelCaliforniaException("RESERVA NAO ENCONTRADA");
		}
		return array;
	}

	public String cancelarReserva(String idCliente, long idReserva) {
		StringBuilder out = new StringBuilder();
		Reservas r = this.reservas.get(idReserva);
		if(r.getIdCliente().equals(idCliente)) {
			LocalDateTime dataAtual = LocalDateTime.now();
			LocalDateTime dataReservada = r.getDataInicio();
			if(dataReservada.minusDays(1).isAfter(dataAtual)) {
				this.reservas.remove(idReserva);
				out.append("[CANCELADA]");
			}else {
				throw new HotelCaliforniaException("DATA INVÁLIDA!");
			}
			return out.toString();
		}else {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA CANCELAR A SUA RESERVA");
		}

	}

	private boolean existeReservaRestaurante(LocalDateTime dataInicio, LocalDateTime dataFim){
		for (Reservas r : this.reservas.values()) {
			if(r instanceof ReservaRestaurante){
				LocalDateTime reservaInicio = ((ReservaRestaurante)r).getDataInicio();
				LocalDateTime reservaFinal = ((ReservaRestaurante)r).getDataFim();
				if(dataInicio.isBefore(reservaFinal) && dataFim.isAfter(reservaInicio)) return true;
			}
		}
		return false;
	}

	private boolean existeReservaAuditorio(LocalDateTime dataInicio, LocalDateTime dataFim){
		for (Reservas a : this.reservas.values()) {
			if(a instanceof ReservaAuditorio){
				LocalDateTime reservaInicio = ((ReservaAuditorio)a).getDataInicio();
				LocalDateTime reservaFinal = ((ReservaAuditorio)a).getDataFim();
				if(dataInicio.isBefore(reservaFinal) && dataFim.isAfter(reservaInicio)) return true;
			}
		}
		return false;
	}

	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDateTime dataInicio,LocalDateTime dataFim, int numPessoas, String idRefeicoes, ControllerRefeicao cr) {
		if(numPessoas > 50) throw new HotelCaliforniaException("CAPACIDADE EXCEDIDA");
		if(!periodoMinino(dataInicio)) throw new HotelCaliforniaException("NECESSARIO ANTECEDENCIA MINIMA DE 01 (UM) DIA");
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		Usuario cliente = controllerusuarios.getUsuarios().get(idCliente);
		Refeicao arrRefeicao = cr.getRefeicoesPorIdstr(idRefeicoes);
		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);
		if(matcher.find()) {
			if(!existeReservaRestaurante(dataInicio, dataFim)) {
				Reservas novaReserva = new ReservaRestaurante(idCliente, dataInicio, dataFim, arrRefeicao, numPessoas, cliente, "RESTAURANTE");
				this.reservas.put(contador++, novaReserva);
				return novaReserva.toString();
			}
			throw new HotelCaliforniaException("JA EXISTE RESERVA PARA ESTA DATA");
		}else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
		}
	}

	public String reservarAuditorio(String idAutenticacao, String idCliente,long idAuditorio, LocalDateTime dataInicio,LocalDateTime dataFim, int qtdMaxPessoas ) {
		if(qtdMaxPessoas > 150) throw new HotelCaliforniaException("CAPACIDADE EXCEDIDA");
		if(!periodoMinino(dataInicio)) throw new HotelCaliforniaException("NECESSARIO ANTECEDENCIA MINIMA DE 01 (UM) DIA");
		Usuario u = controllerusuarios.getUsuarios().get(idAutenticacao);
		Usuario cliente = controllerusuarios.getUsuarios().get(idCliente);
		AreaComum a = this.controllerAreas.getAreas().get(idAuditorio);
		String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);
		if(matcher.find()) {
			if(!existeReservaAuditorio(dataInicio, dataFim)) {
				Reservas novaReserva = new ReservaAuditorio(idCliente, dataInicio, dataFim, cliente, a, qtdMaxPessoas, "AUDITORIO");
				this.reservas.put(contador++, novaReserva);
				return novaReserva.toString();
			}
			throw new HotelCaliforniaException("JA EXISTE RESERVA PARA ESTA DATA");
		}else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
		}
	}
	
	public Reservas getReserva(long idReserva) {
		return this.reservas.get(idReserva);
	}

}
