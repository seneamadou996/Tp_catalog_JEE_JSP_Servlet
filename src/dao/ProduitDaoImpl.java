package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.entities.Produit;

//impl�mentation de l'interface IProduitDao
public class ProduitDaoImpl implements IProduitDao{

	//impl�mentation de la m�thode de sauvegarde des produits
	@Override
	public Produit save(Produit p) {
		
		//ouverture d'une nouvelle connexion
		Connection connection = SingletonConnection.getConnection();
		
		try {
			
			//pr�parer la requ�te ps pour l'isertion des produit dans la base de donn�es
			PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUITS (DESIGNATION, PRIX, QUANTITE) VALUES(?,?,?)");
			
			//Transmission des param�tres a la requ�te
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			
			//execution de la requ�te
			ps.executeUpdate();
			
			//envoie dune requ�te pour la r�cup�ration de l'id
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(ID) AS MAX_ID FROM PRODUITS");
			ResultSet rs = ps2.executeQuery();
			
			//r�cup�ration du r�sultat
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

	//impl�mentation de la m�thode pour la recherche de produit par mot cl�
	@Override
	public List<Produit> produitsParMC(String mc) {
		//cr�ation d'une nouvelle liste de produit
		List<Produit> produits=new ArrayList<Produit>();
		
		//ouvrir la connexion
		Connection connection  = SingletonConnection.getConnection();
		
		try {
			
			//pr�paration de la requ�te pour la r�cup�ration des produit par d�signation
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE DESIGNATION LIKE ?");
			
			//transmission du param�tre mot cl� mc
			ps.setString(1, mc);
			
			//ex�cution de la requ�te
			ResultSet rs = ps.executeQuery();
			
			//r�cup�ration du r�sultat
			while(rs.next()) {
				
				//stockage du r�sultat dans le model
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

	//impl�mentation de la m�thode pour la r�cupration d'un produit par ID 
	@Override
	public Produit getProduit(Long id) {
		//instanciation de la class Produit
		Produit p= null;
		
		//ouverture de la connexion
		Connection connection  = SingletonConnection.getConnection();
		
		try {
			//requ�te pour la r�cup�ration d'un produit connaissant l'ID
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			//r�cup�ration du r�sultat obtenue
			while(rs.next()) {
				
				//stockage du r�sultat dans le model
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

	//impl�mentation de la m�thode update
	@Override
	public Produit update(Produit p) {
		
		//ouverture de la connexion
		Connection connection = SingletonConnection.getConnection();
		
		try {
			//requ�te pour la mis � jour du produit r�cup�rer via son ID
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

	//impl�mentation de la m�thode delete 
	@Override
	public void delete(Long id) {
		
		//ouverture de la connexion 
		Connection connection = SingletonConnection.getConnection();
		
		try {
			//la requ�te pour la suppression d'un produit connaissant l'ID
			PreparedStatement ps = connection.prepareStatement("DELETE FROM PRODUITS WHERE ID=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//cr�ation d'un m�thode init qui sera appel� pour la v�rification si l'injection des d�pendances a reussit
	public void init() {
		System.out.println("Initialisation...........");
	}

}
