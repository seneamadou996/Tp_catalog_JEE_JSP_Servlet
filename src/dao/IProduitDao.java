package dao;

import java.util.List;

import metier.entities.Produit;

public interface IProduitDao {

	//d�claration des m�thodes fonctionnelles
	public Produit save(Produit p);
	public List<Produit> produitsParMC(String mc);
	public Produit getProduit(Long id);
	public Produit update(Produit p);
	public void delete(Long id);
}
