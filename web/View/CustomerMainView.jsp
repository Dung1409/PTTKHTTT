<%@page import="Model.Member"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Main View</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/CustomerMainView.css">
</head>
<body>
    <div class="container">
        <h1> Electronics Supermarket </h1>
        <%
            Member customer = (Member) request.getAttribute("member");
            request.getSession().setAttribute("customer", customer);
        %>
        <h2>Welcome, <%= customer.name %> 👋</h2>

        <div class="button-group">
            <a href="${pageContext.request.contextPath}/ProductView" class="btn">🛒 Order Online</a>
            <a href="${pageContext.request.contextPath}/ShoppingCartView" class="btn">🧾 Shopping Cart</a>
        </div>
    </div>
</body>
</html>
