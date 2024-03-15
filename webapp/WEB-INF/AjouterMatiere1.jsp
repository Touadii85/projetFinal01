<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une matiere</title>
</head>
<body>	
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
    <form action="ControllerAjouter2" method="post">
        <div class="matiere">
            <label for="matiere">Matière</label>
            <input type="text" name="txtmatiere" id="matiere" required>
        </div>

        <div class="buttons">
            <button type="submit">Valider</button>
        </div>
    </form>
</body>
</html>