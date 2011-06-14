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
    ArrayList<HashMap> resList = (ArrayList<HashMap>) new ResourceAPI().getAll().clone();
%>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="/NetCracker/admin/journal.jsp">Расписание событий</a><br>
                <a href="/NetCracker/admin/ciclic.jsp">Создание цыклов</a><br>
            </div>
            <div class="block" id="content">
                <h1>Создание цыклов</h1>
                <script type="text/javascript">
                    $(function(){
                        $('#datepicker1').datepicker({
                                inline: true
                        });
                        $('#datepicker2').datepicker({
                                inline: true
                        });
                    });
                    $(document).ready(function() {
                        $('#timepicker\\.1').timepicker();
                        $('#timepicker\\.2').timepicker();
                    });
                </script>

                <form method="POST">
                    <fieldset title="Забронировать ресурс">
                        имя ресурса: <select name="res">
                            <% for(HashMap res:resList){ %>
                                <option value="<%=res.get("ID")%>"><%=res.get("TITLE")%></option>
                            <% } %>
                        </select><br>
                        дата начала цыкла: <input name="from" id="datepicker1">
                        дата окончания цыкла: <input name="to" id="datepicker2"><br>
                        время события c: <input name="time_from" id="timepicker.1">
                        по: <input name="time_to" id="timepicker.2"><br>
                    </fieldset>
                        
                    <fieldset title="Дни недели">
                        <!--
                            Sun Mon Tue Wed Thu Fri Sat
                        -->
                        Выберите дни недели для события:<br>
                        <input type="checkbox" name="days" value="Mon" /> Понедельник<br>
                        <input type="checkbox" name="days" value="Tue" /> Вторник<br>
                        <input type="checkbox" name="days" value="Wed" /> Среда<br>
                        <input type="checkbox" name="days" value="Thu" /> Четверг<br>
                        <input type="checkbox" name="days" value="Fri" /> Пятница<br>
                        <input type="checkbox" name="days" value="Sat" /> Субота<br>
                        <input type="checkbox" name="days" value="Sun" /> Воскресенье<br>
                    </fieldset>
                        <input type="submit" value="Внести бронь">
                </form><br>


                <%
                    if (request.getParameter("from")!=null){
                        HashMap info = new AccountAPI().getInfo(session.getAttribute("login").toString());
                        String uId   = info.get("ID").toString();
                        String resId = request.getParameter("res").toString();
                        String sTime = request.getParameter("from").toString() + " " + request.getParameter("time_from").toString();
                        String eTime = request.getParameter("to").toString() + " " + request.getParameter("time_to").toString();

                        String[] days = request.getParameterValues("days");
                        String   loop = "";
                        if (days != null) {
                            for (int i = 0; i < days.length; i++) {
                                loop = loop+" "+days[i];
                            }
                        }
                        int r = -1;
                        r = new JournalAPI().reserveRes(uId, resId, utilits.convertDataToDBFormat(sTime), utilits.convertDataToDBFormat(eTime), loop);
                        //new JournalAPI().

                        if (r>0){%>
                            <div class="attention">Запись успешно добавленна</div>
                        <%} else {
                            if (r==0){%>
                                <div class="error_mess">Не удалось добавить бронь.<br>Время занято пользователем с более высоким приоритетом.</div>
                            <%}else{%>
                                <div class="error_mess">Ошибка. Не удалось добавить бронь</div>
                            <%}
                        }
                    }
                %>



            </div>
        </div>
<%@include file="footer.jsp" %>