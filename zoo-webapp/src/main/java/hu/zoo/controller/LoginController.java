package hu.zoo.controller;

import hu.zoo.dao.SupporterDAO;
import hu.zoo.dao.SupporterDAOImpl;
import hu.zoo.model.Supporter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;


    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        SupporterDAO supporterDAO = SupporterDAOImpl.getInstance();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Supporter supporter = supporterDAO.login(email, password);

        if( supporter == null){
            response.sendRedirect("pages/login.jsp");
            return;
        }

        request.getSession().setAttribute("currentSupporter", supporter);
        response.sendRedirect("pages/list-animals.jsp");
    }
}
