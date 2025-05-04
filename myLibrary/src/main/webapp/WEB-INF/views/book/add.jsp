<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <h1>Add New Book</h1>
    <form:form modelAttribute="book" method="post" action="${pageContext.request.contextPath}/book/add">
        <form:errors path="*" cssClass="error"/>
        <div>
            <form:label path="title">Title:</form:label>
            <form:input path="title"/>
            <form:errors path="title" cssClass="error"/>
        </div>
        <div>
            <form:label path="isbn">ISBN:</form:label>
            <form:input path="isbn"/>
            <form:errors path="isbn" cssClass="error"/> <!-- Added for validation -->
        </div>
        <div>
            <form:label path="author">Author:</form:label>
            <form:select path="author">
                <form:options items="${authors}" itemValue="id" itemLabel="name"/>
            </form:select>
            <form:errors path="author" cssClass="error"/> <!-- Added for validation -->
        </div>
        <button type="submit" class="button">Save</button>
    </form:form>
    <a href="${pageContext.request.contextPath}/book" class="button">Back to List</a>
</body>
</html>