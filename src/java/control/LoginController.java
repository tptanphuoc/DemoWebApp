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
import javax.servlet.http.HttpSession;
import view.DAO;

/**
 *
 * @author huynh
 */ 
public class LoginController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");// set mã encoding để xử lý đc tiếng Việt
        
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        
        DAO dao = new DAO();
        
        Account acc = dao.login(username, password);
        if (acc == null) {      // ko tìm thấy account nào thì trả về string lỗi
            request.setAttribute("error1", "Incorrect username or password!"); // string lỗi
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else{
            HttpSession session = request.getSession(); //khởi tạo session
            session.setAttribute("account", acc); // nếu login oke =>đẩy account lên session
            request.getRequestDispatcher("home-controller").forward(request, response); //xài Cách 1, nếu ko cần gửi dữ liệu
//            response.sendRedirect("home-controller"); //xài Cách 2, nếu có dữ liệu cần gửi
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
