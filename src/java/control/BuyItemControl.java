/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import dao.DAO;
import entity.Account;
import entity.Cart;
import entity.Product;
import entity.productCart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
@WebServlet(name="BuyItemControl", urlPatterns={"/buy"})
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class BuyItemControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuyItemControl</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyItemControl at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        double sum = 0, bill = 0;
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        String address = request.getParameter("address");
        String phone = request.getParameter("number");
        List<Product> product = new ArrayList<>();
        List<productCart> p = new ArrayList<>();
        int id = a.getId();
        DAO dao = new DAO();
        Cart c = dao.geCartByID(id);
        p =  dao.geProductCartByID(c.getCartId());
        for (productCart pro: p) {
            Product product1 = dao.getProductByID(String.valueOf(pro.getPid()));
            product.add(product1);
        }
        
        for (productCart pro: p) {
            for (Product product2: product) {
                if (product2.getId() == pro.getPid()) {
                    sum = product2.getPrice() * pro.getAmount();
                    bill = bill + sum;
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        bill = Double.parseDouble(df.format(bill));
        dao.insertOrder(c.getCartId(), address, phone, bill);
        dao.editProductCart(c.getCartId());
        response.sendRedirect("display");
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
