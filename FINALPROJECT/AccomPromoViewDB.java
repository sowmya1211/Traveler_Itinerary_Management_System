import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AccomPromoViewDB extends HttpServlet{ 
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost:3306/TIMS";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "sqldb";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException{
        response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "View Accomodation Details";
		
		String aid = request.getParameter("accom_id"); 

		out.println(
         "<html>\n" +
         "<head>"+
		 "<link rel=\"stylesheet\" href=\"PromRecTable.css\">"+
		 "<title>"+title+"</title></head>\n" +
         "<body style = \"background-image:url('images/painting-1682416389136-9172.jpg'); background-repeat:no-repeat; background-size:cover; opacity:0.4\">\n");

		   
         try{
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            
            // Execute SQL query
            String sql = "SELECT * FROM Accommodation WHERE accom_id='" + aid + "'";
		    //Execute SQL Query
			ResultSet rs = st.executeQuery(sql);
            out.print("<table style=\"margin-top: 90px;\">");
			out.print("<tr><th>Accommodation ID</th>");
            out.print("<tr><th>Location ID</th>");
			out.print("<th>Accommodation Name</th>");
			out.print("<th>Accommodation Type</th>");
			out.print("<th>Address</th>");
			out.print("<th>Cost</th>");
			out.print("<th>Amenities</th>");
			out.print("<th>Promotion Priority</th>");
			out.print("</tr>");

			if(rs.next())
			{
			  rs.previous(); 
			  while(rs.next()){
				String accom_id = rs.getString("accom_id");
                String loc_id = rs.getString("loc_id");
				String accom_name = rs.getString("accom_name");
				String accom_type = rs.getString("accom_type");
				String addr = rs.getString("address");
				float cost = rs.getFloat("cost");
				String amen = rs.getString("amenities");
				int prom_priority = rs.getInt("prom_priority");
				
				//Display values
				out.print("<tr><td>" + accom_id + "</td>");
				out.print("<tr><td>" + loc_id + "</td>");
				out.print("<td>" + accom_name + "</td>");
				out.print("<td>" + accom_type + "</td>");
				out.print("<td>" + addr + "</td>");
				out.print("<td>" + cost + "</td>");
				out.print("<td>" + amen + "</td>");
				out.print("<td>" + prom_priority + "</td></tr>"); 
			  }
			  out.println("</table>");
			}
			else{
			 out.print("</table>");
			 out.println("<p>No Records Found</p>");
			} 
            // Close all the connections
            rs.close();
			st.close();
            conn.close();
		} 
		catch(SQLException se) {
         //Handle errors for JDBC
            out.println("<p>Error</p>");
            se.printStackTrace();
		} 
        catch(Exception e){
            out.println("<p>Error</p>");
        }
        finally{
            out.println("</body></html>");
        }
      }
    
}

