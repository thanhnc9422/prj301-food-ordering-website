/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.dao;
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
public class CheckCA extends HttpServlet {

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
        HttpSession session = request.getSession();
        String usernameca = request.getParameter("usernameca");
        String passwordca = request.getParameter("passwordca");
        String lastnameca = request.getParameter("lastnameca");
        String firstnameca = request.getParameter("firstnameca");
        String phonenumberca = request.getParameter("phonenumberca");
        String emailca = request.getParameter("email");

        if (dao.CheckCA(usernameca) != null || dao.CheckEmail(emailca) != null) {
            request.setAttribute("checkedca", "tên tài khoản hoặc địa chỉ email đã tồn tại!");
            request.getRequestDispatcher("createaccount").forward(request, response);
        } else {
            if (passwordca.length() < 8) {
                request.setAttribute("checkedca", "mật khẩu ít nhất 8 ký tự!");
                request.getRequestDispatcher("createaccount").forward(request, response);
            }else{
            String pin = dao.getRandom();
            session.setAttribute("pin", pin);
            session.setAttribute("usernameca", usernameca);
            session.setAttribute("passwordca", passwordca);
            session.setAttribute("lastnameca", lastnameca);
            session.setAttribute("firstnameca", firstnameca);
            session.setAttribute("phonenumberca", phonenumberca);
            session.setAttribute("emailca", emailca);
            String subject = "Verify you account";
            String message = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>JSP Page</title>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + session.getAttribute("pin")
                    + "    </body>\n"
                    + "</html>\n"
                    + "";
            dao.SendMail(emailca, subject, message, "thanhnche160277@fpt.edu.vn", "we123456");
            request.getRequestDispatcher("Views/Verify.jsp").forward(request, response);
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
