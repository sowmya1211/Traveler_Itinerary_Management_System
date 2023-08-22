import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdatePassServlet extends HttpServlet {
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
            
            String uid = (String)request.getParameter("uid");
            String pwd = (String)request.getParameter("pwd");

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            
            String sql1 = "UPDATE users SET password='"+pwd+"' WHERE uid='"+uid+"'";    
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.execute(sql1);

            out.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    } 
    
}
