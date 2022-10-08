package Model.bean;

import java.io.Serializable;

public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id=-1;
	private String numero="";
	private String scadenza="";
	private int codice=-1;
	private String nome="";
	private int idUtente=-1;
	
	public PagamentoBean() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getScadenza() {
		return scadenza;
	}
	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	
	@Override
	public String toString() {
		return "PagamentoBean [id=" + id + ", numero=" + numero + ", scadenza=" + scadenza + ", codice=" + codice
				+ ", nome=" + nome + ", idUtente=" + idUtente + "]";
	}

	
	
}
