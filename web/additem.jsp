<%@page import="Marketplace.User" %>
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
        <h1 class="display-3">Hi <%=user.getName() %>
        </h1>
        <p>Below you can create an new add for the item you want to sell.</p>
    </div>
</div>
<div class="container">
    <form method="post" action="additem" enctype="multipart/form-data">
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
            Item added successfully.
        </div>
        <%
            }
        %>
        <div class="form-group">
            <label>What do you want to sell?</label>
            <input type="text" class="form-control" name="itemname" required="required">
        </div>

        <div class="form-group">
            <label>How much do you want to sell it for? (&euro;)</label>
            <input type="number" min="0.01" step="any" class="form-control" name="price" required="required">
        </div>
        <div class="form-group">
            <label>Please provide a description of the item</label>
            <textarea class="form-control" name="description" rows="3" required="required"></textarea>
        </div>

        <div class="form-group">
            <label>Image upload</label>
            <input type="file" name="image" class="form-control-file" aria-describedby="fileHelp">
            <small id="fileHelp" class="form-text text-muted">Select a picture to go with your ad.</small>
        </div>

        <input type="hidden" name="userid" value="<%=user.getId() %>">

        <button type="submit" value="Add" class="btn btn-primary">Submit</button>
    </form>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<%@include file="footer.jsp" %>