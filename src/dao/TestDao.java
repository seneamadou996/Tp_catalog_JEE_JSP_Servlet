package dao;

import java.util.List;

import metier.entities.Produit;

//creation d'un m�thode test pour tester la couche dao
public class TestDao {

	public static void main(String[] args) {
		
		//instanciation de la class ProduitDaoImpl
		ProduitDaoImpl dao = new ProduitDaoImpl();
		
		//cr�ation de nouveaux produits
		Produit p1 = dao.save(new Produit("HP 6580", 1600, 10));
		Produit p2 = dao.save(new Produit("Imprimante Epson 760", 1200, 15));
		
		//affichage de l'ensemble produit utilisant la m�thode toString
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		
		//affichage des produit par mot cl�
		System.out.println("Chercher des produits");
		List<Produit> produits = dao.produitsParMC("%H%");
		for(Produit p : produits) {
			System.out.println(p.toString());
		}
	}

} 
