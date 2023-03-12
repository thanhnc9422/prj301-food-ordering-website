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
public class ConfirmEdit extends HttpServlet {

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
        String alert = "";
        int fid = Integer.parseInt(request.getParameter("fid"));
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String cid = request.getParameter("cid");
        String img = request.getParameter("img");
        String cp = request.getParameter("cp");
        AccountUser a = (AccountUser) session.getAttribute("account");

        if (a.getPassword().equals(dao.getMd5(cp))) {
            if (!name.equals("")) {
                dao.ChangeInfoItem("Name", name, fid);
            }
            if (!price.equals("")) {
                dao.ChangeInfoItem("Price", price, fid);
            }
            if (!cid.equals("")) {
                dao.ChangeInfoItem("Subcategory", cid, fid);
            }
            if (!img.equals("")) {
                dao.ChangeInfoItem("images", img, fid);
            }
            alert = "Xác nhận thay đổi!";
        } else {
            alert = "Mật khẩu xác nhận sai!";
        }
        request.setAttribute("alert", alert);
        dao.LoadProductEdit(fid);
        request.setAttribute("dao", dao);
        request.getRequestDispatcher("Views/EditItem.jsp").forward(request, response);
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
