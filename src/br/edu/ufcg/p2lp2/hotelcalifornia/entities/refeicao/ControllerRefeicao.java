package br.edu.ufcg.p2lp2.hotelcalifornia.entities.refeicao;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.ControllerUsuarios;
import br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios.Usuario;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

public class ControllerRefeicao{
    private Map<Long, Refeicao> refeicoes;
    private static ControllerRefeicao instance;
    private ControllerUsuarios controlerUs;

	public ControllerRefeicao() {
        this.refeicoes = new HashMap<>();
        this.controlerUs = ControllerUsuarios.getInstance();
    }
	
	public static ControllerRefeicao getInstance() {
        if (instance == null) {
            instance = new ControllerRefeicao();
        }
        return instance;
    }
	
	public static void resertInstance() {
		instance = null;
	}
	
	public void init() {
		this.refeicoes.clear();
        this.controlerUs = ControllerUsuarios.getInstance();
	}

    public boolean existeRefeicaoTitulo(String titulo) {
        for (Refeicao r : this.refeicoes.values()) {
            if (r.getTitulo().equals(titulo)) return true;
        }
        return false;
    }

    public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo, LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel) {
    	Usuario u = this.controlerUs.getUsuarios().get(idAutenticacao);
        if(u == null) throw new HotelCaliforniaException("USUARIO NAO EXISTE");
    	String idAutenticacaoUsuario = u.getId();
		String regex = "(FUN|GER)\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(idAutenticacaoUsuario);
        if (matcher.find()) {
            if(horarioInicio.isAfter(horarioFinal)) throw new HotelCaliforniaException("HORARIO DE FIM DEVE SER POSTERIOR AO HORARIO DE INICIO");
            if (!existeRefeicaoTitulo(titulo)) {
                long idRefeicao = refeicoes.size() + 1;
                Refeicao novaRefeicao = new Refeicao(idRefeicao, tipoRefeicao, titulo, horarioInicio, horarioFinal, valor, disponivel);
                this.refeicoes.put(idRefeicao, novaRefeicao);
                return novaRefeicao.toString();
            } else throw new HotelCaliforniaException("REFEICAO JA EXISTE");
        }
        throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA REFEICAO");
    }

    public  String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal, double valorPorPessoa, boolean disponivel){
        if(refeicoes.containsKey(idRefeicao)){
            if(horarioInicio.isAfter(horarioFinal)) throw new HotelCaliforniaException("HORARIO DE FIM DEVE SER POSTERIOR AO HORARIO DE INICIO");
            refeicoes.get(idRefeicao).setRefeicao(horarioInicio, horarioFinal, valorPorPessoa, disponivel);
            return refeicoes.get(idRefeicao).toString();
        }
        throw new HotelCaliforniaException("REFEICAO NAO EXISTE!");
    }

    public String exibirRefeicao(long idRefeicao){
        if(refeicoes.containsKey(idRefeicao)){
            Refeicao r = refeicoes.get(idRefeicao);
            return r.toString().trim();
        }
        return "REFEIÇÃO INVÁLIDA!";
    }
    
    public Refeicao[] getRefeicoesPorId(String[] ids) {
    	Refeicao[] array = new Refeicao[ids.length];
    	for (int i = 0; i < ids.length; i++) {
    		Long id = Long.parseLong(ids[i]);
    		Refeicao r = this.refeicoes.get(id);
    		array[i] = r;
    	}
    	return array;
    }
    
    public Refeicao getRefeicoesPorIdstr(String ids) {
    	Long id = Long.parseLong(ids);
    	Refeicao r = this.refeicoes.get(id);
    	return r;
    }

    public String[] listarRefeicoes(){
        String[] listagem = new String[refeicoes.size()];
        int i = 0;
        for (Long chave : this.refeicoes.keySet()) {
            listagem[i] = refeicoes.get(chave).toString().trim();
            i++;
        }
        return listagem;
    }
    
    public Map<Long, Refeicao> getRefeicoes() {
		return refeicoes;
	}

	public void setRefeicoes(Map<Long, Refeicao> refeicoes) {
		this.refeicoes = refeicoes;
	}
	
	public Refeicao getRefeicao(long idRefeicao) {
	    return this.refeicoes.get(idRefeicao);
	}
	
	public Refeicao getRefeicaoNome(String titulo) {
		Refeicao r = null;
	    for (Map.Entry<Long, Refeicao> entry : refeicoes.entrySet()) {
			Long key = entry.getKey();
			Refeicao val = entry.getValue();
			if(val.getTitulo().equals(titulo));
				r = refeicoes.get(key);
				break;
		}
	    return r;
	}
	
	

}