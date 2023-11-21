package controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.areacomum.AreaComum;
import entities.areacomum.Auditorio;
import entities.usuarios.Usuario;
import exception.HotelCaliforniaException;


public class ControllerAreas {
	private HashMap<Long, AreaComum> areas;
	private Long contador = 1L;
	private static ControllerAreas instance;
	private ControllerUsuarios cu;

	public ControllerAreas() {
		this.areas = new HashMap<Long, AreaComum>();
		this.cu = ControllerUsuarios.getInstance();
	}

	public static ControllerAreas getInstance() {
        if (instance == null) {
            instance = new ControllerAreas();
        }
        return instance;
    }
	
	public static void resertInstance() {
		instance = null;
	}
	
	public void init() {
		this.areas.clear();
		this.areas = new HashMap<Long, AreaComum>();
	}
	
	public String disponibilizarAreaComum(String idAtenticacao, String tipoAreaComum, String titulo, LocalTime horarioInicio,LocalTime horarioFinal, double valorPessoa, boolean disponivel, int qtdMaxPessoas) {
		StringBuilder out = new StringBuilder();
		Usuario u = cu.getUsuarios().get(idAtenticacao);
		if(u == null) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}
		String idAutenticacaoUsuario = u.getId();
		String regex = "ADM\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);
		
		for (Map.Entry<Long, AreaComum> entry : this.areas.entrySet()) {
			AreaComum val = entry.getValue();
			if(val.getTitulo().equals(titulo)) {
				throw new HotelCaliforniaException("AREA COMUM JA EXISTE");
			}
		}
		
		if(horarioInicio.isAfter(horarioFinal)) {
			throw new HotelCaliforniaException("HORARIO DE FIM DEVE SER POSTERIOR AO HORARIO DE INICIO");
		}
		if(matcher.find() && tipoAreaComum.equals("AUDITORIO")) {
			AreaComum novaArea = new Auditorio(titulo, tipoAreaComum, horarioInicio, horarioFinal, valorPessoa, qtdMaxPessoas, disponivel);
			this.areas.put(contador++, novaArea);
			out.append(novaArea.toString());
		}else {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA AREA COMUM");
		}
		return out.toString();
	}
	
	public String alterarAreaComum(String idAtenticacao, long idAreaComum, LocalTime novoHorarioInicio,LocalTime novoHorarioFinal, double novoPreco, int capacidadeMax, boolean disponivel) {
		AreaComum a = this.areas.get(idAreaComum);
		a.setDataInicio(novoHorarioInicio);
		a.setDataFim(novoHorarioFinal);
		a.setValorPessoa(novoPreco);
		a.setNumPessoas(capacidadeMax);
		a.setDisponivel(disponivel);
		return a.toString();
	}
	
	public String[] listarAreasComuns() {
		ArrayList <String> arrAreas = new ArrayList<>();
		for (Map.Entry<Long, AreaComum> entry : areas.entrySet()) {
			AreaComum val = entry.getValue();
			arrAreas.add(val.toString());
		}
		String[] array = arrAreas.toArray(new String[arrAreas.size()]);
		return array;
	}
	
	public String exibirAreaComum(long idAreaComum) {
		AreaComum a = this.areas.get(idAreaComum);
		return a.toString();
	}

	public HashMap<Long, AreaComum> getAreas() {
		return areas;
	}
}