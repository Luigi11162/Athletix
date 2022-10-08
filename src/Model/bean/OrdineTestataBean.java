package Model.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class OrdineTestataBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id=-1;
	private Date data=new Date(0);
	private Time ora=new Time(0);
	private String indirizzo_Fatturazione="";
	private String pagamento="";
	private String indirizzo_Spedizione="";
	private List<OrdineRigoBean> ordineRigo=new ArrayList<>();
	private int idUtente=-1;
	
	public OrdineTestataBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getOra() {
		return ora;
	}

	public void setOra(Time ora) {
		this.ora = ora;
	}
	public String getIndirizzo_Fatturazione() {
		return indirizzo_Fatturazione;
	}

	public void setIndirizzo_Fatturazione(String indirizzo_Fatturazione) {
		this.indirizzo_Fatturazione = indirizzo_Fatturazione;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getIndirizzo_Spedizione() {
		return indirizzo_Spedizione;
	}

	public void setIndirizzo_Spedizione(String indirizzo_Spedizione) {
		this.indirizzo_Spedizione = indirizzo_Spedizione;
	}
	
	public List<OrdineRigoBean> getOrdineRigo() {
		return ordineRigo;
	}

	public void setOrdineRigo(List<OrdineRigoBean> ordineRigo) {
		this.ordineRigo = ordineRigo;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	@Override
	public String toString() {
		return "OrdineTestataBean [id=" + id + ", data=" + data + ", ora=" + ora + ", indirizzo_Fatturazione="
				+ indirizzo_Fatturazione + ", pagamento=" + pagamento + ", indirizzo_Spedizione=" + indirizzo_Spedizione
				+ ", ordineRigo=" + ordineRigo + ", idUtente=" + idUtente + "]";
	}
	
}
