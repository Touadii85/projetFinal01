package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {
    static String url="jdbc:mysql://localhost:3306/projetfinal";
    static String user = "root";
    static String pwd = "rot";
    public Connection connection;
    public void connect() throws ClassNotFoundException, SQLException{
        connection=null;
        Class.forName("com.mysql.jdbc.Driver");
        //connexion avec easyline dans base de données
        connection = DriverManager.getConnection(url, user, pwd);
        System.out.print("Connexion OK");
    }

    public MaConnexion() {

    }
}