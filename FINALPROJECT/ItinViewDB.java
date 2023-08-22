import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class ItinViewDB extends HttpServlet{
		// JDBC driver name and database URL
      static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      static final String DB_URL="jdbc:mysql://localhost:3306/TIMS";

      //  Database credentials
      static final String USER = "root";
      static final String PASS = "sqldb";

	  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
		// Set response content type
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

		try{ 
                
            String trip_id = request.getParameter("trip_id");
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Writing table entries into response
            Statement st = conn.createStatement();
			String sql = "SELECT * FROM Itineraries, places where trip_id = '"+trip_id+"' AND tourist_spot=place_name ORDER BY dov, st_time;";
			ResultSet rs = st.executeQuery(sql);
            out.print("<table><tr>");
			out.print("<th>Place Type</th>");
			out.print("<th>Tourist Spot</th>");
			out.print("<th>Date of Visit</th>");
            out.print("<th>Start Time</th>");
            out.print("<th>Duration</th>");
            out.print("<th>Delete item</th>");
			out.print("</tr>");

			
			while(rs.next()){
				String tourist_spot = rs.getString("tourist_spot");
				String spot_type = rs.getString("place_type");
                String dov = rs.getString("dov");
                String st_time = rs.getString("st_time");
                int dur = rs.getInt("duration");
                int itid = rs.getInt("it_id");

				//Display values
				out.print("<tr id='"+itid+"'><td>" + spot_type+ "</td>");
				out.print("<td>" + tourist_spot + "</td>");
                out.print("<td>" + dov + "</td>");
                out.print("<td>" + st_time + "</td>");
				out.print("<td>" + dur + "</td>");
                out.print("<td class='del' onclick='del("+itid+")'>X</td></tr>");
			  }
			  out.println("</table>");
			
            st.close();
            rs.close();
            conn.close(); 
          
        }
        catch (Exception e) {
            out.println(e);
            e.printStackTrace();
        }
    }
}