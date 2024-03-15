package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Matiere;
import Model.MaConnexion;

public class MatiereDAO {
	Matiere a;
	MaConnexion c;
	
	public MatiereDAO(Matiere a) {
		super();
		this.a = a;
		this.c = new MaConnexion();
	}

	public MatiereDAO() {
		super();
		this.c = new MaConnexion();
	}
	
	public boolean existe(String nom) {
	    String reqMatiere = "SELECT COUNT(*) FROM matiere WHERE nom = ?";
	    try {
	        this.c.connect();
	        PreparedStatement select = this.c.connection.prepareStatement(reqMatiere);
	        select.setString(1, nom);
	        ResultSet res = select.executeQuery();
	        
	        // Récupérer le nombre d'enregistrements retournés
	        if (res.next()) {
	            int count = res.getInt(1);
	            return count > 0; // Si count > 0, la matiere existe
	        }
	        return false; // Aucun enregistrement trouvé
	        
	    } catch (ClassNotFoundException | SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
	        // Fermeture des ressources
	        if (c.connection != null) {
	            try {
	                c.connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public int add(Matiere a) {
		//preparer la requête d'insertion
        String reqMatiere="INSERT INTO `matiere`(`nom`)"+
                "VALUES (?)";//curseur sur les données récuperer
        //write you code here
        int id=0;
        try {
        	this.c.connect();
            System.out.println("Connexion OK");
            //requête pour la base de données insertion
            PreparedStatement insert = this.c.connection.prepareStatement(reqMatiere, Statement.RETURN_GENERATED_KEYS);
            insert.setString(1,a.getNom());
            insert.executeUpdate();
            ResultSet rSetIdGenerees0 = insert.getGeneratedKeys();
            while(rSetIdGenerees0.next()) {
                id = rSetIdGenerees0.getInt(1);
            }
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e){
            System.out.println("PROBLEME");
            e.printStackTrace();
        } finally {
            if (c.connection !=null){
                try {
                    c.connection.close();
                } catch (SQLException e) {
                    System.out.println("PROBLEME");
                    e.printStackTrace();
                }
            }
        }
        //TODO FERMER CONNEXION BDD
        //récuperer l'id de la bdd -> retourner
        return id;
	}
	
	public void delete(int idToDelete) {
	    String reqDelete = "DELETE FROM matiere WHERE id = ?";
	    try {
	        this.c.connect();
	        System.out.println("Connexion OK");
	        PreparedStatement deleteStatement = this.c.connection.prepareStatement(reqDelete);
	        deleteStatement.setInt(1, idToDelete);

	        int rowsAffected = deleteStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Matière supprimé avec succès.");
	        } else {
	            System.out.println("Aucune matière trouvé avec l'ID spécifié.");
	        }
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException(e);
	    } catch (SQLException e) {
	        System.out.println("PROBLEME");
	        e.printStackTrace();
	    } finally {
	        if (c.connection != null) {
	            try {
	                c.connection.close();
	            } catch (SQLException e) {
	                System.out.println("PROBLEME");
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
public ArrayList<Matiere> getALL(){
		
        String reqMatiere="SELECT id, nom FROM `matiere` ORDER BY id";
        
        ArrayList<Matiere> des = new ArrayList<>();
        //write you code here
        try {
        	this.c.connect();
        	System.out.println("Connexion OK");
        	PreparedStatement select = this.c.connection.prepareStatement(reqMatiere);
            ResultSet res = select.executeQuery();
            while(res.next()) {
            	int id=res.getInt(1);
                String nom=res.getString(2);
                
                Matiere matiere = new Matiere(id, nom);
                des.add(matiere);
            }
            

            if (c.connection !=null){
                try {
                    c.connection.close();
                } catch (SQLException e) {
                    System.out.println("PROBLEME");
                    e.printStackTrace();
                }
            }
        	
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e){
            System.out.println("PROBLEME");
            e.printStackTrace();
        } 
            
        return des;
        //TODO FERMER CONNEXION BDD
       
    }

	public void modify(int id, String newNom) {
	    String updateQuery = "UPDATE matiere SET nom = ? WHERE id = ?";
	    
	    try {
	        this.c.connect();
	        System.out.println("Connexion OK");
	        PreparedStatement updateStatement = this.c.connection.prepareStatement(updateQuery);
	        updateStatement.setString(1, newNom);
	        updateStatement.setInt(2, id);
	        
	        int rowsAffected = updateStatement.executeUpdate();
	        
	        if(rowsAffected > 0) {
	            System.out.println("Les informations de la matière avec l'ID " + id + " ont été mises à jour avec succès.");
	        } else {
	            System.out.println("Aucune matière trouvée avec l'ID : " + id);
	        }
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException(e);
	    } catch (SQLException e) {
	        System.out.println("PROBLEME");
	        e.printStackTrace();
	    } finally {
	        if (c.connection != null) {
	            try {
	                c.connection.close();
	            } catch (SQLException e) {
	                System.out.println("PROBLEME");
	                e.printStackTrace();
	            }
	        }
	    }
	}

	
	public static void main(String[] args){
		MatiereDAO dao=new MatiereDAO();
	    Scanner sc=new Scanner(System.in);
	    Matiere m=new Matiere();
	    
	    ArrayList<Matiere> eAll = dao.getALL();
	    System.out.println("Liste des matière : ");
	    for (Matiere matiere : eAll) {
	        System.out.println(matiere);
	    }
	    
	    // Lecture de l'ID de la matière à modifier
	    System.out.println("Entrez l'ID de la matière à modifier : ");
	    int id = sc.nextInt();

	    // Consommation de la nouvelle ligne
	    sc.nextLine();

	    // Lecture du nouveau nom de la matière
	    System.out.println("Entrez le nouveau nom de la matière : ");
	    String newNom = sc.nextLine();

	    // Modification de la matière
	    dao.modify(id, newNom);
	    
	    
	    ArrayList<Matiere> eAll2 = dao.getALL();
	    System.out.println("Liste des matière : ");
	    for (Matiere matiere : eAll2) {
	        System.out.println(matiere);
	    }
		
		 // Exemple de valeurs de matière à vérifier
	    System.out.println("Entrez la matiere : ");
	    String nom = sc.nextLine();
		// Appel de la méthode existe
	    boolean utilisateurExiste = dao.existe(nom);

	    // Affichage du résultat
	    if (utilisateurExiste) {
	        System.out.println("\n La matiere " + nom + " existe dans la base de données.");
	    } else {
	        System.out.println("\n La matiere " + nom + " n'existe pas dans la base de données.");
	    }
	    
	    // Ajout d'un nouvel enregistrement
	    System.out.println("entrer une nouvelle matiere :");
	    m.nom=sc.nextLine();
	    int nouvelId = dao.add(m);
	    System.out.println("Nouvel ID ajouté : " + nouvelId);
	    
	 // Suppression d'un enregistrement
	    System.out.println("entrer le l'id de la matière que vous voulez supprimer :");
	    int loginSupprimer = sc.nextInt(); 
	    dao.delete(loginSupprimer);
	    
	    
	    sc.close();
	}

}
