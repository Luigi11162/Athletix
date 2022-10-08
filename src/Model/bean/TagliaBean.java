package Model.bean;

import java.io.Serializable;

public class TagliaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id=-1;
	private String taglia="";
	private int quantita=-1;
	
	public TagliaBean() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}
	
	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	@Override
	public String toString() {
		return "TagliaBean [id=" + id + ", taglia=" + taglia + ", quantita=" + quantita + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagliaBean other = (TagliaBean) obj;
		if (id != other.id)
			return false;
		if (taglia == null) {
			if (other.taglia != null)
				return false;
		} else if (!taglia.equals(other.taglia))
			return false;
		return true;
	}
}
