package Model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UtenteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id=-1;
	private String nome="";
	private String cognome="";
	private String genere="";
	private String email="";
	private String password="";
	private String stato_account="";
	private List<OrdineTestataBean> ordini= new ArrayList<>();
	private	List<CarrelloBean> carrello= new ArrayList<>();
	private List<RecensioneBean> recensioni= new ArrayList<>();
	private List<PagamentoBean> pagamenti=new ArrayList<>();
	private List<RecapitoBean> recapiti=new ArrayList<>();
	
	public UtenteBean() {
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStato_account() {
		return stato_account;
	}
	public void setStato_account(String stato_account) {
		this.stato_account = stato_account;
	}

	public List<CarrelloBean> getCarrello() {
		return carrello;
	}

	public void setCarrello(List<CarrelloBean> carrello) {
		this.carrello = carrello;
	}

	public List<OrdineTestataBean> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<OrdineTestataBean> ordini) {
		this.ordini = ordini;
	}
	
	public List<RecensioneBean> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<RecensioneBean> recensioni) {
		this.recensioni = recensioni;
	}

	public List<PagamentoBean> getPagamenti() {
		return pagamenti;
	}

	public void setPagamenti(List<PagamentoBean> pagamenti) {
		this.pagamenti = pagamenti;
	}

	public List<RecapitoBean> getRecapiti() {
		return recapiti;
	}

	public void setRecapiti(List<RecapitoBean> recapiti) {
		this.recapiti = recapiti;
	}

	@Override
	public String toString() {
		return "UtenteBean [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", genere=" + genere + ", email="
				+ email + ", password=" + password + ", stato_account=" + stato_account + ", ordini=" + ordini
				+ ", carrello=" + carrello + ", recensioni=" + recensioni + ", pagamenti=" + pagamenti + ", recapiti="
				+ recapiti + "]";
	}
	
	
}
