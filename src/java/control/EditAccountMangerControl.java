/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author lenovo
 */
@WebServlet(name = "EditAccountMangerControl", urlPatterns = {"/EditAccountMangerControl"})
public class EditAccountMangerControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        
        try {
            DAO dao = new DAO();
            Account account = dao.getAccountByID(Integer.parseInt(id));
            request.setAttribute("detail", account);
            request.getRequestDispatcher("EditManagerAccount.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditAccountControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        
        DAO dao = new DAO();
        dao.editProfile(name, user,id);
        response.sendRedirect("managerA");
    }

}
