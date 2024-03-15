<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Compte</title>
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
	
    <form action="ControllerAjouter" method="post">
        <div class="login">
            <label for="login">Login</label>
            <input type="text" name="txtlogin" id="login" required>
        </div>

        <div class="password">
            <label for="mot_de_passe">Mot de passe</label>
            <input type="text" name="txtmot_de_passe" id="mot_de_passe" required>
        </div>

        <div class="buttons">
            <button type="submit">Valider</button>
        </div>
    </form>
</body>
</html>