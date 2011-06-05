<%@page import="java.util.HashMap"%>
<%@page import="com.sun.xml.internal.bind.v2.model.impl.ModelBuilder"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 29 трав 2011, 19:35:15
    Author     : andrey
--%>
<% String title = "Вход"; %>
<%@include file="head.jsp" %>
<%@page import="logic.API.AccountAPI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String login    = request.getParameter("login");
    String password = request.getParameter("password");
    if ((login!=null)&&(password!=null)){
        if (new AccountAPI().autorize(login, password)!=null){
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            HashMap inf = new AccountAPI().getInfo(login);
            session.setAttribute("id", inf.get("ID").toString());
            if (inf.get("ID").toString().equals("1")){
                session.setAttribute("isAdmin", "1");
            }
            response.sendRedirect("/NetCracker/home/main.jsp");
        }
    }
%>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="/NetCracker/">Вход</a><br>
                <a href="/NetCracker/?resurection=1">Восстановить пароль</a>
            </div>
            <div class="block" id="content">
                <c:choose>
                    <c:when test="${(empty param.resurection)}">
                        <h1>Вход</h1>
                        <c:if test="${!(empty param.login)}">
                            <div class="error_mess">
                                *Ошибка авторизации
                            </div>
                        </c:if>
                        <form  method="POST">
                                <label>Логин:</label><input type="text" name="login"><br>
                                <label>Пароль:</label><input type="password" name="password"><br>
                                <input type="submit" id="smb" value=" Вход ">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <h1>Восстановление пароля</h1>
                        <c:choose>
                            <c:when test="${empty param.login}">
                                <p>Для восстановления пароля введите логин в 
                                    форме приведенной ниже. Пароль будет выслан вам на e-mail.</p>
                                <form method="POST">
                                    <label>Логин:</label><input name="login"><br>
                                    <label></label><input type="submit" value="Восстановить" />
                                </form>                               
                            </c:when>
                            <c:otherwise>
                                <% if(new AccountAPI().resurection(login)){ %>
                                    <div class="attention">Пароль выслан вам на почьту.</div><br>
                                    <a href="/NetCracker/">Вернутса на страницу логина</a>
                                <% }else{ %>
                                    <div class="error_mess">Неверный логин</div>
                                    <a href="/NetCracker/?resurection=1">Пробывать снова</a>
                                <% } %>
                            </c:otherwise>
                        </c:choose>

                    </c:otherwise>
                </c:choose>

            </div>
        </div>
<%@include file="footer.jsp" %>