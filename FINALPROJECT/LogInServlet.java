import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
  
public class LogInServlet extends HttpServlet {
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
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String uid = request.getParameter("uid");
            String pwd =  request.getParameter("pwd");
            
            // Execute SQL query
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE uid='"+uid+"'";
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next() && pwd.equals(rs.getString("password"))){
                out.println("1");
                out.println(rs.getString("utype"));
            }
            else{
                out.println("0");
            }
            
            conn.close();
            

        }
        catch(Exception e){
            System.out.println(e);
        }
  
    }  
}  