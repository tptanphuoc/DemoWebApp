/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Cart;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "CartController", urlPatterns = {"/cart-manager"})
public class CartController extends HttpServlet {

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

        DAO dao = new DAO();
        HttpSession session = request.getSession();
        ArrayList<Cart> cartList = new ArrayList<>();
        int id = Integer.parseInt(request.getParameter("id"));

        Cart cm = new Cart();
        cm.setId(id);
        cm.setAmount(1);

        ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

        if (cart_list == null) { //trường hợp giỏ chưa có sp nào
            if(dao.getQuantity(id) > 0){ //số lượng sp trong SQL phải > 0
            cartList.add(cm);
            session.setAttribute("cart-list", cartList);
            request.setAttribute("alert", "Thêm vào giỏ thành công!");
            request.getRequestDispatcher("home-controller").forward(request, response);
            } else { //nếu số lượng <= 0
                request.setAttribute("alert", "Sản phẩm đã hết hàng!");
                request.getRequestDispatcher("home-controller").forward(request, response);
            }
        }else{
          //trường hợp giỏ đã có sp
            cartList = cart_list;
            boolean exist = false;

            for (Cart c : cart_list) {

                if (c.getId() == id) { //thêm vào giỏ sp đã có từ trước
                    exist = true;

                    if (c.getAmount() < dao.getQuantity(id)) { //từ lần thêm sau, sp trong giỏ cộng 1
                        int quantity = c.getAmount();
                        quantity++;
                        c.setAmount(quantity);
                        request.setAttribute("alert", "Thêm vào giỏ thành công!");
                    } else { //số lượng sp đặt mua vượt quá quantity trong sql
                        request.setAttribute("alert", "Số lượng sản phẩm trong kho đã tối đa, không thể đặt thêm!");
                        request.getRequestDispatcher("home-controller").forward(request, response);
                    }                    
                    request.getRequestDispatcher("home-controller").forward(request, response);
                }
            }
            if (!exist) { //thêm 1 sp khác
                if(dao.getQuantity(id) > 0){ //số lượng sp trong SQL phải > 0
                cartList.add(cm);
                request.setAttribute("alert", "Thêm vào giỏ thành công!");
                request.getRequestDispatcher("home-controller").forward(request, response);
                } else{ //nếu số lượng <= 0
                    request.setAttribute("alert", "Sản phẩm đã hết hàng!");
                    request.getRequestDispatcher("home-controller").forward(request, response);
                    }
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
