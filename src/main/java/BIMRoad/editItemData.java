package main.java.BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editItemData")
public class editItemData extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //check if user is logged in
        HttpSession session = request.getSession(true);
        if (session == null || session.getAttribute("User") == null) {
            System.out.println("No access, not logged in");
            response.sendRedirect("login.jsp?error=You+are+not+logged+in");
            return;
        }
        //get user details from session
        User user = (User) session.getAttribute("User");

        String what = request.getParameter("what");
        String value = request.getParameter("value");
        int itemid = Integer.parseInt(request.getParameter("itemid"));

        if (what.equals("price")) {
            if (!helpers.isNumeric(value)) {
                //not numeric we're not updating database
                response.sendRedirect("home.jsp");
                return;
            }
        }

        //update Item
        Item.updateItem(what, value, itemid, user.getId());

        response.sendRedirect("home.jsp");
    }
}