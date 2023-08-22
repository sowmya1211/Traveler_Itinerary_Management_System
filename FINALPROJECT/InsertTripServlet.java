import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;  

public class InsertTripServlet extends HttpServlet {
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
            HttpSession session = request.getSession(false);
            String uid = (String)session.getAttribute("uid");

            String st_city = (String)request.getParameter("st_place");
            String dest = (String)request.getParameter("dest");
            String st_date = request.getParameter("st_date");
            String end_date = request.getParameter("end_date");

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
			String sql = "SELECT trip_id FROM trips";
			ResultSet rs = stmt.executeQuery(sql);

            int i = 0;
            while(rs.next()){
                if(i < Integer.parseInt(rs.getString("trip_id"))){
                    i = Integer.parseInt(rs.getString("trip_id"));
                }
            }
            i=i+1;
            out.println(i);
            
            PreparedStatement st = conn.prepareStatement("INSERT INTO trips VALUES(?, ?, ?, ?, ?, ?, NULL, NULL, NULL)");
			st.setString(1, String.valueOf(i));
            st.setString(2, uid);
            st.setString(3, st_city);
            st.setString(4, dest);
            st.setString(5, st_date);
            st.setString(6, end_date);
			st.executeUpdate();

            out.println("successful");
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    }    
}
