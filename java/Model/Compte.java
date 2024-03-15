package Model;

public class Compte {
	public int id;
	public String login;
	public String pwd;
	public String specialite;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public Compte(String login, String pwd) {
		super();
		this.login = login;
		this.pwd = pwd;
	}
	public Compte(int id, String login, String pwd) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
	}
	public Compte(int id, String login, String pwd, String specialite) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.specialite = specialite;
	}
	public Compte() {
		super();
	}
	@Override
	public String toString() {
		return "Compte [id=" + id + ", login=" + login + ", pwd=" + pwd + ", specialite=" + specialite + "]";
	}
}