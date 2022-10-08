package Model.bean;

import java.io.Serializable;

public class OrdineRigoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id=-1;
	private int iva=-1;
	private int quantita=-1;
	private float prezzo=-1;
	private ArticoloBean articolo=new ArticoloBean();
	private int idTestata=-1;
	
	public OrdineRigoBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public ArticoloBean getArticolo() {
		return articolo;
	}

	public void setArticolo(ArticoloBean articolo) {
		this.articolo = articolo;
	}
	
	public int getIdTestata() {
		return idTestata;
	}

	public void setIdTestata(int idTestata) {
		this.idTestata = idTestata;
	}

	@Override
	public String toString() {
		return "OrdineRigoBean [id=" + id + ", iva=" + iva + ", quantita=" + quantita + ", prezzo=" + prezzo
				+ ", articolo=" + articolo + ", idTestata=" + idTestata + "]";
	}
	
}
