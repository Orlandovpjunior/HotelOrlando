package controller;

import java.util.HashMap;

import entities.usuarios.Administrador;
import entities.usuarios.Cliente;
import entities.usuarios.Funcionario;
import entities.usuarios.Gerente;
import entities.usuarios.Usuario;
import exception.HotelCaliforniaException;

public class ControllerUsuarios {

	private int contCLi;
	private int contFun;
	private int contGer;
	private int contAdm;
	private HashMap<String, Usuario> usuarios;
	private static ControllerUsuarios instance;

	public ControllerUsuarios() {
		this.usuarios = new HashMap<String, Usuario>();
		String id = this.geraId("Administrador");
		Usuario usuario = new Administrador(id, "Joao Costa", (long)123456);
		this.usuarios.put(id, usuario);
		this.contAdm += 1;
	}

	public static ControllerUsuarios getInstance() {
		if (instance == null) {
			instance = new ControllerUsuarios();
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	public void init() {
		this.usuarios.clear();
		this.usuarios = new HashMap<String, Usuario>();
		this.contAdm = 1;
		this.contGer = 1;
		this.contFun = 1;
		this.contCLi = 1;
		String id = this.geraId("Administrador");
		Usuario usuario = new Administrador(id, "Joao Costa", (long)123456);
		this.usuarios.put(id, usuario);
		this.contAdm += 1;
	}

	private String geraId(String tipo) {
		if (tipo.toLowerCase().contains("adm")) {
			return "ADM" + this.contAdm++;
		} else if (tipo.toLowerCase().contains("ger")) {
			return "GER" + this.contGer++;
		} else if (tipo.toLowerCase().contains("fun")) {
			return "FUN" + this.contFun++;
		} else if (tipo.toLowerCase().contains("cli")) {
			return "CLI" + this.contCLi++;
		} else {
			throw new IllegalArgumentException("Não foi possível gerar ID");
		}
	}

	private static boolean verificaId(String idAutenticacao, String tipoUsuario) {
		if (tipoUsuario.equalsIgnoreCase("CLI") && !idAutenticacao.contains("CLI")) {
			return true;
		} else if (tipoUsuario.equalsIgnoreCase("FUN") && (idAutenticacao.contains("GER") || idAutenticacao.contains("ADM"))) {
			return true;
		} else if (tipoUsuario.equalsIgnoreCase("GER") && idAutenticacao.contains("ADM")) {
			return true;
		} else if (tipoUsuario.equalsIgnoreCase("ADM") && idAutenticacao.contains("ADM")) {
			return true;
		} else {
			return false;
		}
	}


	private void verificaAlteraGerente() {
		for (String a: this.usuarios.keySet()) {
			if (a.contains("GER")){
				Usuario antigogerente = this.usuarios.get(a);
				antigogerente.setTipo("FUN");
				String idnovo = this.geraId("FUN");
				this.usuarios.remove(a);
				this.usuarios.put(idnovo, antigogerente);
			}
		}
	}

	private boolean verificaTipo(String tipo) {
		for (String a: this.usuarios.keySet()) {
			if (a.contains(tipo)) {
				return true;
			}
		}
		return false;
	}

	public String cadastrarUsuario(String idAutenticacao, String nome, String tipoUsuario, Long documento) {
		if (!this.usuarios.containsKey(idAutenticacao)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}
		if (this.verificaTipo("GER") && tipoUsuario.equals("GER")) {
			throw new HotelCaliforniaException("SO DEVE HAVER UM GERENTE NO HOTEL");
		}
		if (verificaId(idAutenticacao, tipoUsuario)) {
			if (tipoUsuario.toLowerCase().contains("cli")){
				String id = this.geraId(tipoUsuario);
				Usuario usuario = new Cliente(id, nome, documento);
				this.usuarios.put(id, usuario);
				return usuario.toString();
			} else if (tipoUsuario.toLowerCase().contains("fun")) {
				String id = this.geraId(tipoUsuario);
				Usuario usuario = new Funcionario(id, nome, documento);
				this.usuarios.put(id, usuario);
				return usuario.toString();
			} else if(tipoUsuario.toLowerCase().contains("ger")) {
				this.verificaAlteraGerente();
				String id = this.geraId(tipoUsuario);
				Usuario usuario = new Gerente(id, nome, documento);
				this.usuarios.put(id, usuario);
				return usuario.toString();
			} else if (tipoUsuario.toLowerCase().contains("adm")) {
				String id = this.geraId(tipoUsuario);
				Usuario usuario = new Administrador(id, nome, documento);
				this.usuarios.put(id, usuario);
				return usuario.toString();
			} else {
				return "ENTRADA INVALIDA";
			}
		} else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UM NOVO USUARIO DO TIPO");
		}
	}

	public String exibirUsuario(String idUsuario) {
		StringBuilder out = new StringBuilder();
		Usuario us = usuarios.get(idUsuario);
		out.append(us.toString());
		return out.toString();
	}

	public String[] listarUsuarios() {
		String[] arrayUsuarios = new String[this.usuarios.size()];
		int i = 0;
		for (String chave: this.usuarios.keySet()) {
			Usuario usuario = this.usuarios.get(chave);
			arrayUsuarios[i] = usuario.toString();
			i += 1;
		}
		return arrayUsuarios;
	}

	public String atualizarUsuario(String idAutenticacao, String idUsuario, String novoTipoUsuario) {
		if (!idAutenticacao.toLowerCase().contains("adm")) {
			throw new HotelCaliforniaException("APENAS O ADMINISTRADOR PODE ATUALIZAR OS USUARIOS");
		} else if (!this.usuarios.containsKey(idUsuario)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		} else if (!this.usuarios.containsKey(idAutenticacao)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		} else {
			Usuario usuario = this.usuarios.get(idUsuario);
			String idnovo = this.geraId(novoTipoUsuario);
			usuario.setId(idnovo);
			usuario.setTipo(novoTipoUsuario);
			return usuario.toString();
		}
	}

	public boolean verificaId(String id) {
		if (this.usuarios.containsKey(id)) {
			return true;
		} else {
			return false;
		}
	}

	public Usuario getUsuario(String id) {
		return this.usuarios.get(id);
	}

	public int getContCLi() {
		return contCLi;
	}

	public void setContCLi(int contCLi) {
		this.contCLi = contCLi;
	}

	public int getContFun() {
		return contFun;
	}

	public void setContFun(int contFun) {
		this.contFun = contFun;
	}

	public int getContGer() {
		return contGer;
	}

	public void setContGer(int contGer) {
		this.contGer = contGer;
	}

	public int getContAdm() {
		return contAdm;
	}

	public void setContAdm(int contAdm) {
		this.contAdm = contAdm;
	}

	public HashMap<String, Usuario> getUsuarios() {
		return usuarios;
	}

}