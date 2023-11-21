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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getDoc() {
		return doc;
	}

	public void setDoc(long doc) {
		this.doc = doc;
	}

}