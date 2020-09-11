	<!-- déclaration du taglib pour lutilisation de JSTL -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Gestion des produits</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>

<!-- inclusion de l'entête -->
	<%@include file="header.jsp" %>
	
	<div class="container col-md-8 col-md-offset-2 col-xs-12">
		<div class="panel panel-primary">
			<div class="panel-heading">Saisie d'un produit</div>
			<div class="panel-body">
				<form action="saveProduit.php" method="post">
					<div class="form-group">
						<label class="control-label">Désignation</label>
						<input  class="form-control" type="text" name="designation" value="${produit.designation}" required="required"/>
						<span></span>
					</div>
					<div class="form-group">
						<label class="control-label">Prix</label>
						<input  class="form-control" type="text" value="${produit.prix}" name="prix"/>
						<span></span>
					</div>
					<div class="form-group">
						<label class="control-label">Quantite</label>
						<input  class="form-control" type="text" value="${produit.quantite}" name="quantite"/>
						<span></span>
					</div>
					<div>	
						<button  class="btn btn-primary" type="submit">Enrégistrer</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>