<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Member, Model.Receiver, Model.OrderProduct, java.util.List" %>
<html>
    <head>
        <title>Shopping Cart</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ShoppingCartView.css">
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
    </head>
    <body>

        <!-- 🧭 Header -->
        <div class="header">
            <h1>🛒 Shopping Cart</h1>
            <form action="${pageContext.request.contextPath}/ProductView" method="get">
                <input type="submit" class="back-btn" value="← Continue Shopping">
            </form>
        </div>

        <div class="cart-container">
            <%
                Member customer = (Member) request.getSession().getAttribute("customer");
                String receiverName = customer != null ? customer.name : "";
                String phoneNumber = customer != null ? customer.phoneNumber : "";
                String address = customer != null ? customer.address : "";
                List<OrderProduct> orders = (List<OrderProduct>) request.getAttribute("orders");
                Float totalPrice = 0f;
            %>

            <h2>Customer: <%= receiverName%></h2>

            <table>
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price (USD)</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (orders != null && !orders.isEmpty()) {
                            for (OrderProduct op : orders) {
                                float subtotal = op.sellingPrice * op.quanity;
                                totalPrice += subtotal;
                    %>
                    <tr>
                        <td><%= op.p.name%></td>
                        <td><%= op.quanity%></td>
                        <td><%= op.sellingPrice%></td>
                        <td><%= subtotal%></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4">No products in the shopping cart.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <p class="total-price"><strong>Total Price:</strong> <%= totalPrice%> USD</p>


            <form class="receiver-form" action="${pageContext.request.contextPath}/ShoppingCartView" method="post">
                <h3>Receiver Information</h3>
                <label for="receiverName">Receiver Name:</label>
                <input type="text" id="receiverName" name="receiverName" value="<%= receiverName%>" required>

                <label for="phoneNumber">Phone Number:</label>
                <input type="tel" id="phoneNumber" name="phoneNumber" value="<%= phoneNumber%>" required>

                <label for="address">Address:</label>
                <textarea id="address" name="address" rows="2" required><%= address%></textarea>

                <div class="action-buttons">
                    <input type="submit" name="order" value="Place Order">
                </div>
            </form>

        </div>
        <div style="text-align:center; margin-top:20px;">
            <button type="button" class=" back-btn" onclick="history.back()">Back</button>
        </div>
    </body>
</html>
