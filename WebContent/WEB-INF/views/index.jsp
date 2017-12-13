<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Twaiku: a random sample of the human condition</title>
<link rel="stylesheet" href="https://bootswatch.com/4/darkly/bootstrap.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Glegoo">
</head>
<body style="font-family: 'Glegoo', serif !important">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary" style="height: 30px">
  <a class="navbar-brand mx-auto" href="#">Twaiku</a>
</nav>
<nav class="navbar navbar-expand-sm py-0 navbar-dark bg-primary">
  <!-- <a class="navbar-brand" href="#">Twaiku</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button> -->

		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav mx-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Recent</a></li>
				<!-- <li class="nav-item">
        		<a class="nav-link" href="#">Pricing</a>
      			</li> -->
				<li class="nav-item"><a class="nav-link" href="#">About</a></li>
				<li> 
			</ul>
			<form class="form-inline my-lg-0 pl-0 ml-0 mt-0 pt-0 mt-md-0">
      		<input class="form-control mr-sm-2" type="text" placeholder="Search">
      		<button class="btn btn-secondary my-sm-0" type="submit">Search</button>
    		</form>
		</div>
	</nav>
	<div class="container">
	<table class ="table table-hover">
		<tbody>
			<c:forEach var="myVar" items="${tweetTable}">
				<tr scope=row>
					<td><a href="https://twitter.com/${myVar.userName}">@${myVar.userName}</a></td>
					<td>${myVar.tweetString}</td>
					<td><a
						href="https://twitter.com/${myVar.userName}/status/${myVar.tweetID}">link
							to tweet</a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>