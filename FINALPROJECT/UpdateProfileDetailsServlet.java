import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateProfileDetailsServlet extends HttpServlet {
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
            String name = (String)request.getParameter("name");
            int age=0;
            if(((String)request.getParameter("age")).length() != 0){
                age = Integer.parseInt(request.getParameter("age"));
            }
            String address = (String)request.getParameter("address");
            String sex = (String)request.getParameter("sex");
            String phno = (String)request.getParameter("phno");
            String email = (String)request.getParameter("email");
            String proof = (String)request.getParameter("proof");
            out.println(uid + " " + utype + " " + name + " " + age + " " + address + " " + sex + " " + phno + " " + email + " " + proof);

            Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //out.println("p3hey\n");
            
            Statement stmt = conn.createStatement();
            String sql = "";
            if(utype.equals("Traveler")){
                sql ="SELECT tid FROM traveler WHERE tid='"+uid+"'";
            }
            else if(utype.equals("Guide")){
                sql ="SELECT gid FROM guide WHERE gid='"+uid+"'";
            }
            //out.println(sql);
            out.println("hey");

            ResultSet rs = stmt.executeQuery(sql);
            String sql1 = "";
            if(rs.next()){

                if(utype.equals("Traveler")){
                    sql1 = "UPDATE traveler SET name='"+name+"',age="+age+", address='"+address+"', sex='"+sex+"', phno='"+phno+"', email='"+email+"' WHERE tid= '"+uid+"'";
                }
                else if(utype.equals("Guide")){
                    sql1 = "UPDATE guide SET name='"+name+"', phno='"+phno+"', email='"+email+"', proof ='"+proof+"' WHERE gid= '"+uid+"'";

                }
                
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                stmt1.execute(sql1);
                
            }
            else{

                if(utype.equals("Traveler")){
                    PreparedStatement st = conn.prepareStatement("INSERT INTO traveler VALUES(?, ?, ?, ?, ?, ?, ?)");
                    st.setString(1, uid);
                    st.setString(2, name);
                    st.setInt(3, age);
                    st.setString(4, address);
                    st.setString(5, sex);
                    st.setString(6, phno);
                    st.setString(7, email);
                    st.executeUpdate();
                }
                else if(utype.equals("Guide")){
                    PreparedStatement st = conn.prepareStatement("INSERT INTO guide VALUES(?, ?, ?, ?, ?)");
                    st.setString(1, uid);
                    st.setString(2, name);
                    st.setString(3, phno);
                    st.setString(4, email);
                    st.setString(5, proof);
                    out.println("part1");

                    st.executeUpdate();
                }
            }
			

            out.close();
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }  
  
    } 
    
}

