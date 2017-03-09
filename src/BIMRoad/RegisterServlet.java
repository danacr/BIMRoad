package BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String name = request.getParameter("name");
        String street = request.getParameter("street");
        String postalcode = request.getParameter("postalcode");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        String errorMsg = "";

        if (email == null || email.equals("")) {
            errorMsg += "Email can't be empty. ";
        }
        if (password == null || password.equals("")) {
            errorMsg += "Password can't be empty. ";
        }
        if (password == null || password.equals("")) {
            errorMsg += "Confirm password can't be empty. ";
        }
        if (name == null || name.equals("")) {
            errorMsg += "Name can't be empty. ";
        }
        if (street == null || street.equals("")) {
            errorMsg += "Street can't be empty. ";
        }
        if (postalcode == null || postalcode.equals("")) {
            errorMsg += "Postal code can't be empty. ";
        }
        if (city == null || city.equals("")) {
            errorMsg += "City can't be empty. ";
        }
        if (country == null || country.equals("")) {
            errorMsg += "Country can't be empty. ";
        }
        if (!helpers.isValidEmail(email)) {
            errorMsg += "This is not a valid email address. ";
        }
        if (!password.equals(password2)) {
            errorMsg += "The passwords don't match. ";
        }
        if (helpers.doesEmailExists(email)){
            errorMsg += "There exists already a user with this email address.";
        }

        if (!errorMsg.equals("")){
            response.sendRedirect("register.jsp?error=".concat(URLEncoder.encode(errorMsg, "UTF-8")));
        } else {

            int creationDate = helpers.getUnixTimestamp();


            PreparedStatement ps = null;
            try {
                database db = new database();
                Connection con = db.getConnection();

                ps = con.prepareStatement("insert into Users(name,email,password,street,postalcode,city,country,creationDate) values (?,?,?,?,?,?,?,?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setString(4, street);
                ps.setString(5, postalcode);
                ps.setString(6, city);
                ps.setString(7, country);
                ps.setInt(8, creationDate);

                ps.execute();

                ps.close();
                con.close();
                db.connection.close();

                System.out.println("User registered with email=" + email);

                //forward to login page to login
                response.sendRedirect("login.jsp?success=1");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("database connection problem");

            }
        }
    }
}
