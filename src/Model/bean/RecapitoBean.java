package Model.bean;

import java.io.Serializable;

public class RecapitoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id=-1;
	private String indirizzo="";
	private int cap=-1;
	private String citta="";
	private int idUtente=-1;
	
	public RecapitoBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	@Override
	public String toString() {
		return "RecapitoBean [id=" + id + ", indirizzo=" + indirizzo + ", cap=" + cap + ", citta=" + citta
				+ ", idUtente=" + idUtente + "]";
	}
	
}
