package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.entities.Produit;

//implémentation de l'interface IProduitDao
public class ProduitDaoImpl implements IProduitDao{

	//implémentation de la méthode de sauvegarde des produits
	@Override
	public Produit save(Produit p) {
		
		//ouverture d'une nouvelle connexion
		Connection connection = SingletonConnection.getConnection();
		
		try {
			
			//préparer la requête ps pour l'isertion des produit dans la base de données
			PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUITS (DESIGNATION, PRIX, QUANTITE) VALUES(?,?,?)");
			
			//Transmission des paramètres a la requête
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			
			//execution de la requête
			ps.executeUpdate();
			
			//envoie dune requête pour la récupération de l'id
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(ID) AS MAX_ID FROM PRODUITS");
			ResultSet rs = ps2.executeQuery();
			
			//récupération du résultat
			if(rs.next()) {
				p.setId(rs.getLong("MAX_ID"));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	//implémentation de la méthode pour la recherche de produit par mot clé
	@Override
	public List<Produit> produitsParMC(String mc) {
		//création d'une nouvelle liste de produit
		List<Produit> produits=new ArrayList<Produit>();
		
		//ouvrir la connexion
		Connection connection  = SingletonConnection.getConnection();
		
		try {
			
			//préparation de la requête pour la récupération des produit par désignation
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE DESIGNATION LIKE ?");
			
			//transmission du paramètre mot clé mc
			ps.setString(1, mc);
			
			//exécution de la requête
			ResultSet rs = ps.executeQuery();
			
			//récupération du résultat
			while(rs.next()) {
				
				//stockage du résultat dans le model
				Produit p = new Produit();
				p.setId(rs.getLong("ID"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
				produits.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produits;
	}

	//implémentation de la méthode pour la récupration d'un produit par ID 
	@Override
	public Produit getProduit(Long id) {
		//instanciation de la class Produit
		Produit p= null;
		
		//ouverture de la connexion
		Connection connection  = SingletonConnection.getConnection();
		
		try {
			//requête pour la récupération d'un produit connaissant l'ID
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			//récupération du résultat obtenue
			while(rs.next()) {
				
				//stockage du résultat dans le model
				p = new Produit();
				p.setId(rs.getLong("ID"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}

	//implémentation de la méthode update
	@Override
	public Produit update(Produit p) {
		
		//ouverture de la connexion
		Connection connection = SingletonConnection.getConnection();
		
		try {
			//requête pour la mis à jour du produit récupérer via son ID
			PreparedStatement ps = connection.prepareStatement("UPDATE PRODUITS SET DESIGNATION=?, PRIX=?, QUANTITE=? WHERE ID=?");
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			ps.setLong(4, p.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	//implémentation de la méthode delete 
	@Override
	public void delete(Long id) {
		
		//ouverture de la connexion 
		Connection connection = SingletonConnection.getConnection();
		
		try {
			//la requête pour la suppression d'un produit connaissant l'ID
			PreparedStatement ps = connection.prepareStatement("DELETE FROM PRODUITS WHERE ID=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//création d'un méthode init qui sera appelé pour la vérification si l'injection des dépendances a reussit
	public void init() {
		System.out.println("Initialisation...........");
	}

}
