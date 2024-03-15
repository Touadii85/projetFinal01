<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, Model.Matiere, DAO.MatiereDAO, jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des Matières</title>
</head>
<body>
    <h1>Liste des Matières</h1>
    
    <form action="DeconnexionServlet" method="post">
    <button type="submit">Déconnexion</button>
	</form>
    
    <%
	String login = (String) session.getAttribute("login");
	if (login != null) {
	%>
	    <p>utilisateur actuel: <%= login %> !</p>
	<%
	}
	%>
    
    <a href="MatiereAjouter">Ajouter Matière</a>
    <a href="VueListe">VueListe</a>
    
    <% 
        // Création d'une instance de MatiereDAO
        MatiereDAO dao = new MatiereDAO();
        
        // Récupération de la liste des matières
        ArrayList<Matiere> matieres = dao.getALL();
        
        // Vérification si la liste n'est pas vide
        if (!matieres.isEmpty()) {
    %>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>                    
                    <th>Modify</th>
                    <th>Delete</th>
                </tr>
                <% 
                    // Parcours de la liste des matières
                    for (Matiere matiere : matieres) {
                %>
                        <tr>
                            <td><%= matiere.getId() %></td>
                            <td><%= matiere.getNom() %></td>
                            <td><a href="recupModif2?id=<%= matiere.getId() %>&txtmatiere=<%= matiere.getNom() %>">Modifier</a></td>
                            <td><a href="ControllerSupprimer2?id=<%= matiere.getId() %>">Supprimer</a></td>
                            
                        </tr>
                <% 
                    }
                %>
            </table>
    <% 
        } else {
            out.println("<p>Aucune Matière n'est disponible.</p>");
        }
    %>

</body>
</html>