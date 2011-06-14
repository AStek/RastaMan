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

    String startTime = " 00:00:00";
    String endTime = " 23:59:59";
    String thisDate = "";
    if (request.getParameter("this_date")!=null){
        String rdate = request.getParameter("this_date").toString();
        startTime = utilits.convertDataToDBFormat(rdate+" 00:00");
        endTime   = utilits.convertDataToDBFormat(rdate+" 23:59");
        thisDate  = rdate;
        //out.print(rdate);
    } else {
        Date nd = new Date();
        Date nd1 = new Date();
        nd1.setTime(nd.getTime());

        SimpleDateFormat Format = new SimpleDateFormat("yy/MM/dd");
        String ds = Format.format(nd);
        startTime = ds+startTime;
        nd1.setTime(nd1.getTime()+86400000);
        ds = Format.format(nd1);
        endTime   = ds+endTime;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        thisDate  = sdf.format(nd);
    }

    ArrayList<HashMap> resList = (ArrayList<HashMap>) new ResourceAPI().getAll().clone();
%>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="/NetCracker/admin/journal.jsp">Расписание событий</a><br>
                <a href="/NetCracker/admin/ciclic.jsp">Создание цыклов</a><br>
            </div>
            <div class="block" id="content">
                <h1>Журнал</h1>
                <script type="text/javascript">
                    $(function(){
                        $('#datepicker1').datepicker({
                                inline: true
                        });
                        $('#datepicker2').datepicker({
                                inline: true
                        });
                        $('#datepicker3').datepicker({
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
                        <legend>Забронировать ресурс</legend>
                        имя ресурса: <select name="res">
                            <% for(HashMap res:resList){ %>
                                <option value="<%=res.get("ID")%>"><%=res.get("TITLE")%></option>
                            <% } %>
                        </select><br>
                        дата: <input name="date" id="datepicker1">
                        c: <input name="time_from" id="timepicker.1">
                        по: <input name="time_to" id="timepicker.2"><br>
                        <input type="submit" value="Внести бронь">
                    </fieldset>
                </form><br>

                <form method="get"><fieldset title="Навигация"><legend>Навигация</legend>
                    дата:<input name="this_date" id="datepicker3" value="<%=thisDate%>"> <input id="sent_to_date" type="submit" value="Перейти к дате" />
                </fieldset></form>

                <%
                    if (request.getParameter("date")!=null){
                        HashMap info = new AccountAPI().getInfo(session.getAttribute("login").toString());
                        String uId   = info.get("ID").toString();
                        String resId = request.getParameter("res").toString();
                        String sTime = request.getParameter("date").toString() + " " + request.getParameter("time_from").toString();
                        String eTime = request.getParameter("date").toString() + " " + request.getParameter("time_to").toString();
                        int r = -1;
                        r = j.reserveRes(
                                uId,
                                resId,
                                utilits.convertDataToDBFormat(sTime),
                                utilits.convertDataToDBFormat(eTime)
                        );
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
                    ArrayList<HashMap> list = null;
                    try{
                        list = (ArrayList<HashMap>) j.getInfoOfTime(startTime, endTime).clone();
                    } catch(NullPointerException ex) {
                        list = new ArrayList<HashMap>();
                    }
                %>

                <%
                    utilits.getTimelineMenu(out, list, "admin/journal_item.jsp?id=");
                %>


            </div>
        </div>
<%@include file="footer.jsp" %>