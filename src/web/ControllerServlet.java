package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dao.IProduitDao;
import dao.ProduitDaoImpl;
import metier.entities.Produit;

//publication de notre servlet
@WebServlet(name="cs",urlPatterns = {"*.php"})

public class ControllerServlet extends HttpServlet{
	
	//declaration d'une variable de type IProduitDao
	private IProduitDao metier;
	
	//impl�mentation de la methode init de servlet
	@Override
	public void init() throws ServletException {
		//injection des dependances utilisant l'ApplicationContext avec le framework spring 
		
		//metier = new ProduitDaoImpl();
		ApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		//metier = (IProduitDao)springContext.getBean("dao");
		metier = springContext.getBean(IProduitDao.class);
	}
	
	//impl�ment de la m�thode doGet
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//r�cup�ration des url envoy�s pour l'utilisateur
		String path = request.getServletPath();
		
		//tester si l'url r�cup�r� est index.php
		if(path.equals("/index.php")) {
			
			//on fait un forword vers la vue produits.jsp
			request.getRequestDispatcher("produits.jsp").forward(request, response);
		}
		
		//tester si l'url r�cup�r� est chercher.php
		else if(path.equals("/chercher.php")) {
			
			//on recup�re le mot cl� contenu dans la requ�te
			String motCle=request.getParameter("motCle");
		
			//instanciation du model
			ProduitModel model=new ProduitModel();
			
			//stockage du mot cl� dans le model
			model.setMotCle(motCle);
			
			//on fait appel � la couche metier
			List<Produit> produits = metier.produitsParMC("%"+motCle+"%");
			
			//on stock le resultat dans le model 
			model.setProduits(produits);
			
			//on stock le model dans l'objet request
			request.setAttribute("model", model);
			
			//on fait un forword vers la vue produits.jsp
			request.getRequestDispatcher("produits.jsp").forward(request, response);
		}
		
		//tester si l'url r�cup�r� est saisie.php
		else if(path.equals("/saisie.php")) {
			
			//instanciation du model
			request.setAttribute("produit", new Produit());
			
			//on fait un forword vers la vue saisieProduit.jsp
			request.getRequestDispatcher("saisieProduit.jsp").forward(request, response);
		}
		
		//tester si l'url r�cup�r� est SaveProduit.php et que la m�thode est post
		else if(path.equals("/saveProduit.php")&&(request.getMethod().equals("POST"))) {
			
			//r�cup�ration des donn�es envoy�es par le formulaire
			String des=request.getParameter("designation");
			double prix=Double.parseDouble(request.getParameter("prix"));
			int quantite=Integer.parseInt(request.getParameter("quantite"));
			
			//on fait appel � la couche metier
			Produit p = metier.save(new Produit(des, prix, quantite));
			
			//on stock le model dans l'objet request
			request.setAttribute("produit", p);
			
			//on fait un forword vers la vue confirme.jsp
			request.getRequestDispatcher("confirme.jsp").forward(request, response);
		}
		
		//tester si l'url r�cup�r� est supprime.php
		else if(path.equals("/supprime.php")) {
			
			//r�cup�ration de l'ID	du produit selectionn�
			Long id=Long.parseLong(request.getParameter("id"));
			
			//on fait appel � la couche metier
			metier.delete(id);
			
			//on effectue une redirection vers la vue chercher.php avec le parametre motCle
			
			//request.getRequestDispatcher("produits.jsp").forward(request, response);
			response.sendRedirect("chercher.php?motCle=");
		}
		
		//tester si l'url r�cup�r� est edit.php
		else if(path.equals("/edit.php")) {
			
			//recuperation de l'ID
			Long id = Long.parseLong(request.getParameter("id"));
			
			//on fait appel � la couche metier
			Produit p = metier.getProduit(id);
			
			//on stock le model dans l'objet request
			request.setAttribute("produit", p);
			
			//on fait un forword vers la vue editProduit.jsp
			request.getRequestDispatcher("editProduit.jsp").forward(request, response);
		}
		
		//tester si l'url r�cup�r� est updateProduit.php et que la m�thode est post
		else if(path.equals("/updateProduit.php")&&(request.getMethod().equals("POST"))) {
			
			//recuperation des donn�es du formulaire
			Long id=Long.parseLong(request.getParameter("id"));
			String des=request.getParameter("designation");
			double prix=Double.parseDouble(request.getParameter("prix"));
			int quantite=Integer.parseInt(request.getParameter("quantite"));
			
			//injection de ces donn�es dans l'objet Produit 
			Produit p = new Produit(des, prix, quantite);
			
			//injection de l'ID
			p.setId(id);
			
			//appel � la couche metier
			metier.update(p);
			
			//stockage du model dans l'objet request 
			request.setAttribute("produit", p);
			
			//on fait un forword vers la vue confirme.jsp
			request.getRequestDispatcher("confirme.jsp").forward(request, response);
		}
		
		//tester si l'url r�cup�r� ne correspond � aucune des m�hode cr�er
		else {
			
			//on gen�re un exception de type not foud avec l'ojet de l'objet respnse  
			response.sendError(Response.SC_NOT_FOUND);
		}
	}
	
	//impl�mentation de la m�thode doPost
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//on fait appel � la m�thode doGet
		doGet(request, response);
	}

}
