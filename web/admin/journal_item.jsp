<%@page import="logic.utilits"%>
<%@page import="logic.API.ResourceAPI"%>
<%@page import="javax.annotation.Resource"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logic.API.JournalAPI"%>
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
    JournalAPI j = new JournalAPI();
%>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
            </div>
            <div class="block" id="content">
                <h1>Бронирование</h1>
                <c:if test="${(param.id ne null)}">
                    <% HashMap info = j.getInfo(request.getParameter("id").toString()); %>
                    <b>Название ресурса:</b> <%=info.get("TITLE")%><br>
                    <b>ФИО:</b> <%=info.get("NAME")%><br>
                    <b>Время резервирования:</b> с <%=info.get("START_TIME")%> по <%=info.get("END_TIME")%><br>
                    <br>
                    <a href="/NetCracker/admin/journal.jsp">Вернутса к списку</a> ::
                    <a href="http://localhost:8084/NetCracker/admin/journal_item.jsp?del=<%=request.getParameter("id").toString()%>">Снять бронь</a>
                </c:if>

                <c:if test="${(param.del ne null)}">
                    <% if (j.remove(request.getParameter("del").toString())){ %>
                        <div class="attention">Бронирование снято</div>
                    <% } else { %>
                        <div class="error_mess">Ошибка снятия брони</div>
                    <% } %>
                    <br>
                    <br>
                    <a href="/NetCracker/admin/journal.jsp">Вернутса к списку</a>
                </c:if>
            </div>
        </div>
<%@include file="footer.jsp" %>