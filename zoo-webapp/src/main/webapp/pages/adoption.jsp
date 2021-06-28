<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/AnimalController"/>
<% request.getSession().getAttribute("currentSupporter"); %>
<% request.setAttribute("supporter_id", request.getParameter("supporterId")); %>
<% request.setAttribute("supporterName", request.getParameter("supporterName")); %>
<% request.setAttribute("animal_id", request.getParameter("animalId")); %>
<% request.setAttribute("animalName", request.getParameter("animalName")); %>
<% request.setAttribute("animalSpecies", request.getParameter("animalSpecies")); %>
<% request.setAttribute("animalIntroduction", request.getParameter("animalIntroduction")); %>
<% request.setAttribute("animalYearOfBirth", request.getParameter("animalYearOfBirth")); %>
<t:basic-layout title="Adoption">
    <form action="../DoAdoptionController" method="post">
            <input type="hidden" name="supporterId" value="${supporter_id}"/>
            <input type="hidden" name="animalId" value="${animal_id}"/>
            <input type="hidden" name="supporterName" value="${supporterName}"/>
            <div class="form-group">
                <c:choose>
                    <c:when test="${empty fn:trim(animalName)}">
                        <label for="animalName"><b>Állat neve: </b></label>
                        <input required name="animalName" type="text" class="form-control" id="animalName" placeholder="Add meg az állat nevét"/>
                    </c:when>
                    <c:otherwise>
                        <label><b>Állat neve: </b>${animalName}</label>
                        <input type="hidden" name="animalName" value="${animalName}"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group">
                <label><b>Faj: </b>${animalSpecies}</label>
                <input type="hidden" name="animalSpecies" value="${animalSpecies}"/>
            </div>
            <div class="form-group">
                <label><b>Bemutatkozás: </b>${animalIntroduction}</label>
                <input type="hidden" name="animalIntroduction" value="${animalIntroduction}"/>
            </div>
            <div class="form-group">
                <label><b>Születési év: </b>${animalYearOfBirth}</label>
                <input type="hidden" name="animalYearOfBirth" value="${animalYearOfBirth}"/>
            </div>
            <div class="form-group">
                <label for="typeOfSupport">Type of support</label>
                <select name="typeOfSupport" id="typeOfSupport">
                    <option value="FOOD(Kg)">FOOD(Kg)</option>
                    <option value="MONEY($)">MONEY($)</option>
                </select>
            </div>
            <div class="form-group">
                <label for="amountOfSupport">Amount of support</label>
                <input required name="amountOfSupport" type="text" class="form-control" id="amountOfSupport"
                       placeholder="Amount of support"/>
            </div>
            <div class="form-group">
                <label for="frequencyOfSupport">Frequency of support</label>
                <select name="frequencyOfSupport" id="frequencyOfSupport">
                    <option value="ONCE">ONCE</option>
                    <option value="REGULAR">REGULAR</option>
                </select>
            </div>
            <button id="submit" type="submit" class="btn btn-primary">Submit</button>
    </form>
</t:basic-layout>