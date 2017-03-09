package BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bidItemServlet")
public class bidItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int itemid = Integer.parseInt(request.getParameter("itemid"));
        int userid = Integer.parseInt(request.getParameter("userid"));

        if(!helpers.isNumeric(request.getParameter("bidamount"))) {
            response.sendRedirect("viewItem.jsp?itemid="+ itemid);
            return;
        }
        Double bidAmount = Double.parseDouble(request.getParameter("bidamount"));

        if ( helpers.bidIsHigherThanCurrentBid(bidAmount, itemid)) {
            helpers.updateHighestBid(bidAmount, itemid, userid);
        }

        response.sendRedirect("viewItem.jsp?itemid="+itemid);
    }
}