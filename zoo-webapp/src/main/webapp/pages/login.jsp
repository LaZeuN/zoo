<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:basic-layout title="Login">
        <form action="../LoginController" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input required name="email" type="email" class="form-control" id="email"
                       placeholder="Email"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input required name="password" type="password" class="form-control" id="password"
                       placeholder="Password"/>
            </div>
            <button id="submit" type="submit" class="btn btn-primary">Submit</button>
            <span><a href="register.jsp">Register</a></span>
        </form>
</t:basic-layout>