package servelt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import Model.Compte;
import DAO.CompteDAO;


public class ControllerConnecter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ControllerConnecter() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des paramètres du formulaire
        String login = request.getParameter("txtlogin");
        String password = request.getParameter("txtmot_de_passe");

        // Vérification de l'authentification
        CompteDAO compteDAO = new CompteDAO();
        boolean authentifie = compteDAO.existe(login, password);
        
        if (authentifie) {
        	// Stockez le login dans la session
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            // Redirection vers la page d'accueil ou toute autre page appropriée après l'authentification réussie
            
            getServletContext().getRequestDispatcher("/WEB-INF/VueListe.jsp").forward(request, response);
        } else {
            // Redirection vers la page d'authentification avec un message d'erreur si l'authentification échouer
            getServletContext().getRequestDispatcher("/WEB-INF/Authentification.jsp?erreur=1").forward(request, response);
        }
    }
}
