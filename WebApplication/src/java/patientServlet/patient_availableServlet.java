/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientServlet;

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

/**
 * 
 */
public class patient_availableServlet extends HttpServlet {
	    Connection conn = null;
	    PreparedStatement prepared_stmt= null;
	    ResultSet rs = null;
	    public String AMKA = null;
	    
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        try {
	            try {
	                Class.forName("com.mysql.jdbc.Driver");
	            } 
	            catch (ClassNotFoundException ex) {
	                    Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
	                }
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meddb","root", "test123");
	            
	                String day = request.getParameter("days");
	                //String [] table = days.split(",");
	                String sql;
	            //FOR THE LENGTH OF THE TABLE
	            //for (int i=0;i>=table.length;i++){
	            HttpSession session=request.getSession();
	           // sql = "INSERT INTO new_appointments VALUES('"+day+"',null,"+(String)session.getAttribute("patientAMKA")+")";
	        
	            prepared_stmt = conn.prepareStatement(sql);
	            rs = prepared_stmt.executeQuery();

	            out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Î Ï�Î¿Î²Î¿Î»Î® Î´Î¹Î±Î¸Î­ÏƒÎ¹Î¼Ï‰Î½ ÎºÎµÎ½ÏŽÎ½ Î³Î¹Î± ÎºÎ»Î­Î¹ÏƒÎ¹Î¼Î¿ Ï�Î±Î½Ï„ÎµÎ²ÏŒÏ…</title>");            
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<h3>Your availability has passed in the system succesfully</h3>");
	            out.println("<a href=\"/WebApplication/patient_available.html\">Î Ï�Î¿Î²Î¿Î»Î® Î´Î¹Î±Î¸Î­ÏƒÎ¹Î¼Ï‰Î½ ÎºÎµÎ½ÏŽÎ½ Î³Î¹Î± ÎºÎ»Î­Î¹ÏƒÎ¹Î¼Î¿ Ï�Î±Î½Ï„ÎµÎ²ÏŒÏ…</a>");
	            out.println("<a href=\"/WebApplication/patient_history.html\">Î Ï�Î¿Î²Î¿Î»Î® Î¹ÏƒÏ„Î¿Ï�Î¹ÎºÎ¿Ï� Ï€Ï�Î¿Î·Î³Î¿Ï�Î¼ÎµÎ½Ï‰Î½ Ï�Î±Î½Ï„ÎµÎ²Î¿Ï�</a>");
	            out.println("<a href=\"/WebApplication/Appoint_patient.html\">ÎšÎ»ÎµÎ¯ÏƒÎ¹Î¼Î¿ Ï�Î±Î½Ï„ÎµÎ²Î¿Ï�</a>");
	            out.println("<a href=\"/WebApplication/delete_appoint_patient.html\">Î‘ÎºÏ�Ï�Ï‰ÏƒÎ· Ï�Î±Î½Ï„ÎµÎ²Î¿Ï�</a>");
	            out.println("<a href=\"/WebApplication/index.html\">Logout</a>");
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
	                out.println("<h1>An error occured. Please check the data you entered</h1>");
	                out.println("<a href=\"/WebApplication/patient_available.html\">Î Ï�Î¿Î²Î¿Î»Î® Î´Î¹Î±Î¸Î­ÏƒÎ¹Î¼Ï‰Î½ ÎºÎµÎ½ÏŽÎ½ Î³Î¹Î± ÎºÎ»Î­Î¹ÏƒÎ¹Î¼Î¿ Ï�Î±Î½Ï„ÎµÎ²ÏŒÏ…</a>");
	                out.println("<a href=\"/WebApplication/patient_history.html\">Î Ï�Î¿Î²Î¿Î»Î® Î¹ÏƒÏ„Î¿Ï�Î¹ÎºÎ¿Ï� Ï€Ï�Î¿Î·Î³Î¿Ï�Î¼ÎµÎ½Ï‰Î½ Ï�Î±Î½Ï„ÎµÎ²Î¿Ï�</a>");
	                out.println("<a href=\"/WebApplication/Appoint_patient.html\">ÎšÎ»ÎµÎ¯ÏƒÎ¹Î¼Î¿ Ï�Î±Î½Ï„ÎµÎ²Î¿Ï�</a>");
	                out.println("<a href=\"/WebApplication/delete_appoint_patient.html\">Î‘ÎºÏ�Ï�Ï‰ÏƒÎ· Ï�Î±Î½Ï„ÎµÎ²Î¿Ï�</a>");
	                out.println("<a href=\"/WebApplication/index.html\">Logout</a>");
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
