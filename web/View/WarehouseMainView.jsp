<%@ page import="Model.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Warehouse Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/WarehouseMainView.css">
</head>
<body>
    <div class="container">
        <h1>Warehouse Management System</h1>
        
        <h3>
            <% Member warehouse = (Member) request.getAttribute("member"); %>
            Hello, <%= warehouse.name %>
        </h3>

        <div class="button-group">
            <a href="${pageContext.request.contextPath}/UpdateProductView" class="btn">Update Product</a>
        </div>
    </div>
</body>
</html>
