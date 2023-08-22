import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class DelItinServlet extends HttpServlet {
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
            

            String tid = (String)request.getParameter("tid");
            String it_id = (String)request.getParameter("it_id");

            int lno = Integer.parseInt(it_id);

            out.println("Servlet");

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "DELETE FROM itineraries WHERE trip_id='"+tid+"' AND it_id="+it_id;
            PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute(sql);

            int i = lno+1;
            while(true){
                Statement stmt1 = conn.createStatement();
                String sql1 = "SELECT it_id FROM itineraries WHERE trip_id='"+tid+"' AND it_id="+i;
                ResultSet rs = stmt1.executeQuery(sql1);

                if(rs.next()){
                    int id =  Integer.parseInt(rs.getString("it_id"));
                    int newid = id-1;
                    out.println(id + " " + newid);
                    String sql2 = "UPDATE itineraries SET it_id=" + newid + " WHERE trip_id='"+tid+"' AND it_id="+i;
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                    stmt2.execute(sql2);
                    out.println(id + " " + newid);
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