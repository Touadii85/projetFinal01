package servelt;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


public class DeconnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public DeconnexionServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Authentification.jsp").forward(request, response);
    }
}
