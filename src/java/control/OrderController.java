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
public class OrderController extends HttpServlet {

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
        Account account = (Account) request.getSession().getAttribute("account");
        if (account != null) {

            int pID = Integer.parseInt(request.getParameter("id"));
            int pQuantity = Integer.parseInt(request.getParameter("quantity"));

            if (pQuantity <= 0) {
                pQuantity = 1;
            }

            Order orderModel = new Order();
            orderModel.setpID(pID);
            orderModel.setuID(account.getAccID());
            orderModel.setQuantities(pQuantity);
            orderModel.setDate(sdf.format(date));

            DAO dao = new DAO();
            dao.insertOrder(orderModel);

            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list"); //lấy list giỏ hàng hiện tại
            if (cart_list != null) {
                for (Cart c : cart_list) {

                    if (c.getId() == pID) {
                        int quantitiesLeft = dao.getQuantity(pID) - c.getAmount(); //số lượng còn lại = sl trong SQL - sl mua
                        dao.updateQuantity(pID, quantitiesLeft); //sau khi mua thì update lại sl trong SQL
                        cart_list.remove(c); //mua thành công thì bỏ sp ra khỏi giỏ
                        request.setAttribute("alert", "Mua thành công!");
                        request.getRequestDispatcher("ShoppingCart.jsp").forward(request, response);
                        break;
                    }
                }
                
            }

        } else {
            response.sendRedirect("Login.jsp");
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
