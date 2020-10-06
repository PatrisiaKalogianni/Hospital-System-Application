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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import patientServlet.loginServlet;
import mainpackage.Doctor;

/**
 *
 * @author Amy
 */
public class dloginServlet extends HttpServlet {

    Connection conn = null;
    PreparedStatement prepared_stmt= null;
    ResultSet rs = null;
    public String surname = null;
    public String AMKA = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meddb","root", "test123");
            
            
            String sql = "SELECT * FROM doctor WHERE username = '" +username+ "' AND password = '"+password+"'";
        
            prepared_stmt = conn.prepareStatement(sql);
            rs = prepared_stmt.executeQuery();
            while ( rs.next() ) {
                surname = rs.getString("surname");
                AMKA = rs.getString("doctorAMKA");
            }
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
                out.println("<a href=\"/WebApplication/index.html\">Επιστροφή στην Αρχική Σελίδα</a></br>");
                out.println("</body>");
                out.println("</html>");
            }
            //Doctor d = new Doctor(AMKA);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Welcome</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Καλως ήρθατε dr. " + surname + "</h1>");
            out.println("<a href=\"/WebApplication/non_available.html\">Καταχώρηση Διαθεσιμότητας</a></br>");
            out.println("<a href=\"/WebApplication/showAppointments\">Προβολή Ραντεβού</a></br>");
            out.println("<a href=\"/WebApplication/pick_date.html\">Ακύρωση Ραντεβού</a></br>");
            out.println("<a href=\"/WebApplication/logoutServlet\">Αποσύνδεση</a></br>");
            out.println("</body>");
            out.println("</html>");

            
           HttpSession session=request.getSession();  
           session.setAttribute("doctorAMKA",AMKA);
            
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
