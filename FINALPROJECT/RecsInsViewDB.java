import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class RecsInsViewDB extends HttpServlet{
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
        String title = "Insert and View Recommendations";

		try{
                
             String gid = (String)session.getAttribute("uid");
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Finding No of Recommendations to decide Recno
            Statement st_c = conn.createStatement();
			String sql = "SELECT * FROM Recommendations where guide_id = '"+gid+"'";
			ResultSet rs1 = st_c.executeQuery(sql);
            int rec_no = 1;
            while(rs1.next())
              rec_no++;
            st_c.close();

            //Insert into Table   
			PreparedStatement st_ins = conn.prepareStatement("Insert into Recommendations values(?, ?, ?, ?)");
            st_ins.setInt(1, rec_no);
			st_ins.setString(2, gid);
			st_ins.setString(3, request.getParameter("locn"));
            st_ins.setString(4, request.getParameter("recs"));
			st_ins.executeUpdate();
            st_ins.close();


           out.println(
           "<html>\n" + "<head>"+
		   "<link rel=\"stylesheet\" href=\"PromRecTable.css\">"+
		   "<title>"+title+"</title></head>\n" +
           "<body style = \"background-image:url('images/painting-1682416389136-9172.jpg'); background-repeat:no-repeat; background-size:cover; opacity:0.8\">\n");
            //Writing table entries into response
            Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
            out.print("<table>");
			out.print("<tr><th>Recommendation No</th>");
			out.print("<th>Guide ID</th>");
			out.print("<th>Location</th>");
			out.print("<th>Recommendations</th>");
			out.print("</tr>");

			
			while(rs.next()){
                rec_no = rs.getInt("rec_no");
				gid = rs.getString("guide_id");
				String locn = rs.getString("locn");
                String recs = rs.getString("recs");
				//Display values
				out.print("<tr><td>" + rec_no + "</td>");
				out.print("<td>" + gid + "</td>");
				out.print("<td>" + locn + "</td>");
				out.print("<td>" + recs + "</td></tr>");
			  }
			  out.println("</table>");
			
            st.close();
            rs.close();
            conn.close(); 
            out.println("<p>Insert Successful....</p>");
          
        }
        catch (Exception e) {
            out.println(e);
            e.printStackTrace();
        }
    }
} 