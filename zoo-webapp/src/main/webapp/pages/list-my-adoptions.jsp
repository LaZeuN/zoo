<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:include page="/AdoptionController"/>
<% request.getSession().getAttribute("currentSupporter"); %>
<t:basic-layout-menu title="List My Adoptions">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Adopted Animal</th>
            <th scope="col">Adoption date</th>
            <th scope="col">Type of support</th>
            <th scope="col">Amount of support</th>
            <th scope="col">Frequency of support</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${requestScope.adoptionList}">
            <tr>
                <td>${item.supported}</td>
                <td>${item.adoptionDate}</td>
                <td>${item.typeOfSupport}</td>
                <td>${item.amountOfSupport}</td>
                <td>${item.frequencyOfSupport}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:basic-layout-menu>