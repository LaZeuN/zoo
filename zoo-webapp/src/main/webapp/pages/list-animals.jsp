<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:include page="/AnimalController"/>
<% request.getSession().getAttribute("currentSupporter"); %>
<t:basic-layout-menu title="List Animals">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Species</th>
            <th scope="col">Introduction</th>
            <th scope="col">Year Of Birth</th>
            <th scope="col">Support</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${requestScope.animalList}">
            <tr>
                <td>${item.name}</td>
                <td>${item.species}</td>
                <td>${item.introduction}</td>
                <td>${item.yearOfBirth}</td>
                <td>
                    <a href="adoption.jsp?animalId=${item.id}
                    &supporterId=${sessionScope.currentSupporter.id}
                    &supporterName=${sessionScope.currentSupporter.name}
                    &animalName=${item.name}
                    &animalSpecies=${item.species}
                    &animalIntroduction=${item.introduction}
                    &animalYearOfBirth=${item.yearOfBirth}"><i class="fas fa-edit"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:basic-layout-menu>