
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
                <a href="/NetCracker/admin/roles.jsp">Роли.</a><br>
                <a href="/NetCracker/admin/role_add.jsp">Добавить роль</a><br>
            </div>
            <div class="block" id="content">

                <h1>Перечень ролей</h1>
                <!--style>
                    label{width:250px;}
                </style-->
                <c:if test="${(param.role eq null)||(param.confirm ne null)}">
                    <!--Перечень ролей, выводится линками.-->
                    <b>Список ролей</b><hr>
                    <%
                        if (request.getParameter("confirm")!=null){
                            if (request.getParameter("confirm").toString().equals("yes")){
                                if (roleAPI.removeById(request.getParameter("id").toString())){%>
                                    <div class="attention">Запись успешно удалена</div>
                                 <%} else {%>
                                    <div class="error_mess">Не удалось удалить запись.<br>Обратитесь к вашему администратору за помощью.</div>
                                 <%}
                            } else {%>
                                <div class="error_mess">Неверно введено подтверждение удаления</div>
                            <%}
                        }
                    %>
                    <% for(HashMap a: roleAPI.getRoleList()){ %>
                        <a href="/NetCracker/admin/roles.jsp?role=<%=a.get("ID").toString()%>">
                            <%=a.get("TITLE").toString()%>
                        </a><br>
                    <%}%>
                </c:if>

                <c:if test="${(param.role ne null)&&(param.confirm eq null)}">
                   <!---Функция сохранения при не нуловых значениях названия роли.--->
                    <c:if test="${(param.chRName ne null)}">
                        <%
                        if (roleAPI.setById(
                            request.getParameter("role").toString(),
                            request.getParameter("chRName").toString(),
                            request.getParameter("chRWeight").toString())){%>
                            <div class="attention">Информация сохранена</div>
                        <%} else {%>
                            <div class="error_mess">Произошла ошибка сохранения данных<br>
                            обратитесь к вашему системному администратору за помощью</div>
                        <%}
                        %>
                    </c:if>
                    <!--Если выбрана роль.-->
                    <% HashMap<Object, Object> p = roleAPI.getById(request.getParameter("role"));%>
                    <form method="post">
                        <label>ID:</label><input type="text" readonly name="id" value="<%=request.getParameter("role").toString()%>"><br>
                        <label>Название:</label><input type="text" name="chRName" value="<%=p.get("TITLE")%>"><br>
                        <!--Тут с весом. Надеюсь шо правильно, обрати внимание.--->
                        <label>Вес:</label><input type="text" name="chRWeight" value="<%=p.get("PRIORITY") %>"><br>
                        <label></label><input type="submit" value="Сохранить">
                    </form>


                    <form method="post">
                        <p>Наберите "yes" в поле ввода для подтверждения удаления роли.</p>
                        <input name="confirm"><br>
                        <input type="hidden" name="id" value="<%=request.getParameter("role").toString()%>">
                        <input type="submit" value=" Удалить " />
                    </form>

                    <b>Список ресурсов</b><hr>
                    <!--Удаление.-->
                    <c:if test="${(param.resDel ne null)}">
                        <%
                            if (roleAPI.rmvResRole(request.getParameter("resDel"), request.getParameter("role"))){%>
                                <div class="attention">Ресурс успешно удален</div>
                            <%} else {%>
                                <div class="error_mess">Произошла ошибка удаления ресурса<br>
                                обратитесь к вашему системному администратору за помощью</div>
                            <%}
                        %>
                    </c:if>
                    <!--Удалили ресурс из роли--->
                    <!--Добавление.-->
                    <c:if test="${(param.add_res ne null)}">
                        <%
                            if (roleAPI.setResRole(request.getParameter("add_res"), request.getParameter("role"))){%>
                                <div class="attention">Ресурс успешно добавлен</div>
                            <%} else {%>
                                <div class="error_mess">Произошла ошибка добавления ресурса<br>
                                обратитесь к вашему системному администратору за помощью</div>
                            <%}
                        %>
                    </c:if>
                    <!--Добавление ресурса из роли--->
                    <% Boolean flag=true; %>
                    <% for(HashMap a: roleAPI.getResOfRole(request.getParameter("role").toString())){ %>
                        <% if(flag){flag=false;}else{%>, <%}%>
                        <%=a.get("TITLE").toString()%>
                        (<a href="/NetCracker/admin/roles.jsp?role=<%=request.getParameter("role").toString()%>&resDel=<%=a.get("ID").toString()%>">Удалить</a>)
                    <%}%>

                    <form method="post"><input type="hidden" readonly name="id" value="<%=request.getParameter("role").toString()%>"><br>
                        <select name="add_res" size="1">
                            <% for(HashMap a: rAPI.getAll()){ %>
                        <option value=<%=a.get("ID").toString()%>><%=a.get("TITLE").toString()%></option>
                        <%}%>
                        </select> <input type="submit" value=" + ">
                    </form>
                </c:if>


            </div>
        </div>
<%@include file="footer.jsp" %>