import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class GetItineraryDetailsServlet extends HttpServlet {
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
            
            String tid = (String)request.getParameter("tid");


			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
			String sql = "SELECT o.trans_type AS ott, o.source AS osc, o.dest AS odc, o.start_time AS os, o.end_time AS oe, o.cost AS oc, r.trans_type AS rtt, r.source AS rsc, r.dest AS rdc, r.start_time AS rs, r.end_time AS re, r.cost AS rc, a.accom_name AS an, a.accom_type AS at, a.address AS aa, a.cost AS ac, a.amenities AS aam  FROM trips t, transit o, transit AS r, accommodation a WHERE t.trip_id='"+tid+"' AND t.accom_id=a.accom_id AND t.onw_trans_id = o.transit_id AND t.ret_trans_id= r.transit_id;";
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                out.println("Type: "+rs.getString("ott")+"<br>Start city: "+rs.getString("osc")+"<br>Dest city: "+rs.getString("odc")+"<br>Start time: "+rs.getString("os")+"<br>End time: "+rs.getString("oe")+"<br>Cost: "+rs.getString("oc"));
                out.println("Type: "+rs.getString("rtt")+"<br>Start city: "+rs.getString("rsc")+"<br>Dest city: "+rs.getString("rdc")+"<br>Start time: "+rs.getString("rs")+"<br>End time: "+rs.getString("re")+"<br>Cost: "+rs.getString("rc"));
                out.println("Name: "+rs.getString("an")+"<br>Type: "+rs.getString("at")+"<br>Address: "+rs.getString("aa")+"<br>Cost: "+rs.getString("ac")+"<br>Amenities: "+rs.getString("aam"));               
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    } 
    
}
