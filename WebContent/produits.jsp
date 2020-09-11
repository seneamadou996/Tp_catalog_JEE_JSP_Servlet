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
	
	<div class="container col-md-10 col-md-offset-1 col-xs-12">
		<div class="panel panel-primary">
			<div class="panel-heading">Liste des produits</div>
			<div class="panel-body">
				<form action="chercher.php" method="get">
					<div class="form-group">
						<label>Mot Clé:</label>
						<input type="text" name="motCle" value="${model.motCle}"/>
						<button typ="submit" class="btn btn-primary">Chercher</button>
					</div>
				</form>
				<table class="table table-striped">
					<tr>
						<th>ID</th><th>DESIGNATION</th><th>PRIX</th><th>QUANTITE</th>
					</tr>
					<c:forEach items="${model.produits}" var="p">
						<tr>
							<td>${p.id}</td>
							<td>${p.designation}</td>
							<td>${p.prix}</td>
							<td>${p.quantite}</td>
							<td><a onclick="return confirm('Etes vous sûre?')" href="supprime.php?id=${p.id}">Supprimer</a></td>
							<td><a href="edit.php?id=${p.id}">Editer</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>