/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import view.DAO;

/**
 *
 * @author huynh
 */
public class ProductQuantityController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter();) {

            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));

            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            DAO dao = new DAO();

            if (action != null && id >= 1) {
                if (action.equals("increase")) {
                    for (Cart c : cart_list) {
                        if (c.getId() == id) {
                            if (c.getAmount() < dao.getQuantity(id)) {
                                int quantity = c.getAmount();
                                quantity++;
                                c.setAmount(quantity);
                                response.sendRedirect("ShoppingCart.jsp");
                            } else {
                                request.setAttribute("alert", "Số lượng sản phẩm trong kho đã tối đa, không thể đặt thêm!");
                                request.getRequestDispatcher("ShoppingCart.jsp").forward(request, response);
                            }
                        }

                    }
                }

                if (action.equals("decrease")) {
                    for (Cart c : cart_list) {
                        if (c.getId() == id && c.getAmount() > 1) {
                            int quantity = c.getAmount();
                            quantity--;
                            c.setAmount(quantity);
                            break;
                        }
                    }
                    response.sendRedirect("ShoppingCart.jsp");
                }
            } else {
                response.sendRedirect("ShoppingCart.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
