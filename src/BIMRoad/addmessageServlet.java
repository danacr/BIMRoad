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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sender = request.getParameter("sender");
        String senderemail = request.getParameter("senderemail");
        String content = request.getParameter("content");
        String receiver = request.getParameter("receiver");

        String errorMsg = null;

        if (sender == "" || sender == null){
            errorMsg += "Sender can't be empty. ";
        }
        if (senderemail == "" || senderemail == null){
            errorMsg += "Sender email can't be empty. ";
        }
        if (content == "" || content == null){
            errorMsg += "Message can't be empty. ";
        }
        if (receiver == "" || receiver == null){
            errorMsg += "Receiver can't be empty. ";
        }

        if (errorMsg != null) {
            response.sendRedirect("addmessage.jsp?error=".concat(URLEncoder.encode(errorMsg, "UTF-8")));
        } else {
            int creationDate = helpers.getUnixTimestamp();
            boolean success = helpers.addMessageToDatabase(sender, senderemail, content, receiver, creationDate);
            if (success){
                response.sendRedirect("addmessage.jsp?success=1");
            }else{
                response.sendRedirect("addmessage.jsp?error=".concat(URLEncoder.encode("Something went wrong", "UTF-8")));
            }
        }

    }

}