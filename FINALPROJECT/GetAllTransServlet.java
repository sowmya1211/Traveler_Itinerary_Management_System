import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
public class GetAllTransServlet extends HttpServlet {
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
            out.println("<tr><th>Transit Type</th><th>Source</th><th>Destination</th><th>Start Time</th><th>EndTime</th><th>Cost</th></tr>");

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM transit ORDER BY prom_priority";
			ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                out.println("<tr id = '"+rs.getString("transit_id") + "' onclick='clear_all();"+fn+"("+rs.getString("transit_id")+");'><td>"+rs.getString("trans_type") + "</td><td>" + rs.getString("source") + "</td><td>" + rs.getString("dest") + "</td><td>" + rs.getString("start_time") + "</td><td>" + rs.getString("end_time")+"</td><td>" + rs.getString("cost")+"</td></tr>");
            }

            out.println("</table>");
            
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    }  
}  


