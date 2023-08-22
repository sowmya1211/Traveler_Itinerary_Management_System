import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class GetProfDetailsServlet extends HttpServlet {
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
            String utype = (String)request.getParameter("utype");
            String name = "";
            int age = 0;
            String address = "";
            String sex = "";
            String phno = "";
            String email = "";
            String proof = "";

			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt1 = conn.createStatement();
            String sql1 = "";

            if(utype.equals("Traveler")){
                sql1 ="SELECT * FROM traveler WHERE tid='"+uid+"'";
            }
            else if(utype.equals("Guide")){
                sql1 ="SELECT * FROM guide WHERE gid='"+uid+"'";
            }
            
            ResultSet rs = stmt1.executeQuery(sql1);
            if(rs.next()){
                name = rs.getString("name");
                phno = rs.getString("phno");
                email = rs.getString("email");
                if(utype.equals("Traveler")){
                    age = rs.getInt("age");
                    address = rs.getString("address");
                    sex = rs.getString("sex");
                }
                else if(utype.equals("Guide")){
                    proof = rs.getString("proof");
                }
            }

            out.println("','"+uid + "','" + utype + "','" + name + "','" + age + "','" + address + "','" + sex + "','" + phno + "','" + email + "','" + proof);


            out.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    } 
    
}
