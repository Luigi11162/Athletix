package Model.bean;

import java.io.Serializable;

public class OperatoreBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id=-1;
	private String nome="";
	private String cognome="";
	private String email="";
	private String password="";
	
	public OperatoreBean() {
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

	@Override
	public String toString() {
		return "OperatoreBean [id=" + id + ", Nome=" + nome + ", Cognome=" + cognome + ", Email=" + email
				+ ", Password=" + password + "]";
	}
	
	
}
