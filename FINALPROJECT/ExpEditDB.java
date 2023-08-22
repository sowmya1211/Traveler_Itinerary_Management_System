import java.io.*;
import java.util.*;
import javax.servlet.*; 
import javax.servlet.http.*;
import java.sql.*;
 
public class ExpEditDB extends HttpServlet{
		// JDBC driver name and database URL
      static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      static final String DB_URL="jdbc:mysql://localhost:3306/TIMS";

      //  Database credentials
      static final String USER = "root";
      static final String PASS = "sqldb";

	  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
		// Set response content type
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
        //HttpSession session = request.getSession(false);
		try{
                 
			// Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // Open a connection 
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
            //Finding No of Expenses]
            String sql = "SELECT * FROM expenses where budget_id='"+request.getParameter("budget_id")+"' and trip_id='"+request.getParameter("trip_id")+"'";
            Statement st_c = conn.createStatement();
            ResultSet rs1 = st_c.executeQuery(sql);
            int no_exp = 1;
            while(rs1.next()){ 
                no_exp++;
            }
               
            st_c.close();
            rs1.close();
            out.println(no_exp);
            
            //Insert into Table   
            PreparedStatement st_ins = conn.prepareStatement("Insert into expenses values(?, ?, ?, ?, ?)");
            st_ins.setInt(1, no_exp);
            st_ins.setString(2, request.getParameter("budget_id"));
            st_ins.setString(4, request.getParameter("exp_dets"));
            st_ins.setFloat(5, Float.parseFloat(request.getParameter("amt_spent")));
            st_ins.setString(3, request.getParameter("trip_id"));
            st_ins.executeUpdate();
            st_ins.close();

            out.println("<p>Insert Successful....</p>");
                
            conn.close();
          
        }
        catch (Exception e) {
            out.println(e);
            e.printStackTrace();
        }
    }
} 
