
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="head.jsp" %>
<%@page import="logic.API.AccountAPI, logic.API.RoleAPI, java.util.HashMap, java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="/NetCracker/admin/accounts.jsp">Список акков.</a><br>
                <a href="/NetCracker/admin/account_add.jsp">Добавить аккаунт</a><br>
            </div>
            <div class="block" id="content">
                <h1>Аккаунты</h1>
                <%
                    RoleAPI rAPI = new RoleAPI();
                    AccountAPI aAPI = new AccountAPI();
                    if(request.getParameter("del")!=null){
                        if(aAPI.deleteById(request.getParameter("del").toString())){%>
                            <div class="attention">Аккаунт был удален</div>
                        <%} else {%>
                            <div class="error_mess">Ошибка удаления аккаунта</div>
                        <%}
                    }
                %>

                <c:if test="${(param.chFIO ne null)}">
                    <%
                        String chID = request.getParameter("chID");
                        String chLogin = request.getParameter("chLogin");
                        String chPwd = request.getParameter("chPwd");
                        String chFIO = request.getParameter("chFIO");
                        String chRole = request.getParameter("chRole");
                        String chEmail = request.getParameter("chEmail");
                        if(aAPI.setById(chID, chLogin, chPwd, chFIO, chRole, chEmail)){%>
                            <div class="attention">Аккаунт был изменен</div>
                        <%} else {%>
                            <div class="error_mess">Ошибка изменения аккаунта</div>
                        <%}

                    %>
                </c:if>
                <style>
                    label{width:250px;}
                </style>
                <c:if test="${(param['choosenFIO'] eq null)&&(param['login'] eq null)}">
                    <b>Список пользователей</b><hr>
                    <% for(HashMap a: aAPI.getAll()){ %>
                        <a href="/NetCracker/admin/accounts.jsp?login=<%=a.get("LOGIN").toString()%>">
                            <%=a.get("NAME").toString()%>
                        </a><br>
                    <%}%>
                </c:if>
                <c:if test="${(param.choosenFIO ne null)||(param.login ne null)}">
                    <%
                        ArrayList<HashMap> user = new ArrayList();
                        if (request.getParameter("choosenFIO")!=null){
                            user=aAPI.getByNameAndRole(request.getParameter("choosenFIO"), request.getParameter("choosenRole"));
                        } else {
                            String tmpLogin = null;
                            if (request.getParameter("chLogin")==null){
                                tmpLogin = new String(request.getParameter("login").toString());
                            } else {
                                tmpLogin = new String(request.getParameter("chLogin").toString());
                            }
                            user.add(aAPI.getInfo(tmpLogin));
                        }
                    %>
                    <form method="post">

                        <style>
                            label{
                                width: 150px;
                            }
                        </style>

                        <label>ID:</label>
                        <input type="text" readonly name="chID" value="<%=user.get(0).get("ID")%>"><br>
                        <label>ФИО:</label>
                        <input type="text" name="chFIO" value="<%=user.get(0).get("NAME")%>"><br>
                        <label>Электронная почта:</label>
                        <input type="text" name="chEmail" value="<%=user.get(0).get("EMAIL")%>"><br>
                        <label>Должность:</label>
                        <select name="chRole" size="1">
                            <% for(HashMap h: rAPI.getRoleList()){ %>
                                <option
                                    <%if(h.get("ID").toString().equals(user.get(0).get("ROLE_ID").toString())){%>
                                    selected
                                    <%}%>
                                value="<%=h.get("ID")%>"><%=h.get("TITLE")%></option>
                            <%}%>
                        </select><br>
                        <label>Логин:</label>
                        <input type="text" name="chLogin" value="<%=user.get(0).get("LOGIN")%>"><br>
                        <label>Пароль:</label>
                        <input type="text" name="chPwd" value="<%=user.get(0).get("PASSWORD")%>"><br>
                        <input type="submit" value="Сохранить"> <a href="/NetCracker/admin/accounts.jsp?del=<%=user.get(0).get("ID")%>">Удалить</a>
                    </form>
                </c:if>


            </div>
        </div>
<%@include file="footer.jsp" %>