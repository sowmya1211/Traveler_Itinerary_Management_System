import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
  
  

public class SessionInitiateServlet extends HttpServlet{
      
    public void doGet(HttpServletRequest request, HttpServletResponse response){  
        try{
            HttpSession session = request.getSession(true);
            session.setAttribute("uid", request.getParameter("uid"));
            session.setAttribute("utype", request.getParameter("utype"));
            int i = Integer.parseInt(request.getParameter("new_user"));
            
            PrintWriter out = response.getWriter();
            //out.println(i + " "+ (i==1));
            if(request.getParameter("utype").equals("Traveler") && i==0){
               RequestDispatcher rd = request.getRequestDispatcher("trav_hmpg.html");
               rd.forward(request, response); 
            }
            else if(request.getParameter("utype").equals("Traveler") && i==1){
                RequestDispatcher rd = request.getRequestDispatcher("profile_details.html");
                rd.forward(request, response); 
            }
            else if(request.getParameter("utype").charAt(0)=='A'){
                RequestDispatcher rd = request.getRequestDispatcher("admin_hmpg.html");
                rd.forward(request, response);
            }
            if(request.getParameter("utype").equals("Guide") && i==0){
                RequestDispatcher rd = request.getRequestDispatcher("guide_hmpg.html");
                rd.forward(request, response); 
            }
            else if(request.getParameter("utype").equals("Guide") && i==1){
                RequestDispatcher rd = request.getRequestDispatcher("profile_details.html");
                rd.forward(request, response); 
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }   
    }  
}
