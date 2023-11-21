package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.quartos.QuartoDouble;
import entities.quartos.QuartoFamily;
import entities.quartos.QuartoSingle;
import entities.quartos.Quartos;
import entities.usuarios.Usuario;
import exception.HotelCaliforniaException;

public class ControllerQuartos {
	private HashMap<Integer, Quartos> quartos;
	private static ControllerQuartos instance;
	private ControllerUsuarios controllerUs;

	public ControllerQuartos() {
		this.quartos = new HashMap<Integer, Quartos>();
		this.controllerUs = ControllerUsuarios.getInstance();
	}

	public static ControllerQuartos getInstance() {
        if (instance == null) {
            instance = new ControllerQuartos();
        }
        return instance;
    }

	public static void resertInstance() {
		instance = null;
	}

	public void init() {
		this.quartos.clear();
	}

	public String disponibilizarQuartoSingle(String idAutenticacao,int idQuartoNum, double precoBase,double precoPorPessoa) {
		StringBuilder out = new StringBuilder();
		Usuario us = controllerUs.getUsuarios().get(idAutenticacao);
		if(this.quartos.get(idQuartoNum) != null) {
			throw new HotelCaliforniaException("QUARTO JA EXISTE");
		}
		if(us == null) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}else{
			String idAutenticacaoUsuario = us.getId();
			String regex = "ADM\\d+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(idAutenticacaoUsuario);
			if(matcher.find()) {
				Quartos novoQuarto = new QuartoSingle(idQuartoNum, precoBase, precoPorPessoa, idQuartoNum);
				quartos.put(idQuartoNum,novoQuarto);
				out.append(novoQuarto.toString());
			}else {
				throw new HotelCaliforniaException("USUARIO NAO E ADMINISTRADOR");
			}
		}
		return out.toString();
	}

	public String disponibilizarQuartoDouble(String idAutenticacao,int idQuartoNum, double precoBase, double precoPorPessoa,  String[] pedidos) {
		StringBuilder out = new StringBuilder();
		Usuario us = controllerUs.getUsuarios().get(idAutenticacao);
		if(this.quartos.get(idQuartoNum) != null) {
			throw new HotelCaliforniaException("QUARTO JA EXISTE");
		}
		
		if(us == null) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}else {
			String idAutenticacaoUsuario = us.getId();
			String regex = "ADM\\d+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(idAutenticacaoUsuario);
			if(matcher.find()) {
				Quartos novoQuarto = new QuartoDouble(idQuartoNum, precoBase, precoPorPessoa, idQuartoNum, pedidos);
				quartos.put(idQuartoNum,novoQuarto);
				out.append(novoQuarto.toString());
			}else {
				throw new HotelCaliforniaException("USUARIO NAO E ADMINISTRADOR");
			}
		}
		return out.toString();
	}

	public String disponibilizarQuartoFamily(String idAutenticacao,int idQuartoNum,double precoBase, double precoPorPessoa, String[] pedidos, int qtdMaxPessoas) {
		StringBuilder out = new StringBuilder();
		Usuario us = controllerUs.getUsuarios().get(idAutenticacao);
		if(this.quartos.get(idQuartoNum) != null) {
			throw new HotelCaliforniaException("QUARTO JA EXISTE");
		}
		
		if(us == null) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}else {
			String idAutenticacaoUsuario = us.getId();
			String regex = "ADM\\d+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(idAutenticacaoUsuario);
			if(matcher.find()) {
				Quartos novoQuarto = new QuartoFamily(idQuartoNum, precoBase, precoPorPessoa, qtdMaxPessoas, pedidos);
				quartos.put(idQuartoNum,novoQuarto);
				out.append(novoQuarto.toString());
			}else {
				throw new HotelCaliforniaException("USUARIO NAO E ADMINISTRADOR");
			}
		}
		return out.toString();
	}

	public String exibirQuarto(int idQuartoNum) {
		return this.quartos.get(idQuartoNum).toString();
	}

	public Map<Integer, Quartos> getQuartos() {
	    return this.quartos;
	}

	public void setQuartos(HashMap<Integer, Quartos> quartos) {
		this.quartos = quartos;
	}

	public String[] listarQuartos() {
		String[] arrQuartos = new String[this.quartos.size()];
		int i = 0;
		for(Quartos quarto: this.quartos.values()) {
			arrQuartos[i] = quarto.toString() + "\n";
			i++;
		}
		return arrQuartos;
	}

}
