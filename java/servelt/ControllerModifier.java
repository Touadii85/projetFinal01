package servelt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import DAO.CompteDAO;

public class ControllerModifier extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ControllerModifier() {
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
        String login = request.getParameter("txtlogin");
        String password = request.getParameter("txtmot_de_passe");

        // Convertir l'id en entier
        int id = Integer.parseInt(idString);

        // Créer une instance de CompteDAO
        CompteDAO dao = new CompteDAO();

        // Appeler la méthode modify pour mettre à jour le compte
        dao.modify(id, login, password);

        // Rediriger vers VueListe.jsp après la mise à jour
        getServletContext().getRequestDispatcher("/WEB-INF/VueListe.jsp").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
