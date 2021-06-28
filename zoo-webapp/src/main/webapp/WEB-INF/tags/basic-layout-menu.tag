<%@ tag description="Basic Page template" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<%@ attribute name="title" required="false" type="java.lang.String" %>

<t:basic-layout title="${title}">
    <jsp:attribute name="header">
        <jsp:invoke fragment="header" />
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:invoke fragment="footer" />
    </jsp:attribute>

    <jsp:body>
        <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav w-100">
                    <li class="nav-item"><a class="nav-link"
                                            href="${pageContext.request.contextPath}/pages/list-animals.jsp">List
                        Animals</a></li>
                    <li class="nav-item"><a class="nav-link"
                                            href="${pageContext.request.contextPath}/pages/list-my-adoptions.jsp">List
                        My Adoptions</a></li>

                    <c:if test="${sessionScope.currentSupporter.email != null}">
                        <li class="nav-item dropdown ml-auto">
                            <a class='nav-link dropdown-toggle' href='#' id='navbarDropdownMenuLink' data-toggle='dropdown'
                               aria-haspopup='true' aria-expanded='false'>
                                    ${sessionScope.currentSupporter.name}
                            </a>
                            <div class='dropdown-menu dropdown-menu-right' aria-labelledby='navbarDropdownMenuLink'>
                                <a class='dropdown-item'
                                   href='${pageContext.request.contextPath}/LogoutController'>Logout</a>
                            </div>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
        <jsp:doBody/>
    </jsp:body>
</t:basic-layout>





