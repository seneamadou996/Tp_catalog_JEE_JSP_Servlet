package dao;

import java.sql.Connection;
import java.sql.DriverManager;

//class pour cr�ation de la connexion � la base de donn�es
public class SingletonConnection {

	//d�claration d'un connexion de fa�on statique 
	 private static Connection connection;
	 
	 static {
		 try {
			 //cr�ation de la connexion avec le pilote jdbc en sp�cifiant l'url de connexion
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_CATALOG?serverTimezone=UTC","root","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 //creation de la methode getConnection pour la r�cup�ration de la connexion
	public static Connection getConnection() {
		return connection;
	}
}
