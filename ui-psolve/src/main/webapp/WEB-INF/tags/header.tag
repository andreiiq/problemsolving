<%@ tag language="java" pageEncoding="ISO-8859-1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="<c:url value="/resources/custom/css/header.css"/>"
          rel="stylesheet">
</head>
<nav class="navbar navbar-default col-lg-6 col-lg-offset-3">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand badger-brand" href="#"/>PBS</a>
        </div>
        <div class="collapse navbar-collapse" id="defaultNavbar1">
            <form:form id="header-search-form" class="navbar-form navbar-left form form-vertical" role="search"
                       method="get" action="/search">
                <div class="form-group">
                    <input id="header-search-input" name="name" list="search-results" type="text" class="form-control"
                           placeholder="Search for everything">
                    <datalist id="search-results">
                        <option value="cici"/>
                    </datalist>
                    <button type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-search" style="color: #5bc0de"
                      aria-hidden="true"></span>
                    </button>
                </div>
            </form:form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">About us</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown" role="button" aria-expanded="false">Settings<span
                        class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">My classrooms</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Privacy</a></li>
                        <li class="divider"></li>
                        <c:url value="/logout" var="logoutURL"/>
                        <li><form:form method="POST" action="${logoutURL}">
                            <button class="link-button" type="submit">Logout</input>
                        </form:form></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
</html>