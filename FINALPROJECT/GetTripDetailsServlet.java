import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
public class GetTripDetailsServlet extends HttpServlet {
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
			String sql = "SELECT * FROM trips WHERE trip_id='"+tid+"'";
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                Statement stmt1 = conn.createStatement();
                String sql1 = "SELECT * FROM location WHERE loc_id='"+rs.getString("st_place")+"'";
                ResultSet rs1 = stmt1.executeQuery(sql1);
                if(rs1.next()){
                    out.println(rs1.getString("city"));
                }

                Statement stmt2 = conn.createStatement();
                String sql2 = "SELECT * FROM location WHERE loc_id='"+rs.getString("dest")+"'";
                ResultSet rs2 = stmt1.executeQuery(sql2);
                if(rs2.next()){
                    out.println(rs2.getString("city"));
                }
                out.println(rs.getString("st_date"));
                out.println(rs.getString("end_date"));
            }

            
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    }  
}  