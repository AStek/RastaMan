<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
    Document   : index
    Created on : 29 трав 2011, 19:35:15
    Author     : andrey
--%>
<%@include file="head.jsp" %>
<%@page import="logic.API.AccountAPI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String login = session.getAttribute("login").toString();
    AccountAPI aAPI = new AccountAPI();
%>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="/NetCracker/home/main.jsp">Главная</a>
                <a href="/NetCracker/home/editing.jsp">Редактировать профиль</a><br>
            </div>
            <div class="block" id="content">
                <h1>Главная</h1><br>
                <label>Имя пользователя:</label>
                <%=aAPI.getInfo(login).get("LOGIN")%><br>
                <label>ФИО:</label>
                <%=aAPI.getInfo(login).get("NAME")%><br>
                <label>Электронная почта:</label>
                <%=aAPI.getInfo(login).get("EMAIL")%><br>
            </div>
        </div>
<%@include file="footer.jsp" %>