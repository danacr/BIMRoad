<%@page import="BIMRoad.Item,BIMRoad.User" %>
<%@ page import="BIMRoad.helpers" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% HttpSession session1 = request.getSession(false);
    if (session1 == null || session1.getAttribute("User") == null) {
%>
<%@include file="headeri.jsp" %>
<% } else { %>
<%@include file="header.jsp" %>
<% }
    try {
%>
<div align="center" class="jumbotron">
    <div class="container">
        <h1 class="display-3">Item details</h1>
        <p>Below you find the details about this item</p>
    </div>
</div>
<div align="center" class="container">
    <%
        int itemid = Integer.parseInt(request.getParameter("itemid"));

        Item dbItem = helpers.getItemById(itemid);
    %>

    <p><img width="200px" src="image?id=<%=dbItem.getId() %>"/>
    </p>
    <p><strong>Name</strong>: <%=dbItem.getItemname() %>
    </p>
    <p><strong>Description</strong>: <%=dbItem.getDescription() %>
    </p>
    <p><strong>Price</strong>: &euro;<%=helpers.doubleToTwoDecimals(dbItem.getPrice()) %>
    </p>
    <p><strong>Date added</strong>: <%=helpers.getDateFromTimestamp(dbItem.getCreationDate()) %>
    </p>
    <% if (dbItem.getBid() != 0) { %>
    <p><strong>Highest bid</strong>: &euro;<%=helpers.doubleToTwoDecimals(dbItem.getBid()) %>
        by <%=helpers.getUserById(dbItem.getBidderid()).getName() %>
    </p>
    <%
        }

        //if user is logged in
        if (session1 != null && session1.getAttribute("User") != null) {
            User user = (User) session.getAttribute("User");

            User seller = helpers.getUserByItemId(dbItem.getId());
    %>
    <p><strong>Seller</strong>: <%=seller.getName() %> <a
            href="addmessage.jsp?receiver=<%=seller.getName() %>">Message</a>
    </p>
    <form action="bidItemServlet" enctype="multipart/form-data">
        <div class="form-group">
            <label>Bid amount</label>
            <input type="number" min="0.01" step="any" class="form-control" name="bidamount" required>
        </div>
        <input type="hidden" name="itemid" value="<%=itemid%>">
        <input type="hidden" name="userid" value="<%=user.getId()%>">
        <input type="submit" class="btn btn-primary" value="Submit">
    </form>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</div>
<%@include file="footer.jsp" %>