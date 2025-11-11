<%@page import="Model.Member"%>
<%@page import="Model.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ProductView.css">
        <style>
            .back-btn {
                background-color: #6c757d !important;
                color: white !important;
                border: none !important;
                padding: 10px 25px !important;
                border-radius: 6px !important;
                cursor: pointer !important;
                font-size: 16px !important;
                font-weight: bold !important;
                transition: background-color 0.3s ease, transform 0.2s ease !important;
            }

            .back-btn:hover {
                background-color: #5a6268 !important;
                transform: scale(1.05) !important;
            }
        </style>
        <title>Product View</title>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            Member customer = (Member) request.getSession().getAttribute("customer");
        %>
    </head>
    <body>
        <div class="container">

            <div class="page-header">
                <div class="header-top">
                    <h1>Electronics Supermarket</h1>
                    <form action="ShoppingCartView" method="get">
                        <input type="submit" value="🛒 View Cart" class="cart-btn">
                    </form>
                </div>
                <h2>Products for <%= customer.name%></h2>
            </div>

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Add to Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (products != null && !products.isEmpty()) {
                                for (Product p : products) {
                        %>
                        <tr>
                            <td><%= p.id%></td>
                            <td><%= p.name%></td>
                            <td><%= p.description%></td>
                            <td><%= p.sellingPrice%></td>
                            <td>
                                <form action="OrderProductView" method="get">
                                    <input type="hidden" name="id" value="<%= p.id%>">
                                    <input type="hidden" name="name" value="<%= p.name%>">
                                    <input type="hidden" name="description" value="<%= p.description%>">
                                    <input type="hidden" name="sellingPrice" value="<%= p.sellingPrice%>">
                                    <input type="hidden" name="purchasePrice" value="<%= p.purchasePrice%>">
                                    <input type="hidden" name="supplierName" value="<%= p.supplierName%>">
                                    <input type="submit" value="Add" class="add-btn">
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr><td colspan="5">No products available</td></tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>

            <div style="text-align:center; margin-top:20px;">
                <button type="button" class=" back-btn" onclick="history.back()">Back</button>
            </div>
        </div>
    </body>
</html>
