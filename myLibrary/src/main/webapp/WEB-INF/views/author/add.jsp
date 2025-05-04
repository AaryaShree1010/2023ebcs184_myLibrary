<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Author</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    <style>
        .error { color: red; }
    </style>
</head>
<body>
    <h1>Add New Author</h1>
    <form:form modelAttribute="author" method="post" action="${pageContext.request.contextPath}/author/add">
        <c:if test="${not empty globalError}">
            <div class="error">${globalError}</div>
        </c:if>
        <div>
            <form:label path="name">Name:</form:label>
            <form:input path="name"/>
            <form:errors path="name" cssClass="error"/>
        </div>
        <div>
            <form:label path="nationality">Nationality:</form:label>
            <form:input path="nationality"/>
            <form:errors path="nationality" cssClass="error"/>
        </div>
        <button type="submit" class="button">Save</button>
    </form:form>
    <a href="${pageContext.request.contextPath}/author" class="button">Back to List</a>
</body>
</html>