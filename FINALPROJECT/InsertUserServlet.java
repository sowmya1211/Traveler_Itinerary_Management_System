import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertUserServlet extends HttpServlet {
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
            
            String role = (String)request.getParameter("role");
            String uname = (String)request.getParameter("uid");
            String pwd = (String)request.getParameter("pwd");
            

            out.println("==>"+role+uname+pwd+request);

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            PreparedStatement st = conn.prepareStatement("INSERT INTO users VALUES(?, ?, ?)");
			st.setString(1, uname);
            st.setString(2, pwd);
			st.setString(3, role);
			st.executeUpdate();

            response.sendRedirect("SessionInitiate?uid="+uname+"&utype="+role+"&new_user=1");
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    }    
}