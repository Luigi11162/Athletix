package Model.bean;

import java.io.Serializable;
import java.sql.Date;

public class RecensioneBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id=-1;
	private int idArticolo=-1;
	private int idUtente=-1;
	private String descrizione="";
	private Date data=new Date(0);
	
	public RecensioneBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RecensioneBean [id=" + id + ", idArticolo=" + idArticolo + ", idUtente=" + idUtente + ", descrizione="
				+ descrizione + ", data=" + data + "]";
	}
	
	
}
