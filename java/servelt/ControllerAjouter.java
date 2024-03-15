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

public class ControllerAjouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ControllerAjouter() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vérifier si l'utilisateur est connecté en vérifiant la session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            // Si aucun utilisateur n'est connecté, rediriger vers la page d'authentification
            response.sendRedirect("Authentification.jsp");
            return; // Arrêter le traitement pour éviter toute autre exécution de code
        }
        // Récupération des paramètres du formulaire
        String login = request.getParameter("txtlogin");
        String password = request.getParameter("txtmot_de_passe");
        // Création d'un objet Compte
        Compte compte = new Compte(login, password);

        // Création d'une instance de CompteDAO
        CompteDAO dao = new CompteDAO();

        // Ajout du nouveau compte
        int nouvelId = dao.add(compte);

        // Redirection vers une page de confirmation ou une autre page appropriée
        getServletContext().getRequestDispatcher("/WEB-INF/VueListe.jsp").forward(request, response);
    }
}
