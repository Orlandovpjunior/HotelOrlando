package entities.usuarios;

public class Administrador extends Usuario {



    public Administrador(String id, String nome, long doc) {
        super(id, nome, "Administrador", doc);
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