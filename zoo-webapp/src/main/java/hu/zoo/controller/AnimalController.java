package hu.zoo.controller;

import hu.zoo.dao.AnimalDAO;
import hu.zoo.dao.AnimalDAOImpl;
import hu.zoo.model.Animal;
import hu.zoo.model.Supporter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AnimalController")
public class AnimalController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AnimalDAO animalDAO = new AnimalDAOImpl().getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Supporter currentSupporter = (Supporter) req.getSession().getAttribute("currentSupporter");
        List<Animal> all = animalDAO.findAll();
        req.setAttribute("animalList", all);
    }
}
