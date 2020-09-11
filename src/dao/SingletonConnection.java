package dao;

import java.sql.Connection;
import java.sql.DriverManager;

//class pour création de la connexion à la base de données
public class SingletonConnection {

	//déclaration d'un connexion de façon statique 
	 private static Connection connection;
	 
	 static {
		 try {
			 //création de la connexion avec le pilote jdbc en spécifiant l'url de connexion
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_CATALOG?serverTimezone=UTC","root","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 //creation de la methode getConnection pour la récupération de la connexion
	public static Connection getConnection() {
		return connection;
	}
}
