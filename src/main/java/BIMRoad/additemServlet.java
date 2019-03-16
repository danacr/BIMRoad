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


@WebServlet(name = "additem", urlPatterns = {"/additem"})
@MultipartConfig
public class additemServlet extends HttpServlet {

    //doGet = HTML GET request - when you don't need to gather info
    //doPost = HTML POST request - when you want to gather info
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Define a new variable "itemName" and save the itemname defined in addItem.jsp in this variable.
        String itemname = request.getParameter("itemname");

        //Define a new variable "userid" and save the userid defined in addItem.jsp in this variable.
        int userid = Integer.parseInt(request.getParameter("userid"));

        //Define a new variable called "filePart" and save the image defined in addItem.jsp in this variable
        Part filePart = request.getPart("image"); //

        //Define a new variable "imagename" and save the SubmittedFileName in this variable

        String imagename = filePart.getSubmittedFileName();

        //Define a new variable called "image" and read the image through getInputStream
        InputStream image = filePart.getInputStream();

        //Define a new variable "description" and save the Description defined in addItem.jsp in this variable
        String description = request.getParameter("description");

        //Define "price" and set to 0.
        double price = 0;

        //helpers.isNumeric checks if the value of price is a double. If this is the case, change price into price entered by user  (see addItem.jsp)
        if (helpers.isNumeric(request.getParameter("price"))) {
            price = Double.parseDouble(request.getParameter("price"));
        } else {
            //If there is no number filled out --> ERROR message: Price needs to be a number
            response.sendRedirect("additem.jsp?error=Price+needs+to+be+a+number");
            return;
        }
        //Default error message is empty
        String errorMsg = "";

        //if itemname is empty, this error message
        if (itemname == null || itemname.equals("")) {
            errorMsg += "Item name can't be empty. ";
        }

        //if description is empty, this error message
        if (description == null || description.equals("")) {
            errorMsg += "Description can't be empty. ";

        }

        //if price is empty, this error message
        if (price == 0) {
            errorMsg += "Price can't be empty or 0.";
        }

        //if there is an errormessage, the user is redirected to the error page (based on the error)
        if (errorMsg != "") {
            response.sendRedirect("additem.jsp?error=".concat(URLEncoder.encode(errorMsg, "UTF-8")));

            //if there is no error
        } else {

            //Set automatically the date of creation
            int creationDate = helpers.getUnixTimestamp();

            //Try to add the item to the database
            boolean success = Item.addItemToDatabase(itemname, description, price, userid, imagename, image, creationDate);

            //if the item is added to the database, the user is redirected to the success page
            if (success) {
                response.sendRedirect("additem.jsp?success=1");

                //if the item is NOT added to the database, the user is redirected to an error page
            } else {
                response.sendRedirect("additem.jsp?error=".concat(URLEncoder.encode("Something went wrong", "UTF-8")));
            }
        }

    }

}