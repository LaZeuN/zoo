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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@WebServlet("/DoAdoptionController")
public class DoAdoptionController extends HttpServlet {

    private AdoptionDAO adoptionDao = AdoptionDAOImpl.getInstance();
    private AnimalDAO animalDAO = AnimalDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Supporter supporter=(Supporter)req.getSession().getAttribute("currentSupporter");
        String animalIdStr = req.getParameter("animalId").strip();
        int animalId = Integer.parseInt(animalIdStr);
        int supporterId = supporter.getId();



        Adoption adoption = new Adoption();
        adoption.setSupporter(req.getParameter("supporterName").strip());
        adoption.setSupported(req.getParameter("animalName").strip());
        adoption.setAdoptionDate(LocalDate.now());
        adoption.setTypeOfSupport(req.getParameter("typeOfSupport"));
        adoption.setAmountOfSupport(req.getParameter("amountOfSupport"));
        adoption.setFrequencyOfSupport(req.getParameter("frequencyOfSupport"));
        adoption.setSupporterId(supporterId);
        adoption.setAnimalId(animalId);

        Animal animal = new Animal();
        animal.setId(animalId);
        animal.setName(req.getParameter("animalName").strip());
        animal.setSpecies(req.getParameter("animalSpecies").strip());
        animal.setPicture("nincs");
        animal.setIntroduction(req.getParameter("animalIntroduction").strip());
        animal.setYearOfBirth(LocalDate.parse(req.getParameter("animalYearOfBirth").strip()));
        animal.setIsAdopted("1");
        animalDAO.save(animal);

        adoptionDao.save(adoption);

        resp.sendRedirect("pages/list-animals.jsp");
    }

}
