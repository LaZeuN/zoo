package hu.zoo.controller;

import hu.zoo.dao.AdoptionDAO;
import hu.zoo.dao.AdoptionDAOImpl;
import hu.zoo.dao.AnimalDAO;
import hu.zoo.dao.AnimalDAOImpl;
import hu.zoo.model.Adoption;
import hu.zoo.model.Animal;
import hu.zoo.model.Supporter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdoptionController")
public class AdoptionController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AdoptionDAO adoptionDAO = new AdoptionDAOImpl().getInstance();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Supporter supporter=(Supporter)req.getSession().getAttribute("currentSupporter");
        List<Adoption> all = adoptionDAO.findAllBySupporterId(supporter.getId());
        req.setAttribute("adoptionList", all);
    }
}
