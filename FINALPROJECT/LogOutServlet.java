import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogOutServlet extends HttpServlet {
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        session.invalidate();
        out.println("Invalidated");
        response.sendRedirect("login.html");
    }
}
