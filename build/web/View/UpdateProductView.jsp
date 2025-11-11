<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Update Product View</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UpdateProductView.css" />
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <h2 style="text-align:center;">Update Product</h2>

        <!-- ✅ Hiển thị thông báo alert -->
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <script>
            Swal.fire({
                icon: '<%= message.contains("successfully") ? "success" : "error"%>',
                title: '<%= message.contains("successfully") ? "Success" : "Error"%>',
                text: '<%= message%>',
                timer: 2500,
                showConfirmButton: false
            });
        </script>
        <%
            }
        %>

        <!-- Ô tìm kiếm -->
        <form method="get" action="UpdateProductView" style="text-align:center; margin-bottom:30px;">
            <input type="text" name="search" placeholder="Enter product name..." 
                   value="<%= request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : ""%>"/>
            <button type="submit">Search</button>
        </form>

        <!-- Bảng sản phẩm -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Product Name</th>
                    <th>Supplier Name</th>
                    <th>Purchase Price</th>
                    <th>Selling Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Product> productList = (List<Product>) request.getAttribute("products");
                    if (productList != null && !productList.isEmpty()) {
                        for (Product p : productList) {
                %>
                <tr>
                    <td><%= p.id%></td>
                    <td><%= p.name%></td>
                    <td><%= p.supplierName%></td>
                    <td><%= p.purchasePrice%></td>
                    <td><%= p.sellingPrice%></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/UpdateDetailProduct" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="<%= p.id%>">
                            <input type="hidden" name="name" value="<%= p.name%>">
                            <input type="hidden" name="description" value="<%= p.description%>">
                            <input type="hidden" name="supplierName" value="<%= p.supplierName%>">
                            <input type="hidden" name="purchasePrice" value="<%= p.purchasePrice%>">
                            <input type="hidden" name="sellingPrice" value="<%= p.sellingPrice%>">
                            <button type="submit">Edit</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6" style="text-align:center;">No products found</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <div style="text-align:center; margin-top:20px;">
            <button type="button" class="back-btn" onclick="history.back()">Back</button>
        </div>
    </body>
</html>
