package BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;

@WebServlet("/editUserData")
public class editUserData extends HttpServlet {

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
        int userid = Integer.parseInt(request.getParameter("userid"));
        PreparedStatement ps = null;

        //if user we want to edit is not the logged in user
        if (userid != user.getId()){
            response.sendRedirect("editUserData.jsp?error=Not+authorized.");
            return;
        }

        if((what).equals("email")){
            if(!helpers.isValidEmail(value)){
                response.sendRedirect("editUserData.jsp?error=This+is+not+a+valid+email+address.");
                return;
            }
            if(helpers.doesEmailExists(value)){
                response.sendRedirect("editUserData.jsp?error=A+user+already+exists+with+this+email+address.");
                return;
            }
        }

        helpers.updateUser(what, value, userid);

        //update session
        User dbUser = helpers.getUserById(user.getId());
        User newSessionUser = new User(dbUser.getId(), dbUser.getName(), dbUser.getEmail(), dbUser.getIsAdmin());
        HttpSession session1 = request.getSession();
        session1.setAttribute("User", newSessionUser);

        response.sendRedirect("editUserData.jsp");

    }
}