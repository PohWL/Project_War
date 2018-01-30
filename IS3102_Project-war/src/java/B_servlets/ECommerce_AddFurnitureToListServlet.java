/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import EntityManager.CountryEntity;
import HelperClasses.Member;
import HelperClasses.ShoppingCartLineItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author junwe
 */
public class ECommerce_AddFurnitureToListServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            
            ShoppingCartLineItem item = new ShoppingCartLineItem();
            ArrayList<ShoppingCartLineItem> shoppingCart = new ArrayList<ShoppingCartLineItem>();
            String sku = request.getParameter("SKU");
            String category = request.getParameter("category");
            String fromJsp = request.getParameter("fromJsp");
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            int wantedQty = Integer.parseInt(request.getParameter("quantity"));
            int qty = getQuantity(Long.parseLong("10001"), sku);
            
            if(qty > 0){
                if(wantedQty <= qty) {
                    item.setId(id);
                    item.setSKU(sku);
                    item.setPrice(Double.parseDouble(request.getParameter("price")));
                    item.setName(name);
                    item.setImageURL(request.getParameter("imageURL"));

                    if (session.getAttribute("shoppingCart") == null) {
                        item.setQuantity(1);
                        shoppingCart.add(item);
                        session.setAttribute("shoppingCart", shoppingCart);
                    } else {
                        shoppingCart = (ArrayList<ShoppingCartLineItem>) session.getAttribute("shoppingCart");
                        boolean counter = false;
                        for(int i = 0; i < shoppingCart.size(); i++){
                            if(shoppingCart.get(i).getSKU().equals(sku)){
                                shoppingCart.get(i).setQuantity(shoppingCart.get(i).getQuantity() + 1);
                                counter = true;
                            }
                        }
                        if(!counter){
                            item.setQuantity(1);
                            shoppingCart.add(item);
                            session.setAttribute("shoppingCart", shoppingCart);
                        }

                        session.setAttribute("shoppingCart", shoppingCart);
                    }

                    if(fromJsp.equals("yes")){
                        response.sendRedirect("/IS3102_Project-war/B/SG/furnitureCategory.jsp?cat=" + URLEncoder.encode(category, "UTF-8") + "&goodMsg=Successfully added!");
                    }
                    else{
                        response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?goodMsg=Successfully added!");
                    }
                } else {
                    if(!fromJsp.equals("yes")){
                        response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=There only " + qty + " quantity left for \"" + name + "\"");
                    }
                }
                
            }
            else{
                if(fromJsp.equals("yes")){
                    response.sendRedirect("/IS3102_Project-war/B/SG/furnitureCategory.jsp?cat=" + URLEncoder.encode(category, "UTF-8") + "&errMsg=Item not added to cart, not enough quantity available.");
                }
                else{
                    response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Item not added to cart, not enough quantity available.");
                }
            }
            
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
        }
    }
    
    public int getQuantity(Long storeID, String SKU) {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target("http://localhost:8080/IS3102_WebService-Student/webresources/entity.storeentity")
                    .path("getQuantity")
                    .queryParam("storeID", storeID)
                    .queryParam("SKU", SKU);
            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            if (response.getStatus() != 200) {
                return 500;
            }
            String result = (String) response.readEntity(String.class);
            return Integer.parseInt(result);

        } catch (Exception e) {
            e.printStackTrace();
            return 800;
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
