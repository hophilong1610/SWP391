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
@WebServlet(name="ShowCartControl", urlPatterns={"/show"})
public class ShowCartControl extends HttpServlet {
   
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
            out.println("<title>Servlet ShowCartControl</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowCartControl at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        double bill = 0, sum = 0, price = 0;
        int id = a.getId();
        DAO dao = new DAO();
        Cart c = dao.geCartByID(id);
        List<Product> product = new ArrayList<>();
        List<productCart> p = dao.geProductCartByID(c.getCartId());      
        for (productCart productcart: p) {
            Product pro =  dao.getProductByID(String.valueOf(productcart.getPid()));
            product.add(pro);
        }
        
        for (productCart pro: p) {
            for (Product product1: product) {
                if (product1.getId() == pro.getPid()) {
                    sum = product1.getPrice() * pro.getAmount();
                    price = price + sum;
                }
            }
        }
//        vat = (price * 10)/100;
        bill = price ;
        DecimalFormat df = new DecimalFormat("#.##");
        bill = Double.parseDouble(df.format(bill));
        request.setAttribute("p", p);
//        request.setAttribute("vat", vat);
        request.setAttribute("price", price);
        request.setAttribute("bill", bill);
        request.setAttribute("product", product);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
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
        processRequest(request, response);
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
