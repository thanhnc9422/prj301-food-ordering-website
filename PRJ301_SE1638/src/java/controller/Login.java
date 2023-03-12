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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author 84961
 */
public class Login extends HttpServlet {

    dao dao;

    public void init() {
        dao = new dao();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("name");
        String password = request.getParameter("password");
//        int pin = dao.PinCode();
//        request.setAttribute("pin", pin);
        HttpSession session = request.getSession();
//        session.setAttribute("pin", pin);
//        String usernameca = request.getParameter("usernameca");
//        String passwordca = request.getParameter("passwordca");
//        String lastnameca = request.getParameter("lastnameca");
//        String firstnameca = request.getParameter("firstnameca");
//        String phonenumberca = request.getParameter("phonenumberca");
        AccountUser a = dao.LoadUser(user, dao.getMd5(password));
//        if (usernameca == null && passwordca == null && lastnameca == null && firstnameca == null && phonenumberca == null) {
            if (a == null) {
                
                request.setAttribute("checked", "Invalid login, please try again");
                request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
            } else {
                dao.LoadProduct();
                dao.LoadCategory();
               
                session.setAttribute("account", a);
                request.getRequestDispatcher("home").forward(request, response);

            }
//        } else {
//                
//            if (dao.CheckCA(usernameca) != null) {
//                request.setAttribute("checkedca", "tên tài khoản đã tồn tại!");
//                request.setAttribute("checkdisplay", "1");
//                request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
//            } else {
//                dao.Insert("kh" + dao.totalAccount(), usernameca, passwordca, lastnameca, firstnameca, phonenumberca);
//                request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
//            }
//
//        }
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
//    int count = 0;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String user = request.getParameter("name");
//        String password = request.getParameter("password");
//        dao.LoadUser(user, password);
//        if (count >= 1) {
//            String check = (dao.getUser().size() == 0) ? "Invalid login, please try again" : "";
//            request.setAttribute("check", check);
//        }
//        count++;
        request.getRequestDispatcher("Views/Login.jsp").forward(request, response);

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
