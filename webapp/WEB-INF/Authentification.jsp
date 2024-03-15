<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authentification</title>
</head>
<body>
    <form action="ControllerConnecter" method="post">
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
