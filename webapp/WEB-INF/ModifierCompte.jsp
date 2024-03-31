<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Model.Compte, Model.Matiere, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier ce compte</title>
</head>
<body>
    <h1>Modifier ce compte</h1>
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
    <% 
        // Récupérer l'objet Compte de l'attribut de la requête
        Compte compte = (Compte)request.getAttribute("compte");
        
        // Vérifier si l'objet Compte n'est pas null
        if (compte != null) {
    %>
    <form action="ControllerModifier" method="post">
    	<div>
    		<input type="hidden" name="id" id="id" value="<%= compte.getId() %>" required>
    	</div>
        <div class="login">
            <label for="login">Login</label>
            <input type="text" name="txtlogin" id="login" value="<%= compte.getLogin() %>" required>
        </div>

        <div class="password">
            <label for="mot_de_passe">Mot de passe</label>
            <input type="text" name="txtmot_de_passe" id="mot_de_passe" value="<%= compte.getPwd() %>" required>
        </div>
        
        <%
            ArrayList<Matiere> allMatieres = (ArrayList<Matiere>) request.getAttribute("allMatieres");
            if (allMatieres != null) {
                for (Matiere matiere : allMatieres) {
        %>
            <div class="matiere">
                <input type="checkbox" name="matiereId" value="<%= matiere.getId() %>"> <%= matiere.getNom() %>
            </div>
        <%
                }
            }
        %>

        <div class="buttons">
            <button type="submit">Valider</button>
        </div>
    </form>
       <% 
        } else {
            out.println("<p>Aucun compte à modifier n'a été trouvé.</p>");
        }
    %>
   
</body>
</html>
