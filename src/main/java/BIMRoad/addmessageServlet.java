package BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "addmessage", urlPatterns = {"/addmessage"})

public class addmessageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //doGet = HTML GET request - when you don't need to gather info
    //doPost = HTML POST request - when you want to gather info
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Define a new variable "sender" and save the sender defined in addmessage.jsp in this variable.
        String sender = request.getParameter("sender");

        // Define a new variable "senderemail" and save the senderemail defined in addmessage.jsp in this variable.
        String senderemail = request.getParameter("senderemail");

        // Define a new variable "content" and save the content defined in addmessage.jsp in this variable.
        String content = request.getParameter("content");

        // Define a new variable "receiver" and save the receiver defined in addmessage.jsp in this variable.
        String receiver = request.getParameter("receiver");

        //Default errormessage = null
        String errorMsg = null;

        //if sender is empty, this error message
        if (sender == "" || sender == null) {
            errorMsg += "Sender can't be empty. ";
        }

        //if senderemail is empty, this error message
        if (senderemail == "" || senderemail == null) {
            errorMsg += "Sender email can't be empty. ";
        }

        //if content is empty, this error message
        if (content == "" || content == null) {
            errorMsg += "Message can't be empty. ";
        }

        //if receiver is empty, this error message
        if (receiver == "" || receiver == null) {
            errorMsg += "Receiver can't be empty. ";
        }

        //if there is an errormessage, the user is redirected to the error page (based on the error)
        if (errorMsg != null) {
            response.sendRedirect("addmessage.jsp?error=".concat(URLEncoder.encode(errorMsg, "UTF-8")));

            //if there is no error
        } else {

            //Set automatically the date of creation
            int creationDate = helpers.getUnixTimestamp();

            //Try to add the item to the database
            boolean success = Message.addMessageToDatabase(sender, senderemail, content, receiver, creationDate);

            //if the item is added to the database, the user is redirected to the success page
            if (success) {
                response.sendRedirect("addmessage.jsp?success=1");

                //if the item is NOT added to the database, the user is redirected to an error page
            } else {
                response.sendRedirect("addmessage.jsp?error=".concat(URLEncoder.encode("Something went wrong", "UTF-8")));
            }
        }

    }

}