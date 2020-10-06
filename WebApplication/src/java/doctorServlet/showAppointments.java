/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import patientServlet.loginServlet;
//import javax.servlet.annotation.WebServlet;


/**
 *
 * @author Amy
 */
public class showAppointments extends HttpServlet {
    
    Connection conn = null;
    PreparedStatement prepared_stmt= null;
    ResultSet rs = null;
    public String patientAMKA = null;
    
    /*
    private DataSource datasource = null;
    public void init() throws ServletException{
	try {
            InitialContext ctx = new InitialContext();
            datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
        }
        catch(Exception e) {
               throw new ServletException(e.toString());
        }
    }
    
    	<Resource name="jdbc/LiveDataSource" auth="Container"
        driverClassName="com.mysql.jdbc.Driver"
        type="javax.sql.DataSource"
        username="root"
        password="test123"
        url="jdbc:mysql://localhost:3306/meddb"
        maxActive="8"
        </Resource>
    */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meddb","root", "test123");
                HttpSession session=request.getSession();
                String sql = "SELECT * FROM appointments  WHERE DoctorAMKA = "+session.getAttribute("doctorAMKA");

                prepared_stmt = conn.prepareStatement(sql);
                rs = prepared_stmt.executeQuery();
                
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ραντεβού Τρέχοντος Μήνα</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<tr>");
            out.println("<td>Date   </td>");
            out.println("<td>Patient's AMKA</td>");
            out.println("</tr>");
                while ( rs.next() ) {
                    out.println("</br>");
                    out.println("<tr>");
                    out.println("<td>"+rs.getString("date")+" </td>");
                    out.println("<td>"+rs.getString("patientAMKA")+"</td>");
                    out.println("</tr>");
                }  
            out.println("</br><a href=\"/WebApplication/non_available.html\">Καταχώρηση Διαθεσιμότητας</a></br>");
            out.println("<a href=\"/WebApplication/showAppointments\">Προβολή Ραντεβού</a></br>");
            out.println("<a href=\"/WebApplication/pick_date.html\">Ακύρωση Ραντεβού</a></br>");
            out.println("<a href=\"/WebApplication/logoutServlet\">Αποσύνδεση</a></br>");
            out.println("</body>");
            out.println("</html>");
            }
            catch (SQLException ex) {
          
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Oops...</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Παρακαλώ ελέγξτε τα στοιχεία που είσαγατε</h1>");
                out.println("<a href=\"/WebApplication/non_available.html\">Καταχώρηση Διαθεσιμότητας</a></br>");
                out.println("<a href=\"/WebApplication/showAppointments\">Προβολή Ραντεβού</a></br>");
                out.println("<a href=\"/WebApplication/pick_date.html\">Ακύρωση Ραντεβού</a></br>");
                out.println("<a href=\"/WebApplication/logoutServlet\">Αποσύνδεση</a></br>");
                out.println("</body>");
                out.println("</html>");
            }
 
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
        processRequest(request, response);
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
        processRequest(request, response);
        
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
