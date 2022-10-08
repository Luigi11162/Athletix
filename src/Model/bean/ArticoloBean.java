package Model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArticoloBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id=-1;
	private String nome="";
	private float prezzo=-1;
	private String descrizione="";
	private String marca="";
	private int quantita=-1;
	private String materiale="";
	private int iva=-1;
	private List<CategoriaBean> categorie= new ArrayList<>();
	private UnitaBean unita=new UnitaBean();
	private List<RecensioneBean> recensioni= new ArrayList<>();
	private List<TagliaBean> taglie=new ArrayList<>();
	private String immagine="";
	
	public ArticoloBean() {
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

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getMateriale() {
		return materiale;
	}

	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public List<CategoriaBean> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<CategoriaBean> categorie) {
		this.categorie = categorie;
	}

	public UnitaBean getUnita() {
		return unita;
	}

	public void setUnita(UnitaBean unita) {
		this.unita = unita;
	}

	public List<RecensioneBean> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<RecensioneBean> recensioni) {
		this.recensioni = recensioni;
	}

	public List<TagliaBean> getTaglie() {
		return taglie;
	}

	public void setTaglie(List<TagliaBean> taglie) {
		this.taglie = taglie;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	@Override
	public String toString() {
		return "ArticoloBean [id=" + id + ", nome=" + nome + ", prezzo=" + prezzo + ", Descrizione=" + descrizione
				+ ", Marca=" + marca + ", Quantita=" + quantita + ", Materiale=" + materiale + ", Iva=" + iva
				+ ", categorie=" + categorie + ", unita=" + unita + ", recensioni=" + recensioni + ", taglie=" + taglie
				+ "]";
	}
	
	
}
