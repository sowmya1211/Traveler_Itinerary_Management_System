import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DelChecklistItemServlet extends HttpServlet {
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

            out.println("Servlet");

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "DELETE FROM checklistitems WHERE checklist_id= '"+cid+"' AND checklist_item_no="+lno;
            PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute(sql);

            int i = lno+1;
            while(true){
                Statement stmt1 = conn.createStatement();
                String sql1 = "SELECT checklist_item_no FROM checklistitems WHERE checklist_id='"+cid+"' AND checklist_item_no="+i;
                ResultSet rs = stmt1.executeQuery(sql1);

                if(rs.next()){
                    int id =  rs.getInt("checklist_item_no");
                    int newid = id-1;
                    out.println(id + " " + newid);
                    String sql2 = "UPDATE checklistitems SET checklist_item_no=" + newid + " WHERE checklist_id= '"+cid+"' AND checklist_item_no="+id;
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                    stmt2.execute(sql2);
                }
                else{
                    break;
                }
                i+=1;
            }
            out.println("succesful");
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    } 
    
}