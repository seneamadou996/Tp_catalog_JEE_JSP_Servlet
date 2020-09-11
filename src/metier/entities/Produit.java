package metier.entities;

import java.io.Serializable;

public class Produit implements Serializable{
	
	//déclaration des attributs
	private Long id;
	private String designation;
	private double prix;
	private int quantite;
	
	//constructeur sans paramètres
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructeur avec paramètres
	public Produit(String designation, double prix, int quantite) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	}

	// Getteurs et Setteurs
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	// la méthode toString
	@Override
	public String toString() {
		return "Produit [id=" + id + ", designation=" + designation + ", prix=" + prix + ", quantite=" + quantite + "]";
	}
	
}
