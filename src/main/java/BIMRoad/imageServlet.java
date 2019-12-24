package BIMRoad;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/image")
public class imageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String x = request.getParameter("id");

        Blob image = null;

        String s = null;

        try {
            database db = new database();
            Connection con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement("Select * from Items where id=?");
            stmt.setString(1, x);
            ServletOutputStream out = response.getOutputStream();

            ResultSet rs = stmt.executeQuery();
            {

                while (rs.next()) {
                    s = rs.getString("imagename");

                    // image = new javax.sql.rowset.serial.SerialBlob(rs.getBlob("image"));
                    image = rs.getBlob("image");
                    InputStream imgData = image.getBinaryStream();

                    s = "image/" + s.substring(s.lastIndexOf('.') + 1);
                    response.setContentType(s);
                    int length = (int) image.length();
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];

                    while ((length = imgData.read(buffer)) != -1) {

                        out.write(buffer, 0, length);
                    }

                    imgData.close();
                    out.flush();

                }
            }

        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}
