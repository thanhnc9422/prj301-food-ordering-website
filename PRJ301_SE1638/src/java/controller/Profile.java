/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.dao;
import entity.AccountUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author 84961
 */
public class Profile extends HttpServlet {

    dao dao;

    public void init() {
        dao = new dao();
    }

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
        request.getRequestDispatcher("Views/Profile.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String alert = "";
        String username = request.getParameter("username");
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String password = request.getParameter("password");
        String phonenumber = request.getParameter("phonenumber");
        String cp = request.getParameter("cp");
        AccountUser a = (AccountUser) session.getAttribute("account");
       if(password.length()<8){
        alert = "mật khẩu ít nhất 8 ký tự!";
       }else{
        if (a.getPassword().equals(dao.getMd5(cp))) {
            if (!username.equals("")) {
                dao.ChangeInfo("UserName", username, a.getUserID());
            }
            if (!lastname.equals("")) {
                dao.ChangeInfo("LastName", lastname, a.getUserID());
            }
            if (!firstname.equals("")) {
                dao.ChangeInfo("FirstName", firstname, a.getUserID());
            }
            if (!password.equals("")) {
                dao.ChangeInfo("Password", dao.getMd5(password), a.getUserID());
            }
            if (!phonenumber.equals("")) {
                dao.ChangeInfo("PhoneNumber", phonenumber, a.getUserID());
            }
            alert = "Xác nhận thay đổi!";
        } else {
            alert = "Mật khẩu xác nhận sai!";
        }
       }
       

        String id = a.getUserName();
        AccountUser b = dao.LoadUser(id);
        session.removeAttribute("account");
        session.setAttribute("account", b);
        request.setAttribute("alert", alert);
        request.getRequestDispatcher("Views/Profile.jsp").forward(request, response);
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
