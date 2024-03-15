package servelt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import DAO.CompteDAO;
import Model.Compte;


public class recupModif extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public recupModif() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vérifier si l'utilisateur est connecté en vérifiant la session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            // Si aucun utilisateur n'est connecté, rediriger vers la page d'authentification
            response.sendRedirect("Authentification.jsp");
            return; // Arrêter le traitement pour éviter toute autre exécution de code
        }
		// Récupere l'id, le login et le password
    	String idString = request.getParameter("id");
    	String login = request.getParameter("login");
    	String password = request.getParameter("pwd");
    	
    	//vérifier si les valeurs ne sont pas null et si l'id est un entier
    	if (idString != null && login != null && password != null) {
    		try {
    			int id = Integer.parseInt(idString);
    			// Créer un objet Compte avec les valeurs récupérées
    			Compte compte = new Compte(id, login, password);
    			
    			// Stocker l'objet Compte dans l'attribut de la requête pour l'afficher dans la page JSP
                request.setAttribute("compte", compte);
                
             // Rediriger vers la page de modification
                request.getRequestDispatcher("/WEB-INF/ModifierCompte.jsp").forward(request, response);
    			
    		} catch (NumberFormatException e) {
                // Gérer les erreurs de conversion de l'ID
                response.getWriter().println("L'ID spécifié n'est pas valide.");
            }
        } else {
            // Gérer les cas où des paramètres sont manquants
            response.getWriter().println("Paramètres manquants dans la requête.");
        	}
    	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

