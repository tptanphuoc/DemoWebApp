/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import entity.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import view.DAO;

/**
 *
 * @author huynh
 */
public class SignupController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        
        String username = request.getParameter("user");
        String userPhoneNum = request.getParameter("phone");
        String password = request.getParameter("pass");
        String rePassword = request.getParameter("repass");
      
        DAO dao = new DAO();
        Account accInSQL = dao.checkAccount(username);
        
        //lỗi khi ko nhập hết info đăng ký
        if(username.isEmpty() || userPhoneNum.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
            request.setAttribute("error2", "Please complete all information!"); 
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else 
            //trường hợp đki thành công
            if(password.equals(rePassword) && accInSQL == null){
            dao.signup(username, userPhoneNum, password);
            request.setAttribute("message", "Register successful!");
            request.getRequestDispatcher("Login.jsp").forward(request, response); 
        }
                                
        //lỗi khi nhập sai repassword
        if(password.compareTo(rePassword)!= 0){
            request.setAttribute("error2", "The entered passwords do not match. Try again!"); 
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        
        //lỗi khi username đki trùng với username đã có trong SQL
        if(accInSQL != null){
            request.setAttribute("error2", "Account already existed. Try another username!"); 
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
