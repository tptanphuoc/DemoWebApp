/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Account;
import entity.Cart;
import entity.Order;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import view.DAO;

/**
 *
 * @author huynh
 */
public class CheckOutController extends HttpServlet {

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
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        
        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
        
        Account account = (Account) request.getSession().getAttribute("account");
        
        if(cart_list != null && account != null){ //trường hợp giỏ hàng có sp và đã login
            for (Cart c : cart_list) {
                Order order = new Order();
                order.setpID(c.getId());
                order.setuID(account.getAccID());
                order.setQuantities(c.getAmount());
                order.setDate(sdf.format(date));
                
                DAO dao = new DAO();
                dao.insertOrder(order);
                
                int quantitiesLeft = dao.getQuantity(c.getId()) - c.getAmount(); //số lượng còn lại = sl trong SQL - sl mua
                dao.updateQuantity(c.getId(), quantitiesLeft); //sau khi mua thì update lại sl trong SQL
                
            }
            request.setAttribute("alert", "Mua thành công!");
            cart_list.clear();
            request.getRequestDispatcher("ShoppingCart.jsp").forward(request, response);
        } else {
            if(account == null){ //chưa login
                request.setAttribute("alert", "Bạn chưa login!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
            else    request.setAttribute("alert", "Chưa có sản phẩm nào trong giỏ!"); //đã login nhưng chưa có sp nào trong giỏ
                    request.getRequestDispatcher("ShoppingCart.jsp").forward(request, response);
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
