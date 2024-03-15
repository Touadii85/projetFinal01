package Model;

public class Specialiter {
	public int idcompte;
	public int idmatiere;
	
	public int getIdcompte() {
		return idcompte;
	}
	public void setIdcompte(int idcompte) {
		this.idcompte = idcompte;
	}
	public int getIdmatiere() {
		return idmatiere;
	}
	public void setIdmatiere(int idmatiere) {
		this.idmatiere = idmatiere;
	}
	
	public Specialiter(int idcompte, int idmatiere) {
		super();
		this.idcompte = idcompte;
		this.idmatiere = idmatiere;
	}
	@Override
	public String toString() {
		return "Specialiter [idcompte=" + idcompte + ", idmatiere=" + idmatiere + "]";
	}
	

}
