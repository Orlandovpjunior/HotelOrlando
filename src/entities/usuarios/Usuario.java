package entities.usuarios;

public abstract class Usuario {

	protected String id;
	protected String nome;
	protected String tipo;
	protected long doc;

	public Usuario(String id, String nome, String tipo, long doc) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.doc = doc;
	}

	public abstract String getId();
	public abstract String getNome();
	public abstract String getTipo();
	public abstract long getDoc();
	public abstract void setTipo(String novoTipo);
	public abstract void setId(String idnovo);


}