package Model.bean;

import java.io.Serializable;

public class CarrelloBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id=-1;
	private int idArticolo=-1;
	private int idUtente=-1;
	private int quantita=-1;
	private TagliaBean taglia=new TagliaBean();
	
	public CarrelloBean() {
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

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public TagliaBean getTaglia() {
		return taglia;
	}

	public void setTaglia(TagliaBean taglia) {
		this.taglia = taglia;
	}

	@Override
	public String toString() {
		return "CarrelloBean [id=" + id + ", idArticolo=" + idArticolo + ", idUtente=" + idUtente + ", quantita="
				+ quantita + ", taglia=" + taglia + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrelloBean other = (CarrelloBean) obj;
		if (id != other.id)
			return false;
		if (idArticolo != other.idArticolo)
			return false;
		if (idUtente != other.idUtente)
			return false;
		if (taglia == null) {
			if (other.taglia != null)
				return false;
		} else if (!taglia.equals(other.taglia))
			return false;
		return true;
	}

}
