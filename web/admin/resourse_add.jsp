
<%@page import="logic.API.RoleAPI"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="head.jsp" %>
<%@page import="logic.API.AccountAPI, logic.API.ResourceAPI, java.util.HashMap, java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResourceAPI rAPI = new ResourceAPI();
%>

<c:if test="${(param.newRes ne null)}">
    <%
        rAPI.add(request.getParameter("newRes"));
    %>
</c:if>

        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="/NetCracker/admin/resourses.jsp">Ресурсы.</a><br>
                <a href="/NetCracker/admin/resourse_add.jsp">Добавить ресурс</a><br>
            </div>
            <div class="block" id="content">
                <h1>Добавление ресурса</h1>
                <c:if test="${(param.newRes ne null)}">
                    <div class="attention">Ресурс <%=request.getParameter("newRes")%> успешно добавлен</div><br>
                    <a href="/NetCracker/admin/resourses.jsp">Вернутса к списку ресурсов</a><br><br>
                </c:if>
                <style>
                    label{width:150px;}
                </style>
                <form method="post">
                    <label>Название ресурса</label>
                    <input type="text" name="newRes"><br>
                    <label></label><input type="submit" value="Создать">
                </form>



            </div>
        </div>
<%@include file="footer.jsp" %>