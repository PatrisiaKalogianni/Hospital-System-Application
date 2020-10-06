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

/**
 *
 * @author Amy
 */
public class cancelAppointment extends HttpServlet{

    Connection conn = null;
    PreparedStatement prepared_stmt= null;
    ResultSet rs = null;
    int today = 4;
    String da;
    
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String patientAMKA;
        PrintWriter out = response.getWriter();          
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meddb","root", "test123");

            String date = request.getParameter("date");
            
            if (Integer.parseInt(date)-3< today){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Oops...</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Το ραντεβού δεν μπορεί να ακυρωθεί</h1>");
                out.println("<a href=\"/WebApplication/non_available.html\">Καταχώρηση Διαθεσιμότητας</a></br>");
                out.println("<a href=\"/WebApplication/showAppointments\">Προβολή Ραντεβού</a></br>");
                out.println("<a href=\"/WebApplication/pick_date.html\">Ακύρωση Ραντεβού</a></br>");
                out.println("<a href=\"/WebApplication/logoutServlet\">Αποσύνδεση</a></br>");
                out.println("</body>");
                out.println("</html>");
            }
            else if(Integer.parseInt(date)>30||Integer.parseInt(date)<1){
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
            else{
                
            patientAMKA = request.getParameter("patientAMKA");
                    
            HttpSession session=request.getSession();
            
            da = (String)session.getAttribute("doctorAMKA");

            String sql = "DELETE FROM appointments WHERE DoctorAMKA = '"+da+"' AND patientAMKA = '"+patientAMKA+ " ' AND date = '"+date+"'";
                
            prepared_stmt = conn.prepareStatement(sql);
            prepared_stmt.executeUpdate();
            
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Cancel Appointments</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Το ραντεβού σας έχει πλέον ακυρωθεί</h1>");
                out.println("<a href=\"/WebApplication/non_available.html\">Καταχώρηση Διαθεσιμότητας</a></br>");
                out.println("<a href=\"/WebApplication/showAppointments\">Προβολή Ραντεβού</a></br>");
                out.println("<a href=\"/WebApplication/pick_date.html\">Ακύρωση Ραντεβού</a></br>");
                out.println("<a href=\"/WebApplication/logoutServlet\">Αποσύνδεση</a></br>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(cancelAppointment.class.getName()).log(Level.SEVERE, null, ex);
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
