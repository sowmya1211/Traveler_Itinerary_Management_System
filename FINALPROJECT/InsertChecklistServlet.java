import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;  

public class InsertChecklistServlet extends HttpServlet {
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
            String cname = (String)request.getParameter("cname");

            
            Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM checklist";
			ResultSet rs = stmt.executeQuery(sql);

            String last = "C_00";
            while(rs.next()){
                last = rs.getString("checklist_id");
            }

            int l = Integer.parseInt(last.substring(2))+1;
            String n = String.format("%02d", l);
            out.print("C_"+n);
            
            PreparedStatement st = conn.prepareStatement("INSERT INTO checklist VALUES(?, ?, ?)");
			st.setString(1, "C_"+n);
            st.setString(2, tid);
            st.setString(3, cname);
			st.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    }    
}
