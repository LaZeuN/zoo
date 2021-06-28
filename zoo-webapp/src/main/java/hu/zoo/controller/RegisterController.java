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

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {

    SupporterDAO dao = SupporterDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Supporter supporter = new Supporter();
        supporter.setName(req.getParameter("name"));
        supporter.setEmail(req.getParameter("email"));
        supporter.setPassword(req.getParameter("password"));

        dao.save(supporter);

        resp.sendRedirect("pages/login.jsp");
    }
}
