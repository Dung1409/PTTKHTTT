<%@page import="Model.Member"%>
<%@page import="Model.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    
    <% 
        Member customer = (Member) request.getSession().getAttribute("customer"); 
        System.out.println(customer.name);
    %>
<head>
    <title>Product Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/OrderProductView.css" />
</head>

<body>
    <div class="container">
        <h2>Product Details</h2>
        <% Product p = (Product) request.getAttribute("product"); %>
        <p><strong>Name:</strong> <%=p.name%></p>
        <p><strong>Description:</strong> <%=p.description%></p>
        <p><strong>Supplier:</strong> <%=p.supplierName%></p>
        <p><strong>Selling Price:</strong> <%=p.sellingPrice%></p>

        <form action="OrderProductView" method="post">
            <div class="quantity-group">
                <label><strong>Quantity:</strong></label>
                <input type="number" name="quantity" value="1" min="1" />
                <input type="submit" value="Add to Cart">
            </div>
        </form>

        <div class="btn-row">
            <form action="ShoppingCartView" method="get">
                <input type="submit" value="View Shopping Cart" class="secondary-btn">
            </form>

            <form action="ProductView" method="get">
                <input type="submit" value="Back" class="back-btn">
            </form>
        </div>
    </div>
</body>
</html>
