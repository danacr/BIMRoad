<%@page import="main.java.BIMRoad.Item,main.java.BIMRoad.Message,main.java.BIMRoad.User" %>
<%@ page import="main.java.BIMRoad.helpers" %>
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
%>
<div align="center" class="jumbotron">
    <div class="container">
        <h1 class="display-3">Hi <%=user.getName() %>
        </h1>
        <%
            //if user is admin
            if (user.getIsAdmin() == 1) {
        %>

        <p><a href="admin_home.jsp">Admin Home</a></p>

        <%
            }
        %>
        <p>Below you see your profile details.</p>
    </div>
</div>
<div class="container">
    <strong>Your Email</strong>: <%=user.getEmail() %>
    <br>
    <br>
    <h3>Your items</h3>
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
                ArrayList<Item> dbItems = Item.getItemsByUserid(user.getId());
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
                    <a href=editItemData.jsp?itemid=<%=tempItem.getId() %>>Edit</a>
                    <a href=viewItem.jsp?itemid=<%=tempItem.getId() %>>View</a>
                    <a href=deleteItem?id=<%=tempItem.getId() %>>Delete</a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <br>
    <br>
    <h3>Your messages</h3>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th>Sender</th>
                <th>Sender email</th>
                <th>content</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Message> dbMessages = Message.getMessagesByUsername(user.getName());
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
                <td><a href="addmessage.jsp?receiver=<%=tempMessage.getSender() %>">Reply</a> <a
                        href="deleteMessage?id=<%=tempMessage.getId() %>">Delete</a></td>
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