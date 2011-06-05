
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
                <c:if test="${(param.res eq null)||(param.confirm ne null)}">
                    <b>Список ресурсов</b><hr>
                    <%
                        if (request.getParameter("confirm")!=null){
                            if (request.getParameter("confirm").toString().equals("yes")){
                                if (rAPI.removeById(request.getParameter("id").toString())){%>
                                    <div class="attention">Запись успешно удалена</div>
                                 <%} else {%>
                                    <div class="error_mess">Неудалось удалить запись.<br>Обратитесь к вашему администратору за помощью.</div>
                                 <%}
                            } else {%>
                                <div class="error_mess">Неверно введено подтверждение удаления</div>
                            <%}
                        }
                    %>
                    <% for(HashMap a: rAPI.getAll()){ %>
                        <a href="/NetCracker/admin/resourses.jsp?res=<%=a.get("ID").toString()%>">
                            <%=a.get("TITLE").toString()%>
                        </a><br>
                    <%}%>
                </c:if>
                <c:if test="${(param.res ne null)&&(param.confirm eq null)}">
                    <!---Функция сохранения при не нуловых значениях названия.--->
                    <c:if test="${(param.chRName ne null)}">
                        <%
                        if (rAPI.setById(
                            request.getParameter("id").toString(),
                            request.getParameter("chRName").toString())){%>
                            <div class="attention">Ресурс успешно сохранен</div>
                        <%} else {%>
                            <div class="error_mess">Произошла ошибка сохранения данных<br>
                            обратитесь к вашему системному администратору за помощью</div>
                        <%}
                        %>
                    </c:if>
                    <!--Если выбрана роль.-->
                    <% String p = rAPI.getById(request.getParameter("res").toString()); %>
                    <form method="post">
                        <label>ID:</label><input type="text" readonly name="id" value="<%=request.getParameter("res").toString()%>"><br>
                        <label>Название:</label><input type="text" name="chRName" value="<%=p%>"><br>
                        <label></label><input type="submit" value="Сохранить">
                    </form>

                    <form method="post">
                        <p>Наберите "yes" в поле ввода для подтверждения удаления ресурса.</p>
                        <input name="confirm"><br>
                        <input type="hidden" name="id" value="<%=request.getParameter("res").toString()%>">
                        <input type="submit" value=" Удалить " />
                    </form>
                        
                    <b>Список ролей</b><hr>
                    <!--Удаление.-->
                    <c:if test="${(param.delRole ne null)}">
                        <%
                            if (roleAPI.rmvResRole(request.getParameter("res"), request.getParameter("delRole"))){%>
                                <div class="attention">Роль успешно удалена</div>
                            <%} else {%>
                                <div class="error_mess">Произошла ошибка удаления роли<br>
                                обратитесь к вашему системному администратору за помощью</div>
                            <%}
                        %>
                    </c:if>
                    <!--Удалили ресурс из роли--->
                    <!--Добавление.-->
                    <c:if test="${(param.add_role ne null)}">
                        <%
                            if (roleAPI.setResRole(request.getParameter("res"), request.getParameter("add_role"))){%>
                                <div class="attention">Роль успешно добавлена</div>
                            <%} else {%>
                                <div class="error_mess">Произошла ошибка добавления роли<br>
                                обратитесь к вашему системному администратору за помощью</div>
                            <%}
                        %>
                    </c:if>
                    <!--Добавление ресурса из роли--->
                    <% Boolean flag=true; %>
                    <% for(HashMap a: rAPI.getRoleOfRes(request.getParameter("res").toString())){ %>
                        <%=a.get("TITLE").toString()%>
                        <% if(flag){flag=false;}else{%>, <%}%>
                        (<a href="/NetCracker/admin/resourses.jsp?res=<%=request.getParameter("res").toString()%>&delRole=<%=a.get("ID").toString()%>">Удалить</a>)
                    <%}%>

                    <form method="post"><input type="hidden" readonly name="id" value="<%=request.getParameter("res").toString()%>"><br>
                        <select name="add_role" size="1">
                            <% for(HashMap a: roleAPI.getRoleList()){ %>
                                <option value=<%=a.get("ID").toString()%>><%=a.get("TITLE").toString()%></option>
                            <%}%>
                        </select> <input type="submit" value=" + ">
                    </form>
                </c:if>


            </div>
        </div>
<%@include file="footer.jsp" %>