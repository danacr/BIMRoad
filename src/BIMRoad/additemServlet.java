package BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;

@WebServlet(name = "additem", urlPatterns = {"/additem"})
@MultipartConfig
public class additemServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String itemname = request.getParameter("itemname");
        int userid = Integer.parseInt(request.getParameter("userid"));
        Part filePart = request.getPart("image"); // Retrieves <input type="file" name="file">
        String imagename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream image = filePart.getInputStream();
        String description = request.getParameter("description");
        double price = 0;
        if (helpers.isNumeric(request.getParameter("price"))) {
            price = Double.parseDouble(request.getParameter("price"));
        } else {
            //stop!, non numeric price
            response.sendRedirect("additem.jsp?error=Price+needs+to+be+a+number");
            return;
        }

        String errorMsg = "";

        if (itemname == null || itemname.equals("")) {
            errorMsg += "Item name can't be empty. ";
        }
        if (description == null || description.equals("")) {
            errorMsg += "Description can't be empty. ";

        }
        if (price == 0) {
            errorMsg += "Price can't be empty or 0.";
        }


        if (errorMsg != "") {
            response.sendRedirect("additem.jsp?error=".concat(URLEncoder.encode(errorMsg, "UTF-8")));
        } else {

            int creationDate = helpers.getUnixTimestamp();
            boolean success = helpers.addItemToDatabase(itemname, description, price, userid, imagename, image, creationDate);
            if (success) {
                response.sendRedirect("additem.jsp?success=1");
            } else {
                response.sendRedirect("additem.jsp?error=".concat(URLEncoder.encode("Something went wrong", "UTF-8")));
            }
        }

    }

}