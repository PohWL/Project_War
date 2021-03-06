/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import HelperClasses.ShoppingCartLineItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author junwe
 */
public class ECommerce_RemoveItemFromListServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            
            ShoppingCartLineItem item = new ShoppingCartLineItem();
            ArrayList<ShoppingCartLineItem> shoppingCart = new ArrayList<ShoppingCartLineItem>();
            ArrayList<ShoppingCartLineItem> removeList = new ArrayList<ShoppingCartLineItem>();
            shoppingCart = (ArrayList<ShoppingCartLineItem>) session.getAttribute("shoppingCart");
            String sku = request.getParameter("SKU");
            String[] skuArr;
            skuArr = sku.split(":");
            
            for(String singleSku : skuArr){
                for (ShoppingCartLineItem cartItem : shoppingCart) {
                    if (cartItem.getSKU().equals(singleSku)) {
                        removeList.add(cartItem);
                    }
                }
            }
            
            shoppingCart.removeAll(removeList);
            session.setAttribute("shoppingCart", shoppingCart);
            
            response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?goodMsg=Successfully removed!");
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
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
