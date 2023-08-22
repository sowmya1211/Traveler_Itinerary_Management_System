import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddItemServlet extends HttpServlet {
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
            String cid = (String)request.getParameter("cid");
            int lno = Integer.parseInt(request.getParameter("cno"));
            String itname = (String)request.getParameter("name");

            out.println("==>"+cid+lno+itname);

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement("insert into checklistitems values(?, ?, ?,  NULL)");
			
            st.setInt(1, Integer.valueOf(request.getParameter("cno")));
			st.setString(2, request.getParameter("cid"));
			st.setString(3, request.getParameter("name"));
			st.executeUpdate();

            out.println("succesful");
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    } 
    
}
