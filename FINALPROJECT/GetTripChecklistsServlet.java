import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
public class GetTripChecklistsServlet extends HttpServlet {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost:3306/TIMS";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "sqldb";

    public void doGet(HttpServletRequest request, HttpServletResponse response){  
        try{
            HttpSession session = request.getSession(false);
            
            response.setContentType("text/html");  
            PrintWriter out = response.getWriter(); 
            
            String uid = (String)session.getAttribute("uid");
            String tid = (String)request.getParameter("tid");


			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM checklist WHERE trip_id='"+tid+"'";
			ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                out.println(rs.getString("checklist_id") + "," + rs.getString("checklist_name"));
            }
            
        }
        catch(Exception e){ 
            System.out.println(e);
        }  
  
    }  
}  

