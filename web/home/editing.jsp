<%@page import="java.util.HashMap"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
    Document   : index
    Created on : 29 трав 2011, 19:35:15
    Author     : andrey
--%>
<%@include file="head.jsp" %>
<%@page import="logic.API.AccountAPI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="editing">Редактировать профиль</a><br>
                <a href="#"></a>
            </div>
            <div class="block" id="content">
                <style>
                    label{
                        width: 200px;
                    }
                </style>
                <%
                    String login       = session.getAttribute("login").toString();
                    String password    = session.getAttribute("password").toString();
                    AccountAPI aapi = new AccountAPI();
                    HashMap info = aapi.getInfo(login);
                    if (request.getParameter("FIO")!=null){
                        if (aapi.setById(
                                info.get("ID").toString(),
                                info.get("LOGIN").toString(),
                                info.get("PASSWORD").toString(),
                                request.getParameter("FIO").toString(),
                                info.get("ROLE_ID").toString(),
                                request.getParameter("email").toString())
                        ){info = aapi.getInfo(login);
                        %>
                            <div class="attention">Изменения успешно внесены</div>
                        <%}else{%>
                            <div class="error_mess">Ошибка изменения данных</div>
                        <%}
                    }
                %>
                <form action="editing.jsp" method="POST">
                    <fieldset title="Информация профиля">
                        <legend>Информация профиля</legend>
                        <label>ФИО:</label>
                        <input type="text" name="FIO" value="<%=(info.get("NAME"))%>"><br>
                        <label>Электронная почта:</label>
                        <input type="text" name="email" value="<%=(info.get("EMAIL"))%>"><br>
                        <label></label><input type="submit" value="Сохранить">
                    </fieldset>
                </form>

                <%
                    if ((request.getParameter("newPwd")!=null)
                            &&
                         (request.getParameter("newPwd").toString()
                            .equals(request.getParameter("confPwd").toString()))
                       ){
                        if (aapi.setById(
                                info.get("ID").toString(),
                                info.get("LOGIN").toString(),
                                request.getParameter("newPwd").toString(),
                                info.get("NAME").toString(),
                                info.get("ROLE_ID").toString(),
                                info.get("EMAIL").toString())
                        ){
                            session.setAttribute("password", request.getParameter("newPwd").toString());%>
                            <div class="attention">Пароль изменен</div>
                        <%}else{%>
                            <div class="error_mess">Ошибка изменения данных</div>
                        <%}
                    }
                %>
                <form action="editing.jsp" method="POST">
                    <fieldset title="Смена пароля">
                        <legend>Смена пароля</legend>
                            <label>Новый пароль:</label>
                            <input type="password" name="newPwd"><br>
                            <label>Подтверждение пароля:</label>
                            <input type="password" name="confPwd"><br>
                            <!--label>Введите старый пароль:</label>
                            <input type="password" name="oldPwd"><br-->
                            <label></label><input type="submit" value="Смена пароля">
                    </fieldset>
                </form>
                
            </div>
        </div>
<%@include file="footer.jsp" %>