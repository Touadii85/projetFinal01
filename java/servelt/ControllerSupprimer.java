package servelt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import DAO.CompteDAO;


public class ControllerSupprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ControllerSupprimer() {
        super();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifiez si l'utilisateur est connecté en vérifiant la session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            // Si aucun utilisateur n'est connecté, redirigez vers la page d'authentification
            response.sendRedirect("Authentification.jsp");
            return; // Arrêtez le traitement pour éviter toute autre exécution de code
        }

        // Récupere l'id
        String idString = request.getParameter("id");
        
        if(idString != null) {
            try {
                // Convertir l'id en entier
                int id = Integer.parseInt(idString);
                
                //supprimer le compte avec l'id spécifier
                CompteDAO dao = new CompteDAO();
                dao.delete(id);
                
                // Rediriger vers VueListe.jsp après la suppression
                getServletContext().getRequestDispatcher("/WEB-INF/VueListe.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Gérer les cas où l'ID n'est pas un entier valide
                response.getWriter().println("L'ID spécifié n'est pas valide.");
            }
        } else {
            // Gérer les cas où aucun ID n'est fourni dans la requête
            response.getWriter().println("Aucun ID spécifié.");
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
