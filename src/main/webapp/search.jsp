<%@page import="BIMRoad.Item,BIMRoad.helpers" %>
<%@ page import="java.util.ArrayList" %>


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
    <div align="center" class="container">
        <h1 class="display-3">You searched for <%=request.getParameter("itemname")%>
        </h1>
        <p>Below you can find a list that matches this search term.</p>
    </div>
</div>
<div class="container">
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
                String itemname = request.getParameter("itemname");
                ArrayList<Item> dbItems = Item.getItemsBySearch(itemname);
                for (int i = 0; i < dbItems.size(); i++) {
                    Item tempItem = dbItems.get(i);
            %>
            <tr>
                <th scope="row"><img height="60" src="image?id=<%=tempItem.getId() %>"/>
                </th>
                <td><a href=viewItem.jsp?itemid=<%=tempItem.getId() %>><%=tempItem.getItemname() %>
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
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<%@include file="footer.jsp" %>