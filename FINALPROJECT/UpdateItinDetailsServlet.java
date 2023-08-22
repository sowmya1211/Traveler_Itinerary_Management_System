import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateItinDetailsServlet extends HttpServlet {
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

            HttpSession session = request.getSession(false);
            String uid = (String)session.getAttribute("uid");

            String trip_id = (String)request.getParameter("tid");
            String acc_id = (String)request.getParameter("acc_id");
            String onw_id = (String)request.getParameter("onw_id");
            String ret_id = (String)request.getParameter("ret_id");

            if(acc_id==""){
                acc_id ="NULL";
            }
            if(onw_id==""){
                onw_id ="NULL";
            }
            if(ret_id==""){
                ret_id ="NULL";
            }

            out.println(uid + " " + trip_id + " "+acc_id + " "+ onw_id+" "+ret_id);


			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            

            String sql1 = "UPDATE trips SET onw_trans_id='"+onw_id+"',ret_trans_id='"+ret_id+"', accom_id='"+acc_id+"' WHERE trip_id= '"+trip_id+"'";
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.execute(sql1);
            out.println("present");


            out.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    } 
    
}
