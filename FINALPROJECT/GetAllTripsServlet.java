import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
public class GetAllTripsServlet extends HttpServlet {
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
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
			String sql = "SELECT trip_id, dest, st_date FROM trips WHERE traveler_id='"+uid+"' ORDER BY st_date";
			ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Statement stmt1 = conn.createStatement();
                String sql1 = "SELECT * FROM location WHERE loc_id='"+rs.getString("dest")+"'";
                ResultSet rs1 = stmt1.executeQuery(sql1);
                String city = "";
                if(rs1.next()){
                    city = rs1.getString("city");
                }
                out.print(city + " (" + rs.getDate("st_date") + ") " + rs.getString("trip_id")  + ",");
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    }  
}  

