<%@page import="BIMRoad.User,java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>

<%@include file="header.jsp" %>
<div align="center" class="jumbotron">
    <div class="container">

        <%
            HttpSession session1 = request.getSession(false);
            //check if user is logged in
            if (session1 == null || session1.getAttribute("User") == null) {
                response.sendRedirect("login.jsp?error=".concat(URLEncoder.encode("You need to be logged in", "UTF-8")));
            }
            try {
                //get user from session
                User user = (User) session.getAttribute("User");
                //get additional details from database

                User dbUser = null;

                if (user.getIsAdmin() == 1) {

                    // If Admin clicked on Edit Profile
                    if (request.getParameter("id") == null) {
                       dbUser = User.getUserById(user.getId());
                    } else {
                    dbUser = User.getUserById(Integer.parseInt(request.getParameter("id")));
                    }
         %>
        <h1 class="display-3">Edit user <%=dbUser.getName() %>
        </h1>
        <p>Below you can edit the profile details of user <%=dbUser.getName() %>.</p>
        <%
        } else {
            dbUser = User.getUserById(user.getId());
        %>
        <h1 class="display-3">Edit your details</h1>
        <p>Below you can edit your profile details.</p>
        <%
            }
        %>

    </div>
</div>
<div class="container">

    <p><strong>Name</strong>: <%=dbUser.getName() %>
    </p>
    <p><strong>Email</strong>: <%=dbUser.getEmail() %>
    </p>
    <p><strong>Street</strong>: <%=dbUser.getStreet() %>
    </p>
    <p><strong>Postal code</strong>: <%=dbUser.getPostalcode() %>
    </p>
    <p><strong>City</strong>: <%=dbUser.getCity() %>
    </p>
    <p><strong>Country</strong>: <%=dbUser.getCountry() %>
    </p>
    <p><strong>Password</strong>: <%=dbUser.getPassword() %>
    </p>

    <form action="editUserData" enctype="multipart/form-data">
        <%
            if (request.getParameter("error") != null) {
        %>
        <div class="alert alert-danger">
            <strong>Error!</strong> <%= request.getParameter("error") %>
        </div>
        <%
            }
        %>
        <div class="form-group">
            <label for="exampleSelect1">What do you want to change?</label>
            <select name="what" class="form-control" id="exampleSelect1">
                <option value="email">Email</option>
                <option value="street">Street</option>
                <option value="postalcode">Postal code</option>
                <option value="city">City</option>
                <option value="country">Country</option>
                <option value="password">Password</option>
            </select>
        </div>
        <div class="form-group">
            <label>Value</label>
            <input type="text" class="form-control" name="value">
        </div>
        <input type="hidden" name="userid" value="<%=dbUser.getId()%>">
        <input type="submit" class="btn btn-primary" value="Submit change">
    </form>
</div>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<%@include file="footer.jsp" %>