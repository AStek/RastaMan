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
                <a href="/NetCracker/home/journal.jsp">Расписание событий</a><br>
                <a href="/NetCracker/home/ciclic.jsp">Создание цыклов</a><br>
            </div>
            <div class="block" id="content">
                <h1>Бронирование</h1>
                <c:if test="${(param.id ne null)}">
                    <% HashMap info = j.getInfo(request.getParameter("id").toString()); %>
                    <% if(info.get("LOOP")==null){ %>
                        <b>Название ресурса:</b> <%=info.get("TITLE")%><br>
                        <b>ФИО:</b> <%=info.get("NAME")%><br>
                        <b>Время резервирования:</b> с <%=info.get("START_TIME")%> по <%=info.get("END_TIME")%><br>
                        <br>
                        <a href="/NetCracker/home/journal.jsp">Вернутса к списку</a>
                        <% if (session.getAttribute("id").toString().equals(info.get("AC_ID"))){ %>
                         ::  <a href="http://localhost:8084/NetCracker/home/journal_item.jsp?del=<%=request.getParameter("id").toString()%>">Снять бронь</a>
                       <%}%>
                    <% } else { 
                        String t1[] = info.get("START_TIME").toString().split(" ");
                        String t2[] = info.get("END_TIME").toString().split(" ");
                        %>
                        <b>Цыклическое событие</b><br>
                        <b>Название ресурса:</b> <%=info.get("TITLE")%><br>
                        <b>ФИО:</b> <%=info.get("NAME")%><br>
                        <b>Период цыкла:</b> с <%=t1[0]%> по <%=t2[0]%><br>
                        <b>Время резервирования:</b> с <%=t1[1]%> по <%=t2[1]%><br>
                        <b>Зарезервированные дни:</b><%=info.get("LOOP")%><br>
                        <br>
                        <a href="/NetCracker/home/journal.jsp">Вернутса к списку</a>
                        <% if (session.getAttribute("id").toString().equals(info.get("AC_ID"))){ %>
                         ::  <a href="http://localhost:8084/NetCracker/home/journal_item.jsp?del=<%=request.getParameter("id").toString()%>">Снять бронь</a>
                       <%}%>

                    <% } %>
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