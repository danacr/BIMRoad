package BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {

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

        //user is not an admin send back to home
        if (user.getIsAdmin() != 1) {
            response.sendRedirect("home.jsp");
            return;
        } else {
            //get message id from URL (GET)
            int id = Integer.parseInt(request.getParameter("id"));
            User.deleteUser(id);

            response.sendRedirect("admin_home.jsp");
        }
    }
}