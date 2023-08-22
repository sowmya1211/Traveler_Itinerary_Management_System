import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
  
public class ValidateUnameServlet extends HttpServlet {
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
            //out.println("<p>Driver Access Initialising....</p>");
			Class.forName(JDBC_DRIVER);
			//out.println("<p>Driver Access Succesful....</p>");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//out.println("<p>Database Connection Successful....</p>");

            String uid = request.getParameter("uid");
            
            // Execute SQL query
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE uid='"+uid+"'";
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
               //out.println(uname + ",, " +pwd);
                out.println("0");
            }
            else{
                out.println("1");
            }
            
            conn.close();
            

        }
        catch(Exception e){
            System.out.println(e);
        }
  
    }  
}  