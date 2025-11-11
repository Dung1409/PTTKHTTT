<%@ page import="Model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Update Product Detail</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UpdateDetailProduct.css" />
        <style>
            .message {
                text-align: center;
                margin: 15px auto;
                padding: 10px 15px;
                width: 60%;
                border-radius: 8px;
                font-weight: bold;
            }
            .message.success {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }
            .message.error {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }
        </style>
    </head>
    <body>
        <%
            Product p = (Product) request.getAttribute("product");
            String message = (String) request.getAttribute("message");
        %>

        <div class="container">
            <h2>Update Product Detail</h2>

            <!-- ✅ Thông báo thành công hoặc lỗi -->
            <% if (message != null) {%>
            <div class="message <%= message.contains("successfully") ? "success" : "error"%>">
                <%= message%>
            </div>
            <% }%>

            <form action="${pageContext.request.contextPath}/UpdateDetailProduct" method="post">
                <input type="hidden" name="id" value="<%= p.id%>">

                <label>Product Name:</label>
                <input type="text" name="name" value="<%= p.name%>" required><br>

                <label>Supplier Name:</label>
                <input type="text" name="supplierName" value="<%= p.supplierName%>" required><br>

                <input type="hidden" name="purchasePrice" value="<%= p.purchasePrice%>">

                <label>Selling Price:</label>
                <input type="number" name="sellingPrice" step="0.01" value="<%= p.sellingPrice%>" required><br>

                <label>Description:</label>
                <input type="text" name="description" value="<%= p.description%>" required><br>

                <div class="button-group">
                    <button type="submit" name="action" value="save" class="save-btn">Update</button>
                    <a href="${pageContext.request.contextPath}/UpdateProductView" class="cancel-btn">Back</a>
                </div>
            </form>
        </div>
    </body>
</html>
