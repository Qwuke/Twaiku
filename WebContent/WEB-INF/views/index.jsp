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
<link rel="stylesheet"
	href="https://bootswatch.com/4/darkly/bootstrap.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Glegoo">
<link href="https://fonts.googleapis.com/css?family=Bungee+Shade"
	rel="stylesheet">


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
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<!-- <li class="nav-item">
                          <a class="nav-link" href="#">Pricing</a>
                            </li> -->
				<li class="nav-item"><a class="nav-link" href="about">About</a></li>
				<li>
			</ul>
			<form class="form-inline my-lg-0 pl-0 ml-0 mt-0 pt-0 mt-md-0">
				<input class="form-control mr-sm-2 text-right" type="text" placeholder="Coming soon!">
				<button class="btn btn-secondary my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	<div class="tweetColumns">
		<table class="table">
			<tbody>
				<c:forEach var="myVar" items="${tweetTable}">
					<tr scope=row>
						<div id="col1" class="tweet">
							<div class="person">
								<div class="fullData">
									<div class="profileImage">
										<a
											href="https://twitter.com/${myVar.userName}/status/${myVar.tweetID}"
											class="toAccount" target="_blank" title="Go To Profile">
											<img class="userIcon" src="${myVar.profileImageLink}"
											alt="Profile" />
										</a>
									</div>
									<div class="userInfo">
										<div class="userName">${myVar.userDisplayName}</div>
										<a href="https://twitter.com/${myVar.userName}">
											<div class="userHandle">@${myVar.userName}</div>
										</a>
									</div>
								</div>
								<div class="followDiv">
									<a href="https://twitter.com/TwaikuGC" class="toTwaiku"
										target="_blank"> <img class="twaikuLogo"
										src="<c:url value="/images/Ticon.png" />" alt="Twaiku"
										title="Follow Us!" />
									</a>
								</div>
							</div>
							<div class="text">
								<div class="textBody">${myVar.tweetString}</div>
								<!-- <div class="time">11:11 AM - 1 Jan 14</div> -->
							</div>
							<!-- <div class="retweet">51 retweets</div> -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
		${search}
	</div>


	<!-- <div id="col2" class="tweet">
            <div class="person">
                <div class="fullData">
                    <div class="profileImage">
                        <a href="#" class="toAccount" target="_blank" title="Go To Profile">
                            <img class="userIcon" src="https://fillmurray.com/50/50" alt="Profile" />
                        </a>
                    </div>
                    <div class="userInfo">
                        <div class="userName">Bill Murray</div>
                        <div class="userHandle">@bill</div>
                    </div>
                </div>
                <div class="followDiv">
                    <a href="https://twitter.com/TwaikuGC" class="toTwaiku" target="_blank">
                        <img class="twaikuLogo" src="./images/Ticon.PNG" alt="Twaiku" title="Follow Us!" />
                    </a>
                </div>
            </div>
            <div class="text">
                <div class="textBody">This is a real tweet.</div>
                <div class="time">11:11 AM - 1 Jan 14</div>
            </div>
            <div class="retweet">51 retweets</div>
        </div> -->
	
</body>

</html>