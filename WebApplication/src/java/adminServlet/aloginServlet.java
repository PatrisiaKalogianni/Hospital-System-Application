package adminServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.security.MessageDigest;
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
import javax.xml.bind.DatatypeConverter;
//import adminServlet.aloginServlet;

public class aloginServlet extends HttpServlet{


    Connection conn = null;
    PreparedStatement prepared_stmt= null;
    ResultSet rs = null;
    String id;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param inputBytes
     * @param algorithm
     * @param request servlet request
     * @param response servlet response
     * @return 
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //avelable algorithms MD2,MD5, SHA..
    public static String getHash(byte[] inputBytes, String algorithm){
        String hashValue ="";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            byte[] digestBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
            
        }
        catch(Exception e){
            
        }
        return hashValue;
    }
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        //String name = null;
        try (PrintWriter out = response.getWriter()) {
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            //String pass = (getHash(password.getBytes(), "MD5"));
            // Boss2 pw:boss = ceb8447cc4ab78d2ec34cd9f11e4bed2

    
   
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(aloginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meddb","root", "test123");
            
            //to chech hashed password we shall use pass instead of password!!!!!
            String sql = "SELECT * FROM admin WHERE username = '" +username+ "' AND password = '"+password+"'";
        
            prepared_stmt = conn.prepareStatement(sql);
            //prepared_stmt.setString(1, username);
            rs = prepared_stmt.executeQuery();
            while ( rs.next() ) {
            id = rs.getString("id");
            }
            }
            catch (SQLException ex) {
          
            Logger.getLogger(aloginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("<!DOCTYPE html >");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >\n" +
                    "<title>ΔΙΑΧΕΙΡΙΣΤΗΣ " + id + "</title>\n" +
                    "</head>\n" + 
                    "<h1>ΔΙΑΧΕΙΡΙΣΤΗΣ " +id+ "</h1>" +
                    "<img src=\"C:\\Users\\Public\\Pictures\\med\\IMG_20170618_143934.png\"/>\n" +
                    "<body style=\"background-color:#F5F5DC\">\n" +
                    "    <br />\n" +
                    "	<a href=\"/WebApplication/Insert_doctor.html\"> Εισαγωγή νέου Ιατρού</a></br>\n" +
                    "	<br />\n" +
                    "	<a href=\"/WebApplication/Insert_patient.html\"> Εισαγωγή νέου Ασθενή</a></br>\n" +
                    "	<br />\n" +
                    "	<a href=\"/WebApplication/admin_delete_doctor.html\">Διαγραφή Ιατρού</a></br>\n" +
                    "	<br />\n" + 
                    "<a href=\"/WebApplication/index.html\">Logout</a></br>"+
                    "</body>\n");            
            out.println("</html>");
        }
        
        HttpSession session=request.getSession();  
        session.setAttribute("id",id);
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
