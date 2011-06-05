<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="head.jsp" %>
<%@page import="logic.API.AccountAPI, logic.API.RoleAPI, java.util.HashMap, java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    RoleAPI rAPI = new RoleAPI();
    AccountAPI aAPI = new AccountAPI();

    if(request.getParameter("del")!=null){
        aAPI.deleteById(request.getParameter("del").toString());
    }
%>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="/NetCracker/admin/accounts.jsp">Список акков.</a><br>
                <a href="/NetCracker/admin/account_add.jsp">Добавить аккаунт</a><br>
            </div>
            <div class="block" id="content">
                <h1>Аккаунты</h1>
                <style>
                    label{width:250px;}
                </style>
                    <form method="post">

                        <style>
                            label{
                                width: 150px;
                            }
                        </style>
                        <c:if test="${(param.nuFIO ne null)}">
                            <%
                                String nuLogin = request.getParameter("nuLogin").toString();
                                String nuPwd = request.getParameter("nuPwd").toString();
                                String nuFIO = request.getParameter("nuFIO").toString();
                                String nuRole = request.getParameter("nuRole").toString();
                                String nuEmail = request.getParameter("nuEmail").toString();
                                
                            %>
                            <% if (aAPI.add(nuLogin, nuPwd, nuFIO, nuRole, nuEmail)) {%>
                                <div class="attention">Аккаунт успешно добавлен</div><br>
                                <a href="/NetCracker/admin/accounts.jsp">Вернутса к списку акаунтов</a>
                            <% } else { %>
                                <div class="error_mess">Ошибка добавления акаунта</div><br>
                                <a href="/NetCracker/admin/accounts.jsp">Вернутса к списку акаунтов</a>
                            <% } %>
                        </c:if><br>

                        <label>ФИО:</label>
                        <input type="text" name="nuFIO"><br>
                        <label>Электронная почта:</label>
                        <input type="text" name="nuEmail"><br>
                        <label>Должность:</label>
                        <select name="nuRole" size="1">
                            <% for(HashMap h: rAPI.getRoleList()){ %>
                                <option value="<%=h.get("ID")%>"><%=h.get("TITLE")%></option>
                            <%}%>
                        </select><br>
                        <label>Логин:</label>
                        <input type="text" name="nuLogin" ><br>
                        <label>Пароль:</label>
                        <input type="text" name="nuPwd" ><br>
                        <input type="submit" value="Создать">
                    </form>

            </div>
        </div>
<%@include file="footer.jsp" %>