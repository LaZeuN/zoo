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

@WebServlet("/SupporterController")
public class SupporterController extends HttpServlet {

    private SupporterDAO supporterDAO = SupporterDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Supporter supporter = (Supporter) req.getSession().getAttribute("currentSupporter");

        if(req.getParameter("name") != null && !req.getParameter("name").isEmpty()){
            supporter.setName(req.getParameter("name"));
        }

        if(req.getParameter("email") != null && !req.getParameter("email").isEmpty()){
            supporter.setEmail(req.getParameter("email"));
        }

        supporter = supporterDAO.save(supporter);

        req.getSession().setAttribute("currentSupporter", supporter);
        resp.sendRedirect("pages/profile.jsp");
    }
}
