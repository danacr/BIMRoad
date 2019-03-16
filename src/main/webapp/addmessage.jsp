<%@page import="main.java.BIMRoad.User" %>
<%@ page import="java.net.URLEncoder" %>
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
        User user = (User) session.getAttribute("User");
%>
<div align="center" class="jumbotron">
    <div class="container">
        <h1 class="display-3">Send message</h1>
        <p>Below you can send a message to another user.</p>
    </div>
</div>
<div class="container">
    <form action="addmessage" method="post">
        <%
            if (request.getParameter("error") != null) {
        %>
        <div class="alert alert-danger">
            <strong>Error!</strong> <%= request.getParameter("error") %>
        </div>
        <%
            }
        %>
        <%
            if (request.getParameter("success") != null) {
        %>
        <div class="alert alert-success">
            Message sent successfully.
        </div>
        <%
            }
        %>
        <div class="form-group">
            <label>Receiver</label>
            <% if (request.getParameter("receiver") != null) {
            %>
            <input type="text" class="form-control" name="receiver" value="<%= request.getParameter("receiver") %>">
            <% } else {
            %>
            <input type="text" class="form-control" name="receiver">
            <% } %>
        </div>
        <div class="form-group">
            <label>Message content</label>
            <textarea class="form-control" name="content" rows="3"></textarea>
        </div>
        <input type="hidden" name="senderemail" value="<%=user.getEmail() %>">
        <input type="hidden" name="sender" value="<%=user.getName() %>">
        <input type="submit" class="btn btn-primary" value="Send">
        <br>
    </form>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<%@include file="footer.jsp" %>