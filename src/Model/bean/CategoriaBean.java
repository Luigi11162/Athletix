package Model.bean;

import java.io.Serializable;

public class CategoriaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	
	public CategoriaBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "CategoriaBean [id=" + id + ", Nome=" + nome + "]";
	}


}
