import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
public class GetAllAccomServlet extends HttpServlet {
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
            out.println("<table>");
            out.println("<tr><th>Accomodation Type</th><th>Accomodation Name</th><th>Address</th><th>Cost</th><th>Amenities</th></tr>");

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM accommodation NATURAL JOIN location WHERE city = '" + request.getParameter("city") + "' ORDER BY prom_priority";
			ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                out.println("<tr id = '"+rs.getString("accom_id") + "' onclick='clear_all();"+fn+"("+rs.getString("accom_id")+");'><td>"+rs.getString("accom_type") + "</td><td>" + rs.getString("accom_name") + "</td><td>" + rs.getString("address") + "</td><td>" + rs.getString("cost") + "</td><td>" + rs.getString("amenities") +"</tr>");
            }

            out.println("</table>");
            
        } 
        catch(Exception e){
            System.out.println(e);
        }  
  
    }  
}  

