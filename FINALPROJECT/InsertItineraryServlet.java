import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;  

public class InsertItineraryServlet extends HttpServlet {
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

            String tid = (String)request.getParameter("tid");
            String ts = (String)request.getParameter("ts");
            String dov = request.getParameter("dov");
            String st_time = request.getParameter("st_time");
            String duration = request.getParameter("duration"); 
            

            out.println("==>"+tid+" "+ts+" "+ dov+ " "+ st_time);
            Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
        

			String sql = "SELECT * FROM itineraries WHERE trip_id = '"+tid+"'";
			ResultSet rs = stmt.executeQuery(sql);

            int i = 1;
            while(rs.next()){
                i = Integer.parseInt(rs.getString("it_id"));
                out.println(i);
                i+=1;
            }
            out.println(i);
            
            PreparedStatement st = conn.prepareStatement("INSERT INTO itineraries VALUES(?, ?, ?, ?, ?, ?)");
			st.setString(1, tid);
            st.setString(2, String.valueOf(i));
            st.setString(3, ts);
            st.setString(4, dov);
            st.setString(5, st_time); 
            st.setString(6, duration);
			st.executeUpdate();
			

            out.println("successful");
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    }    
}
