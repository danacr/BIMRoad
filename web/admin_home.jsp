<%@page import="BIMRoad.Item,BIMRoad.Message,BIMRoad.User" %>
<%@ page import="BIMRoad.helpers" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>

<%@include file="header.jsp" %>
<%
    HttpSession session1 = request.getSession(false);
    //check if user is logged in
    if (session1 == null || session1.getAttribute("User") == null) {
        response.sendRedirect("login.jsp?error=".concat(URLEncoder.encode("You need to be logged in", "UTF-8")));
    }
    try {
        //get session of logged in user
        User user = (User) session.getAttribute("User");

        //check if real admin
        if (user.getIsAdmin() != 1) {
            response.sendRedirect("home.jsp");
            return;
        }

%>
<div align="center" class="jumbotron">
    <div class="container">
        <h1 class="display-3">Hi <%=user.getName() %> (Admin)
        </h1>
        <p>This is the admin interface.</p>
    </div>
</div>
<div class="container">
    <br>
    <br>
    <h3>All items</h3>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th>Picture</th>
                <th>Item name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Item> dbItems = Item.getItems();
                for (int i = 0; i < dbItems.size(); i++) {
                    Item tempItem = dbItems.get(i);
            %>
            <tr>
                <th scope="row"><img height="60" src="image?id=<%=tempItem.getId() %>"/>
                </th>
                <td><a href=viewItem.jsp?itemid=<%=tempItem.getId() %>><%=tempItem.getItemname() %>
                </a>
                </td>
                <td>&euro;<%=helpers.doubleToTwoDecimals(tempItem.getPrice()) %>
                </td>
                <td><%=tempItem.getDescription() %>
                </td>
                <td>
                    <a href="deleteItem?id=<%=tempItem.getId() %>">Delete</a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <br>
    <br>
    <h3>All Users</h3>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Street</th>
                <th>Postalcode</th>
                <th>City</th>
                <th>Country</th>
                <th>Creation</th>
                <th>Admin</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<User> dbUsers = User.getUsers();
                for (int i = 0; i < dbUsers.size(); i++) {
                    User tempUser = dbUsers.get(i);
            %>
            <tr>
                <th scope="row"><%=tempUser.getId() %>
                </th>
                <td><%=tempUser.getName() %>
                </td>
                <td><%=tempUser.getEmail() %>
                </td>
                <td><%=tempUser.getStreet() %>
                </td>
                <td><%=tempUser.getPostalcode() %>
                </td>
                <td><%=tempUser.getCity() %>
                </td>
                <td><%=tempUser.getCountry() %>
                </td>
                <td><%=helpers.getDateFromTimestamp(tempUser.getCreationDate()) %>
                </td>
                <td><%=tempUser.getIsAdmin() %>
                </td>
                <td><a href="deleteUser?id=<%=tempUser.getId() %>">Delete</a>
                </td>
                <td><a href=editUserData.jsp?id=<%=tempUser.getId() %>>Edit</a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <br>
    <br>
    <h3>All Messages</h3>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th>Sender</th>
                <th>Sender email</th>
                <th>Content</th>
                <th>Receiver</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Message> dbMessages = Message.getMessages();
                for (int i = 0; i < dbMessages.size(); i++) {
                    Message tempMessage = dbMessages.get(i);
            %>
            <tr>
                <th scope="row"><%=tempMessage.getSender() %>
                </th>
                <td><%=tempMessage.getSenderEmail() %>
                </td>
                <td><%=tempMessage.getContent() %>
                </td>
                <td><%=tempMessage.getReceiver() %>
                </td>
                <td><a href="deleteMessage?id=<%=tempMessage.getId() %>">Delete</a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <br>
    <br>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<%@include file="footer.jsp" %>