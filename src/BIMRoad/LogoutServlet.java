package BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Check if there is a session
        HttpSession session = request.getSession(true);

        //If there is no session
        if (session == null || session.getAttribute("User") == null) {
            System.out.println("No access, not logged in");

            //Redirect user to ERROR page
            response.sendRedirect("login.jsp?error=You+are+not+logged+in");
            return;
        }

        //If there is a session, define a new array of cookies and fill with the cookies
        Cookie[] cookies = request.getCookies();

        //Create a for loop to close all cookies until cookies = null
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID=" + cookie.getValue());
                    break;
                }
            }
        }

        //invalidate the session if exists
        HttpSession session1 = request.getSession(false);
        System.out.println("User=" + session1.getAttribute("User"));
        if (session1 != null) {
            session1.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

}
