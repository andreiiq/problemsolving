<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="badger"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Badger</title>
<!-- Bootstrap -->
<link href="<c:url value="/resources/web/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/bootstrap-social.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/font-awesome.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/web/css/docs.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/web/js/jquery-1.11.2.min.js"/>"></script>
<script src="<c:url value="/resources/web/js/bootstrap.js"/>"></script>

<link href="<c:url value="/resources/custom/css/quiz.css"/>"
	rel="stylesheet">
</head>
<body>
	<badger:header />
	<div class="container">
		<div class="row text-center">
			<div class="col-md-6 col-md-offset-3">
				<h2>${quiz.name}</h2>
				<c:url value="/checkQuiz" var="checkQuizURL" />
				<form:form action="${checkQuizURL}" method="POST">
					<input type="hidden" name="quizID" value="${quiz.id}" />
					<c:forEach items="${quiz.questions}" var="question">
						<div>
							<h3 class="text-left">${question.questionText}</h3>
							<p class="text-left">
								<c:forEach items="${question.answers}" var="answer">
									<%-- <form:input path="fooList[${i.index}].name" type="text" /> --%>
									<label> <input type="radio" name="${question.id}"
										value="${answer.id}" required/> ${answer.answerText}
									</label>
									<br>
								</c:forEach>
								<br>
							</p>
						</div>
					</c:forEach>
					<div class="col-md-6 col-md-offset-3">
						<button class="btn btn-primary btn-lg" type="submit">Submit</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<badger:footer />
</body>
</html>