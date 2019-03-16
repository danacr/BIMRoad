<%@page import=" BIMRoad.Item,BIMRoad.helpers,java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>
<%@include file="header.jsp" %>
<%
    HttpSession session1 = request.getSession(false);
    //check if user is logged in
    if (session1 == null || session1.getAttribute("User") == null) {
        response.sendRedirect("login.jsp?error=".concat(URLEncoder.encode("You need to be logged in", "UTF-8")));
    }
%>
<div align="center" class="jumbotron">
    <div class="container">
        <h1 class="display-3">Edit item details</h1>
        <p>Below you can edit your item for sale details.</p>
    </div>
</div>
<div class="container">
    <%
        try {
            int itemid = Integer.parseInt(request.getParameter("itemid"));
            Item dbItem = Item.getItemById(itemid);
    %>
    <p><img width="200px" src="image?id=<%=dbItem.getId() %>"/>
    </p>
    <p><strong>Name</strong>: <%=dbItem.getItemname() %>
    </p>
    <p><strong>Description</strong>: <%=dbItem.getDescription() %>
    </p>
    <p><strong>Price</strong>: <%=helpers.doubleToTwoDecimals(dbItem.getPrice()) %>
    </p>
    <br>
    <form action="editItemData" enctype="multipart/form-data">
        <div class="form-group">
            <label>What do you want to edit?</label>
            <select name="what" class="form-control">
                <option value="itemname">Name</option>
                <option value="description">Description</option>
                <option value="price">Price</option>
            </select>
        </div>

        <strong>Value</strong>: <input type="text" name="value" required="required">
        <input type="hidden" name="itemid" value="<%=dbItem.getId() %>">
        <input type="submit" class="btn btn-primary" value="Submit change">
    </form>
    <br>
    <br>
    <%
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</div>
<%@include file="footer.jsp" %>