package br.edu.ufcg.p2lp2.hotelcalifornia.entities.usuarios;

public class Gerente extends Usuario {


    public Gerente(String id, String nome, long doc) {
        super(id, nome, "Gerente", doc);
    }

    public String toString() {
        return String.format("[%s] %s (No. Doc. %d)", this.id, this.nome, this.doc);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public long getDoc() {
        return this.doc;
    }

    public void setId(String idnovo) {
        this.id = idnovo;
    }

    public void setTipo(String novoTipo) {
        this.tipo = novoTipo;
    }

}