<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, Model.Compte, Model.Matiere, DAO.CompteDAO, Model.Specialiter, DAO.SpecialiterDAO, jakarta.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des comptes</title>
</head>
<body>
    <h1>Liste des comptes</h1>
    
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
    <a href="CompteAjouter">Ajouter un Compte</a>
    <a href="VueMatiere">Ajouter une Spécialité</a>
    <% 
        // Création d'une instance de CompteDAO
        CompteDAO dao = new CompteDAO();
        
        // Récupération de la liste des comptes
        ArrayList<Compte> eAll = dao.getAll();
        
        SpecialiterDAO specialiterDAO = new SpecialiterDAO();
        ArrayList<Specialiter> specialites = specialiterDAO.getAll();

        
        // Vérification si la liste n'est pas vide
        if (!eAll.isEmpty()) {
    %>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Login</th>
                    <th>Password</th>
                    <th>Spécialité</th>
                    <th>Modifier</th>
                    <th>Supprimer</th>
                </tr>
                <% 
                    // Parcours de la liste des comptes
                    for (Compte compte : eAll) {
                %>
                        <tr>
                            <td><%= compte.getId() %></td>
                            <td><%= compte.getLogin() %></td>
                            <td><%= compte.getPwd() %></td>
                            <td>
                                <form action="ModifierSpecialite" method="post">
                                    <input type="hidden" name="idCompte" value="<%= compte.getId() %>">
                                    <select name="specialite">
                                        <%
                                        if (specialites.isEmpty()) {
                                        %>
                                            <option value="">Aucune spécialité</option>
                                        <%
                                        } else {
                                            for (Matiere specialite : specialites) {
                                        %>
                                            <option value="<%= specialite.getId() %>"><%= specialite.getNom() %></option>
                                        <%
                                            }
                                        }
                                        %>
                                    </select>
                                    <button type="submit">Changer</button>
                                </form>
                            </td>
                            <td><a href="recupModif?id=<%= compte.getId() %>&login=<%= compte.getLogin() %>&pwd=<%= compte.getPwd() %>">Modifier</a></td>
                            <td><a href="ControllerSupprimer?id=<%= compte.getId() %>">Supprimer</a></td>
                        </tr>
                <% 
                    }
                %>
            </table>
    <% 
        } else {
            out.println("<p>Aucun compte n'est disponible.</p>");
        }
    %>
</body>
</html>