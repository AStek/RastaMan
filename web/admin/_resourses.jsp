
<%@page import="logic.API.RoleAPI"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="head.jsp" %>
<%@page import="logic.API.AccountAPI, logic.API.ResourceAPI, java.util.HashMap, java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResourceAPI rAPI = new ResourceAPI();
    RoleAPI roleAPI = new RoleAPI();
%>

        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="/NetCracker/admin/resourses.jsp">Ресурсы.</a><br>
                <a href="/NetCracker/admin/resourse_add.jsp">Добавить ресурс</a><br>
            </div>
            <div class="block" id="content">
                <h1>Ресурсы</h1>
                <!--style>
                    label{width:250px;}
                </style-->
                <c:if test="${(param.res eq null)}">
                    <b>Список ресурсов</b><hr>
                    <% for(HashMap a: rAPI.getAll()){ %>
                        <a href="/NetCracker/admin/resourses.jsp?res=<%=a.get("ID").toString()%>">
                            <%=a.get("TITLE").toString()%>
                        </a><br>
                    <%}%>
                </c:if>
                <c:if test="${(param.res ne null)}">
                    <% String p = rAPI.getById(request.getParameter("res").toString()); %>
                    <form method="post">
                        <label></label><input type="text" readonly name="id" value="<%=request.getParameter("res").toString()%>"><br>
                        <label></label><input type="text" name="chRName" value="<%=p%>"><br>
                        <input type="submit" value="Сохранить">
                    </form>

                    <b>Список пользователей</b><hr>
                    <% for(HashMap a: rAPI.getRoleOfRes(request.getParameter("res").toString())){ %>
                    <%=a.get("TITLE").toString()%>
                    (<a href="/NetCracker/admin/resourses.jsp?login=<%=a.get("ID").toString()%>">Удалить</a>),
                    <%}%>

                    <form method="post"><input type="hidden" readonly name="id" value="<%=request.getParameter("res").toString()%>"><br>
                        <select name="chRName" size="1">
                            <% for(HashMap a: roleAPI.getRoleList()){ %>
                        <option value=<%=a.get("ID").toString()%>><%=a.get("TITLE").toString()%></option>
                        <%}%>
                        </select>
                        <input type="text" name="chRName" value="<%=p%>"> <input type="submit" value="+">
                    </form>
                </c:if>


            </div>
        </div>
<%@include file="footer.jsp" %>