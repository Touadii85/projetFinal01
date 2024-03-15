package servelt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


import DAO.MatiereDAO;
import Model.Matiere;

public class ControllerAjouter2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ControllerAjouter2() {
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
        String matiere = request.getParameter("txtmatiere");

        // Création d'un objet Matiere
        Matiere compte = new Matiere(matiere);

        // Création d'une instance de MatiereDAO
        MatiereDAO dao = new MatiereDAO();

        // Ajout du nouveau compte
        int nouvelId = dao.add(compte);

        // Redirection vers une page de confirmation ou une autre page appropriée
        getServletContext().getRequestDispatcher("/WEB-INF/MatiereListe.jsp").forward(request, response);
    }
}