package Model.bean;

import java.io.Serializable;

public class UnitaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id=-1;
	private String nome="";
	private int quantita=-1;
	
	public UnitaBean() {
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

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		return "UnitaBean [id=" + id + ", Nome=" + nome + ", Quantita=" + quantita + "]";
	}
	
	
}
