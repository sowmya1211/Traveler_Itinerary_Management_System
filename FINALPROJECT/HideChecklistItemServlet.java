import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class HideChecklistItemServlet extends HttpServlet {
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
            int hide_par = Integer.parseInt(request.getParameter("hide"));

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql;
            if(hide_par==1){
                sql = "UPDATE checklistitems SET tickstatus = 'checked' WHERE checklist_id= '"+cid+"' AND checklist_item_no="+lno;
            }
            else{
                sql = "UPDATE checklistitems SET tickstatus = NULL WHERE checklist_id= '"+cid+"' AND checklist_item_no="+lno;
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute(sql);

            out.println("succesful");
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    } 
    
}
