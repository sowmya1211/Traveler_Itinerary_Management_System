import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class BudgEditDB extends HttpServlet{
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
        String title = "Insert and View Budget";

		try{
                
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //Finding No of Budgets
            String sql = "SELECT * FROM Budget WHERE trip_id='"+request.getParameter("trip_id")+"'";
            Statement st_c = conn.createStatement();
			ResultSet rs1 = st_c.executeQuery(sql);
            int budg_id = 1;
            while(rs1.next())
              budg_id++;
            st_c.close(); 
            rs1.close();
            
            String b = String.valueOf(budg_id);
            //Insert into Table   
			PreparedStatement st_ins = conn.prepareStatement("Insert into Budget values(?, ?, ?, ?)");
            st_ins.setString(1, b);
			st_ins.setString(2, request.getParameter("trip_id"));
			st_ins.setString(3, request.getParameter("group_name"));
            st_ins.setFloat(4, Float.parseFloat(request.getParameter("amt")));
			st_ins.executeUpdate();
            st_ins.close();
            conn.close(); 
            out.println("<p>Insert Successful....</p>"); 
          
        }
        catch (Exception e) {
            out.println(e);
            e.printStackTrace();
        }
    } 
} 