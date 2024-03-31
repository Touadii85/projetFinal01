package DAO;

import Model.Matiere;
import Model.Specialiter;
import Model.Compte;
import Model.MaConnexion;
import java.sql.*;
import java.util.ArrayList;

public class SpecialiterDAO {
    private MaConnexion c;

    public SpecialiterDAO(MaConnexion c) {
        this.c = new MaConnexion();
    }
    public SpecialiterDAO() {
		super();
		this.c = new MaConnexion();
	}

    public void add(String login, int idMatiere) {
        try {
            this.c.connect();
            // Requête pour obtenir l'ID du compte à partir du login
            String query = "SELECT id FROM compte WHERE login = ?";
            PreparedStatement statement = this.c.connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            
            int idCompte = 0;
            if (rs.next()) {
                idCompte = rs.getInt("id");
            } else {
                System.out.println("Aucun compte trouvé pour le login: " + login);
                return;
            }

            // Requête pour insérer la spécialité dans la base de données
            PreparedStatement insert = this.c.connection.prepareStatement("INSERT INTO specialiter (idcompte, idmatiere) VALUES (?, ?)");
            insert.setInt(1, idCompte);
            insert.setInt(2, idMatiere);
            insert.executeUpdate();
            
            System.out.println("Spécialité ajoutée avec succès pour le compte: " + login);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e){
            System.out.println("Problème lors de l'ajout de la spécialité pour le compte: " + login);
            e.printStackTrace();
        } finally {
            if (c.connection !=null){
                try {
                    c.connection.close();
                } catch (SQLException e) {
                    System.out.println("Problème lors de la fermeture de la connexion");
                    e.printStackTrace();
                }
            }
        }
    }
    
	public void delete(int id1, int id2) {
	    String reqDelete = "DELETE FROM specialiter WHERE idcompte = ? AND idmatiere = ?";
	    try {
	        this.c.connect();
	        System.out.println("Connexion OK");
	        PreparedStatement deleteStatement = this.c.connection.prepareStatement(reqDelete);
	        deleteStatement.setInt(1, id1);
	        deleteStatement.setInt(2, id2);

	        int rowsAffected = deleteStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Specialiter supprimé avec succès.");
	        } else {
	            System.out.println("Aucune specialiter trouvé avec les ID spécifié.");
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
	
	public ArrayList<Specialiter> getAll() {
	    String reqCompte = "SELECT idcompte, idmatiere FROM specialiter";

	    ArrayList<Specialiter> des = new ArrayList<>();

	    try {
	        this.c.connect();
	        System.out.println("Connexion OK");
	        PreparedStatement select = this.c.connection.prepareStatement(reqCompte);
	        ResultSet res = select.executeQuery();
	        
	        while (res.next()) {
	            int id1 = res.getInt(1);
	            int id2 = res.getInt(2);

	            Specialiter spe = new Specialiter(id1, id2);
	            des.add(spe);
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
	
	public ArrayList<Integer> getMatiereIdsByCompteId(int compteId) {
        ArrayList<Integer> matiereIds = new ArrayList<>();
        String sql = "SELECT idmatiere FROM specialiter WHERE idcompte = ?";
        
        try {
            this.c.connect();
            PreparedStatement ps = this.c.connection.prepareStatement(sql);
            ps.setInt(1, compteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                matiereIds.add(rs.getInt("idmatiere"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        } finally {
            // Close connections, handle exceptions
        }

        return matiereIds;
    }

    public void modify(int idCompte, int idMatiere, int newIdMatiere) {
        try {
            this.c.connect();
            // Requête pour mettre à jour la spécialité d'un compte
            String query = "UPDATE specialiter SET idmatiere = ? WHERE idcompte = ? AND idmatiere = ?";
            PreparedStatement statement = this.c.connection.prepareStatement(query);
            statement.setInt(1, newIdMatiere);
            statement.setInt(2, idCompte);
            statement.setInt(3, idMatiere);
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Spécialité mise à jour avec succès pour le compte ID: " + idCompte);
            } else {
                System.out.println("Aucune mise à jour effectuée. Vérifiez les IDs fournis.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Problème lors de la mise à jour de la spécialité pour le compte ID: " + idCompte);
            e.printStackTrace();
        } finally {
            if (c.connection !=null){
                try {
                    c.connection.close();
                } catch (SQLException e) {
                    System.out.println("Problème lors de la fermeture de la connexion");
                    e.printStackTrace();
                }
            }
        }
    }
	
    public static void main(String[] args) {
        // Création d'une instance de SpecialiterDAO
        SpecialiterDAO specialiterDAO = new SpecialiterDAO();

        // Spécifiez le login et l'ID de la matière à ajouter
        String login = "root"; // Remplacez "votre_login" par le login souhaité
        int idMatiere = 6; // Remplacez 123 par l'ID de la matière souhaitée

        // Test de la méthode ajouterMatiereSpecialite
        specialiterDAO.add(login, idMatiere);
    }
}
