package servelt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.SpecialiterDAO;

import java.io.IOException;

public class AjouterSpecialite extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterSpecialite() {
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
        String login = request.getParameter("txtlogin");
        String idMatiereString = request.getParameter("idMatiere");

        if (login != null && idMatiereString != null) {
            try {
                int idMatiere = Integer.parseInt(idMatiereString);

                // Ajouter la matière à la spécialité de l'utilisateur
                SpecialiterDAO specialiterDAO = new SpecialiterDAO();
                specialiterDAO.add(login, idMatiere);

                // Rediriger vers la page de la liste des matières
                getServletContext().getRequestDispatcher("/WEB-INF/MatiereListe.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Gérer les erreurs de conversion de l'ID de la matière
                response.getWriter().println("L'identifiant de la matière spécifiée n'est pas valide.");
            }
        } else {
            // Gérer les cas où aucun ID de matière ou login n'est fourni dans la requête
            response.getWriter().println("Aucun ID de matière ou login spécifié.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
