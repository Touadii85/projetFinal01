package servelt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import DAO.MatiereDAO;

public class ControllerModifier2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ControllerModifier2() {
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
        // Récupérer les paramètres de la requête
        String idString = request.getParameter("id");
        String nom = request.getParameter("txtmatiere");

        // Convertir l'id en entier
        int id = Integer.parseInt(idString);

        // Créer une instance de MatiereDAO
        MatiereDAO dao = new MatiereDAO();

        // Appeler la méthode modify pour mettre à jour le compte
        dao.modify(id, nom);

        // Rediriger vers MatiereListe.jsp après la mise à jour
        getServletContext().getRequestDispatcher("/WEB-INF/MatiereListe.jsp").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
