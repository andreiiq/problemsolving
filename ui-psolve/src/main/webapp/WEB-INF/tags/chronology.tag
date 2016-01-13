<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ attribute name="userEvents" required="true" type="java.util.List"%>

<c:url value="/post" var="postCommentURL" />
<form:form id="commentForm" action="${postCommentURL}" method="POST">
	<div class="row post-comment">
		<div class="text-justify col-lg-12">
			<div class="input-group">
				<textarea name="message" class="form-control custom-control"
					rows="4" style="resize: none"></textarea>
				<span onClick="commentForm.submit()"
					class="input-group-addon btn btn-primary"> Post</span>
			</div>
		</div>
	</div>
</form:form>

	<c:forEach items="${userEvents}" var="event">
			<c:if test="${event.getEventKind() == 'POST'}">
				<div class="col-sm-4 text-justify col-lg-12 well">
					<p>${event.getMessage()}</p>


					Posted by <a href="#"><strong>${event.getUser().getFirstname()}
							${event.getUser().getLastname()}</strong> </a> on <strong>${event.getTimestamp()}</strong>
				</div>
			</c:if>
			<c:if test="${event.getEventKind() == 'QUIZ_COMPLETION'}">
				<div class="col-sm-4 text-justify col-lg-12 well">
					<a href="#"><strong>${event.getUser().getFirstname()}
							${event.getUser().getLastname()}</strong></a> completed the quiz for <a
						href="classroom/${event.getQuiz().getCourse().getClassroom().getId()}/${event.getQuiz().getCourse().getId()}"><strong>${event.getQuiz().getCourse().getName()}</strong></a>
					on <strong>${event.getTimestamp()}</strong>
				</div>
			</c:if>
		</c:forEach>