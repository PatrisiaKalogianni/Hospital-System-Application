package adminServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import patientServlet.loginServlet;
/**
 *
 * @author Patrisia Kal
 */
public class admn_deleteDoctor extends HttpServlet{
    Connection conn = null;
    PreparedStatement prepared_stmt= null;
    ResultSet rs = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        //String name = null;
        try (PrintWriter out = response.getWriter()) {
            
            //String username = request.getParameter("username");
            String password = request.getParameter("password");
            String dname = request.getParameter("name");
            //String doctorAMKA = request.getParameter("doctorAMKA");
            
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(aloginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meddb","root", "test123");
            
            String sql;
            //FOR THE LENGTH OF THE TABLE
            //for (int i=0;i>=table.length;i++){
            HttpSession session=request.getSession();
            sql = "DELETE FROM doctor WHERE doctorAMKA = '"+(String)session.getAttribute("doctorAMKA")+"' AND password = '"+password+"'";
        
            prepared_stmt = conn.prepareStatement(sql);
            //prepared_stmt.setString(1, username);
            prepared_stmt.executeUpdate();
            //while ( rs.next() ) {
            //  name = rs.getString("name");
            //}
        }
        catch (SQLException ex) {
          
            Logger.getLogger(aloginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet loginServlet</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Doctor " + dname + " has been deleted!</h1>");
        out.println("</body>");
        out.println("</html>");
        }
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(aloginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(aloginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
