/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import dao.DAO;
import entity.Account;
import entity.Product;
import entity.productCart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.Constant;

public class PaymentControl extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            HttpSession session = req.getSession();
            Account a = (Account) session.getAttribute("acc");
//        String address = request.getParameter("address");
//        String phone = request.getParameter("number");
            String productCardId = req.getParameter("productCardId");
            String totalAmount = req.getParameter("totalPrice");
            DAO dao = new DAO();
            List<Product> product = new ArrayList<>();
            List<productCart> p = new ArrayList<>();

            List<productCart> listOrder = dao.geProductCartByID(Integer.parseInt(productCardId));

            Transaction listTransaction = getTransactionInformation(listOrder, totalAmount);

            APIContext apiContext = new APIContext(Constant.CLIENT_ID, Constant.CLIENT_SECRET, Constant.CLIENT_MODE);

            Payment payment = new Payment();
            payment.setIntent("sale");

            Payer payer = getPayerInformation(a);
            payment.setPayer(payer);

            RedirectUrls redirectUrls = getRedirectURLs();
            payment.setRedirectUrls(redirectUrls);
            payment.setTransactions(Collections.singletonList(listTransaction)); // Set a single transaction

            Payment approvedPayment = payment.create(apiContext);

            String link = getApprovalLink(approvedPayment);
            res.sendRedirect(link);
        } catch (PayPalRESTException ex) {
            Logger.getLogger(PaymentControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Transaction getTransactionInformation(List<productCart> productList, String bill) {
        List<Item> items = new ArrayList<>();
        ItemList itemList = new ItemList();
        Transaction transaction = new Transaction();
        DAO dao = new DAO();
        Amount amount = new Amount();

        // Calculate the total amount from the items
//        double totalAmount = Double.parseDouble(bill);

        for (productCart cart : productList) {
            List<Integer> pros = dao.getProductByProductCartId(cart.getCartid() + "");

            for (Integer i : pros) {
                
                Product pro = dao.getProductByID(i + "");
                
                Item item = new Item();
                item.setCurrency("USD");
                item.setName(pro.getName());
                item.setSku(cart.getCartid() + "");
                item.setPrice(pro.getPrice() + "");
                item.setQuantity("1");
               
                items.add(item);
            }
        }

        itemList.setItems(items);
        transaction.setItemList(itemList);

        amount.setCurrency("USD");
        amount.setTotal(bill);
        transaction.setAmount(amount);

        transaction.setDescription("Payment"); // Set an appropriate description here

        return transaction;
    }

    private Payer getPayerInformation(Account a) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(a.getName());

        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/fastfood123/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:8080/fastfood123/paymentConfirm");

        return redirectUrls;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
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
