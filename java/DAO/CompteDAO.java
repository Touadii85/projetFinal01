package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Compte;
import Model.MaConnexion;

public class CompteDAO {
	Compte a;
	MaConnexion c;
	
	public CompteDAO(Compte a) {
		super();
		this.a = a;
		this.c = new MaConnexion();
	}

	public CompteDAO() {
		super();
		this.c = new MaConnexion();
	}
	
	public boolean existe(String log, String pw) {
	    String reqCompte = "SELECT COUNT(*) FROM compte WHERE login = ? AND pwd = ?";
	    try {
	        this.c.connect();
	        PreparedStatement select = this.c.connection.prepareStatement(reqCompte);
	        select.setString(1, log);
	        select.setString(2, pw);
	        ResultSet res = select.executeQuery();
	        
	        // Récupérer le nombre d'enregistrements retournés
	        if (res.next()) {
	            int count = res.getInt(1);
	            return count > 0; // Si count > 0, l'utilisateur existe
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

	public int add(Compte a) {
		//preparer la requête d'insertion
        String reqCompte="INSERT INTO `compte`(`login`, `pwd`)"+
                "VALUES (?,?)";//curseur sur les données récuperer
        //write you code here
        int id=0;
        try {
        	this.c.connect();
            System.out.println("Connexion OK");
            //requête pour la base de données insertion
            PreparedStatement insert = this.c.connection.prepareStatement(reqCompte, Statement.RETURN_GENERATED_KEYS);
            insert.setString(1,a.getLogin());
            insert.setString(2,a.getPwd());
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
	    String reqDelete = "DELETE FROM compte WHERE id = ?";
	    try {
	        this.c.connect();
	        System.out.println("Connexion OK");
	        PreparedStatement deleteStatement = this.c.connection.prepareStatement(reqDelete);
	        deleteStatement.setInt(1, idToDelete);

	        int rowsAffected = deleteStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Utilisateur supprimé avec succès.");
	        } else {
	            System.out.println("Aucun utilisateur trouvé avec l'ID spécifié.");
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

	
	
	public ArrayList<Compte> getAll() {
    String reqCompte = "SELECT c.id, c.login, c.pwd, IFNULL(m.nom, 'Aucune') AS specialite " +
                       "FROM Compte c " +
                       "LEFT JOIN specialiter s ON c.id = s.idcompte " +
                       "LEFT JOIN matiere m ON s.idmatiere = m.id " +
                       "ORDER BY c.id";

    ArrayList<Compte> des = new ArrayList<>();

    try {
        this.c.connect();
        System.out.println("Connexion OK");
        PreparedStatement select = this.c.connection.prepareStatement(reqCompte);
        ResultSet res = select.executeQuery();
        
        while (res.next()) {
            int id = res.getInt(1);
            String login = res.getString(2);
            String pwd = res.getString(3);
            String specialite = res.getString(4);

            Compte compte = new Compte(id, login, pwd, specialite);
            des.add(compte);
        }
    } catch (ClassNotFoundException | SQLException e) {
        throw new RuntimeException(e);
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

    return des;
}

	public void modify(int id, String newLog, String newPw) {
	    String updateQuery = "UPDATE compte SET login=?, pwd=? WHERE id = ?";
	    
	    try {
	        this.c.connect();
	        System.out.println("Connexion OK");
	        PreparedStatement updateStatement = this.c.connection.prepareStatement(updateQuery);
	        updateStatement.setString(1, newLog);
	        updateStatement.setString(2, newPw);
	        updateStatement.setInt(3, id);
	        
	        int rowsAffected = updateStatement.executeUpdate();
	        
	        if(rowsAffected > 0) {
	            System.out.println("Les informations du compte avec l'ID " + id + " ont été mises à jour avec succès.");
	        } else {
	            System.out.println("Aucun compte trouvé avec l'ID : " + id);
	        }
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException(e);
	    } catch (SQLException e) {
	        System.out.println("PROBLEME lors de la mise à jour des informations du compte");
	        e.printStackTrace();
	    } finally {
	        if (c.connection != null) {
	            try {
	                c.connection.close();
	            } catch (SQLException e) {
	                System.out.println("PROBLEME lors de la fermeture de la connexion");
	                e.printStackTrace();
	            }
	        }
	    }
	}



public static void main(String[] args){
    CompteDAO dao=new CompteDAO();
    Scanner sc=new Scanner(System.in);
    Compte c=new Compte();
    
    ArrayList<Compte> eAll = dao.getAll();
    System.out.println("Liste des comptes : ");
    for (Compte compte : eAll) {
        System.out.println(compte);
    }
    
 // Exemple de valeurs de login et mot de passe à vérifier
    System.out.println("Entrez le login : ");
    String login = sc.nextLine();
    System.out.println("Entrez le mot de passe : ");
    String password = sc.nextLine();

    // Appel de la méthode existe
    boolean utilisateurExiste = dao.existe(login, password);

    // Affichage du résultat
    if (utilisateurExiste) {
        System.out.println("L'utilisateur avec le login " + login + " existe dans la base de données.");
    } else {
        System.out.println("L'utilisateur avec le login " + login + " n'existe pas dans la base de données.");
    }
    
    // Ajout d'un nouvel enregistrement
    System.out.println("entrer un nouveau utilisateur :");
    System.out.println("login :");
    c.login=sc.nextLine();
    System.out.println("password :");
    c.pwd=sc.nextLine();
    int nouvelId = dao.add(c);
    System.out.println("Nouvel ID ajouté : " + nouvelId);
    
    // Suppression d'un enregistrement
    System.out.println("entrer le l'id de l'utilisateur que vous voulez supprimer");
    int loginSupprimer = sc.nextInt(); 
    dao.delete(loginSupprimer);
    
 // Demander à l'utilisateur le login de l'utilisateur dont vous voulez modifier le mot de passe
    System.out.println("Entrez l'ID de l'utilisateur que vous voulez modifier :");
    int id = sc.nextInt();
 // Consommer la nouvelle ligne
    sc.nextLine();
    // Demander à l'utilisateur le nouveau login
    System.out.println("Entrez le nouveau login :");
    String newLog = sc.nextLine();

    // Demander à l'utilisateur le nouveau mot de passe
    System.out.println("Entrez le nouveau mot de passe :");
    String newPw = sc.nextLine();

    // Appeler la méthode modify
    dao.modify(id, newLog, newPw);
    
    
    
    ArrayList<Compte> eAll1 = dao.getAll();
    System.out.println("Liste des comptes : ");
    for (Compte compte : eAll1) {
        System.out.println(compte);
    }
    sc.close();


}
}