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
    

%>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>

            </div>
            <div class="block" id="content">
                <h1>Главная</h1><br>
                <a href="/NetCracker/admin/accounts.jsp"><div class="main_menu_item">
                    <img border="0" title="Аккаунты" src="../img/personal.png"><br>
                    Аккаунты
                </div></a>
                <a href="/NetCracker/admin/roles.jsp"><div class="main_menu_item">
                    <img border="0" title="Роли" src="../img/assistant.png"><br>
                    Роли
                </div></a>
                <a href="/NetCracker/admin/resourses.jsp"><div class="main_menu_item">
                    <img border="0" title="Ресурсы" src="../img/blockdevice.png"><br>
                    Ресурсы
                </div></a>

                <a href="/NetCracker/admin/journal.jsp"><div class="main_menu_item">
                    <img border="0" title="Бронирование" src="../img/ical.png"><br>
                    Бронирование
                </div></a>
                <a href="/NetCracker/out.jsp"><div class="main_menu_item">
                    <img border="0" title="Выход" src="../img/exit.png"><br>
                    Выход
                </div></a>
                
            </div>
        </div>
<%@include file="footer.jsp" %>