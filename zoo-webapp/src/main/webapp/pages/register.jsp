<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:basic-layout title="Register">
    <form action="../RegisterController" method="post">
        <div class="form-group">
            <label for="name">Name</label>
            <input required name="name" type="text" class="form-control" id="name"
                   placeholder="Name"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input required name="password" type="password" class="form-control" id="password"
                   placeholder="Password"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input required name="email" type="email" class="form-control" id="email"
                   placeholder="Email"/>
        </div>
        <button id="submit" type="submit" class="btn btn-primary">Submit</button>
    </form>
</t:basic-layout>