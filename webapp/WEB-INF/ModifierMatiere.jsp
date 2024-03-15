<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Model.Matiere"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier ce compte</title>
</head>
<body>
    <h1>Modifier ce compte</h1>
    <form action="DeconnexionServlet" method="post">
    <button type="submit">D�connexion</button>
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
        // R�cup�rer l'objet Compte de l'attribut de la requ�te
        Matiere matiere = (Matiere)request.getAttribute("matiere");
        
        // V�rifier si l'objet Compte n'est pas null
        if (matiere != null) {
    %>
    <form action="ControllerModifier2" method="post">
    	<div>
    		<input type="hidden" name="id" id="id" value="<%= matiere.getId() %>" required>
    	</div>
    	<div class="matiere">
            <label for="matiere">Mati�re</label>
            <input type="text" name="txtmatiere" id="matiere" value="<%= matiere.getNom() %>" required>
        </div>

        <div class="buttons">
            <button type="submit">Valider</button>
        </div>
    </form>
       <% 
        } else {
            out.println("<p>Aucune mati�re � modifier n'a �t� trouv�.</p>");
        }
    %>
   
</body>
</html>