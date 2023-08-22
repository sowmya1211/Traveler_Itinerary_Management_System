import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class GetChecklistDetailsServlet extends HttpServlet {
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


			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM checklistitems WHERE checklist_id='"+cid+"'";
			ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString("item_name");
                String stat = rs.getString("tickstatus");
                String id =  rs.getString("checklist_item_no");
                if(stat == null){
                    out.println(name+ ",,"+id);
                }
                else{
                    out.println(name+ "," + stat+","+id);
                }
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    } 
    
}
