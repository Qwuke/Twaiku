<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="icon" href="<c:url value="/images/Ticon.png" />" />
<link rel="stylesheet" href="https://bootswatch.com/4/darkly/bootstrap.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Glegoo">
<link href="https://fonts.googleapis.com/css?family=Bungee+Shade" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="/resources/styles.css" />" />

<title>Twaiku: A Random Sample of the Human Condition</title>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"
		style="height: 50px">
		<a class="navbar-brand mx-auto" href="#"
			style="font-family: 'Bungee Shade', cursive; font-size: 1.75em">Twaiku</a>
	</nav>
	<nav class="navbar navbar-expand-sm py-0 navbar-dark bg-primary">
		<!-- <a class="navbar-brand" href="#">Twaiku</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button> -->

		<div class="collapse navbar-collapse" id="navbarColor01"
			style="padding-left: 23%; padding-bottom: 5px">
			<ul class="navbar-nav mx-auto" style="font-size: 18px">
				<li class="nav-item"><a class="nav-link" href="index">Home
				</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">About
						<span class="sr-only">(current)</span>
				</a></li>
				<li>
			</ul>
			<form class="form-inline my-lg-0 pl-0 ml-0 mt-0 pt-0 mt-md-0">
				<input class="form-control mr-sm-2 text-right" type="text" placeholder="Coming soon!">
				<button class="btn btn-secondary my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	
      <div class="container marketing" style="color: black">
      <br>
      <br>
      <br>
      <h1 class="text-center">About Twaiku</h1>
      	<div class="container tweet text-center">
      		<h2 class="text-center">Twaiku is a natural language processing project that identifies accidental haikus from a random sample of the public Twitter stream.</h2>
      		<h2>It was made by coders who love finding poetry in everyday life.</h2>
      		<h2>If you want to contribute to Twaiku, star us on <a href="https://github.com/Qwuke/Twaiku">Github</a> or follow us on <a href="https://twitter.com/TwaikuGC">Twitter</a> to fuel motivation for development!</h2>
      		<br>
      		
      		<h2>Twaiku was made with love by</h2>
      	</div>
        <!-- Three columns of text below -->
        <div class="row text-center">
          <div class="col-lg-4">
            <img class="rounded-circle" src="https://media.licdn.com/media/AAIABADGAAAAAQAAAAAAAAwJAAAAJGI5YTBiYjdhLWVlNWMtNGJjZi1iMWQ3LTNkYWRjZjE3YzVlMA.jpg" alt="Generic placeholder image" width="180" height="180">
            <h3 class="megadog">Matthew Davis</h3>
          </div><!-- /.col-lg-4 -->
          <div class="col-lg-4">
            <img class="rounded-circle" src="https://www.grandcircus.co/wp-content/uploads/2017/11/IMG_2723e.png" alt="Generic placeholder image" width="180" height="180">
            <h3 class="megadog">Marcus Perez</h3>
          </div><!-- /.col-lg-4 -->
          <div class="col-lg-4">
            <img class="rounded-circle" src="https://www.grandcircus.co/wp-content/uploads/2017/11/IMG_2727e.png" alt="Generic placeholder image" width="180" height="180">
            <h3 class="megadog">Tristan Mortimer</h3>
          </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->
     </div>
	
		
</body>

</html>