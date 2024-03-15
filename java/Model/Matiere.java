package Model;

public class Matiere {
	public int id;
	public String nom;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Matiere() {
		super();
	}
	public Matiere(int id) {
		super();
		this.id = id;
	}
	public Matiere(String nom) {
		super();
		this.nom = nom;
	}
	public Matiere(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Matiere [id=" + id + ", nom=" + nom + "]";
	}
	
}