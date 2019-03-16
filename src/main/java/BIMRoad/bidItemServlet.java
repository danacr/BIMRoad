package BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bidItemServlet")
public class bidItemServlet extends HttpServlet {

	//doGet = HTTML GET request - when you don't need to gather info
	//doPost = HTTM POST request - when you want to gather info
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Define a new variable "itemid" and save the itemid defined in viewItem.jsp in this variable.
		int itemid = Integer.parseInt(request.getParameter("itemid"));

		// Define a new variable "userid" and save the userid defined in viewItem.jsp in this variable.
		int userid = Integer.parseInt(request.getParameter("userid"));

		//If the bidamount is not numeric redirect the user back to the itempage he/she bid on
		if (!helpers.isNumeric(request.getParameter("bidamount"))) {
			response.sendRedirect("viewItem.jsp?itemid=" + itemid);
			return;
		}

		//Define a new variable bidamount and save the bidamount defined in viewItem.jsp in this variable.
		Double bidAmount = Double.parseDouble(request.getParameter("bidamount"));

		//If the bidamount is higher than the current bid, update the highest bid on that item by that userid
		if (helpers.bidIsHigherThanCurrentBid(bidAmount, itemid)) {
			Item.updateHighestBid(bidAmount, itemid, userid);
		}
		//When done, redirect the user back to the itempage he/she bid one
		response.sendRedirect("viewItem.jsp?itemid=" + itemid);
	}
}