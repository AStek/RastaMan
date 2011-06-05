
<%@page import="logic.API.RoleAPI"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="head.jsp" %>
<%@page import="logic.API.AccountAPI, logic.API.ResourceAPI, java.util.HashMap, java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    RoleAPI roleAPI = new RoleAPI();
%>

<c:if test="${(param.newRes ne null)}">
    <%
        roleAPI.add(request.getParameter("newRole"));
    %>
</c:if>

        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="/NetCracker/admin/roles.jsp">Роли.</a><br>
                <a href="/NetCracker/admin/role_add.jsp">Добавить роль</a><br>
            </div>
            <div class="block" id="content">
                <h1>Добавление роли</h1>
                <c:if test="${(param.newRole ne null)}">
                    <% new RoleAPI().add(request.getParameter("newRole").toString(), request.getParameter("prior").toString()); %>
                    <div class="attention">Роль <%=request.getParameter("newRole")%> успешно добавлена</div><br>
                    <a href="/NetCracker/admin/roles.jsp">Вернутса к списку ролей</a><br><br>
                </c:if>
                <style>
                    label{width:150px;}
                </style>
                <form method="post">
                    <label>Название роли:</label>
                    <input type="text" name="newRole"><br>
                    <label>Приоритет:</label>
                    <input type="text" name="prior"><br>
                    <label> </label> <input type="submit" value=" Создать ">
                </form>



            </div>
        </div>
<%@include file="footer.jsp" %>