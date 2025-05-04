<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Authors List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <h1>Authors</h1>
    <a href="${pageContext.request.contextPath}/author/add">Add New Author</a>
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">${errorMessage}</div>
    </c:if>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Nationality</th>
                <th>Books</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty authors}">
                <tr>
                    <td colspan="5">No authors found. Click "Add New Author" to create one.</td>
                </tr>
            </c:if>
            <c:forEach items="${authors}" var="author">
                <tr>
                    <td>${author.id}</td>
                    <td>${author.name}</td>
                    <td>${author.nationality}</td>
                    <td>
                        <c:forEach items="${author.books}" var="book">
                            ${book.title}<br>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/author/update/${author.id}" class="button">Edit</a>
                        &nbsp;|&nbsp;
                        <a href="${pageContext.request.contextPath}/author/delete/${author.id}" 
                           onclick="return confirm('Are you sure you want to delete this author?')" class="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>