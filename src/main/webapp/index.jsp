<%@page import="main.java.BIMRoad.Item,main.java.BIMRoad.helpers" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% HttpSession session1 = request.getSession(false);
    if (session1 == null || session1.getAttribute("User") == null) {
%>
<%@include file="headeri.jsp" %>
<% } else { %>
<%@include file="header.jsp" %>
<% }
%>
<div align="center" class="jumbotron">
    <div class="container">
        <h1 class="display-3">BIM Road</h1>
        <p>Below you find a list with all items for sale.</p>
    </div>
</div>
<div align="center" class="container">
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
                <td><a href=viewItem.jsp?itemid=<%=tempItem.getId()%>><%=tempItem.getItemname() %>
                </a></td>
                <td>&euro;<%=helpers.doubleToTwoDecimals(tempItem.getPrice()) %>
                </td>
                <td><%=tempItem.getDescription() %>
                </td>
                <td><a href=viewItem.jsp?itemid=<%=tempItem.getId() %>> View </a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
<%@include file="footer.jsp" %>

