package BIMRoad;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;


@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get email and password from POST
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //check for errors
        String errorMsg = "";
        if (email == null || email.equals("")) {
            errorMsg += "Email can't be empty ";
        }
        if (password == null || password.equals("")) {
            if(errorMsg.equals("")) {
                errorMsg += "Password can't be empty";
            }else{
                errorMsg += "and password can't be empty";
            }
        }
        //if something was empty
        if (!errorMsg.equals("")) {
            response.sendRedirect("login.jsp?error=".concat(URLEncoder.encode(errorMsg, "UTF-8")));
            System.out.println(errorMsg);
        }

        //if no valid email address was entered
        if (!helpers.isValidEmail(email)){
            response.sendRedirect("login.jsp?error=".concat(URLEncoder.encode("This is not a valid email address", "UTF-8")));
            System.out.println("not a valid email");
        }

        //lookup this email and password
        User dbUser = helpers.getUserByEmailPassword(email, password);

        //default value for id in User is -1 so we know when it is empty
        if (dbUser.getId() == -1) {
           response.sendRedirect("login.jsp?error=Wrong%20username%20or%20password");
           System.out.println("wrong username or password");
        } else {
            //add details to user's session
            User user = new User(dbUser.getId(), dbUser.getName(), dbUser.getEmail(), dbUser.getIsAdmin());
            HttpSession session = request.getSession();
            session.setAttribute("User", user);

            if(dbUser.getIsAdmin()==1){
                response.sendRedirect("admin_home.jsp");
            }else {
                response.sendRedirect("home.jsp");
            }
        }
    }
}
