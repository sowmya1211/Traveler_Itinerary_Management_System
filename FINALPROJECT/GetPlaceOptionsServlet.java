import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
public class GetPlaceOptionsServlet extends HttpServlet {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost:3306/TIMS";

    //  Database credentials
    static final String USER = "root"; 
    static final String PASS = "sqldb";

    public void doGet(HttpServletRequest request, HttpServletResponse response){  
        try{            
            response.setContentType("text/html");  
            PrintWriter out = response.getWriter();

            String fn = request.getParameter("fn");
            String dest_city = request.getParameter("dest_city");

            out.println("<table>");
            out.println("<tr><th>Place Name</th><th>Place Type</th></tr>");

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM places NATURAL JOIN location WHERE city = '"+dest_city+"'";
			ResultSet rs = stmt.executeQuery(sql);

            int i = 0;
            while(rs.next()){
                out.println("<tr id = '"+rs.getString("place_name") + "' onclick='"+fn+"("+i+");'><td>"+rs.getString("place_name") + "</td><td>"+rs.getString("place_type") + "</td></tr>");
                i+=1;
            }

            out.println("</table>");
            
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    }  
}  

