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
    if (request.getParameter("rdate")!=null){
        String rdate = request.getParameter("rdate").toString();
        out.print(rdate);
    } else {
        Date nd = new Date();
        Date nd1 = new Date();
        SimpleDateFormat Format = new SimpleDateFormat("yy/MM/dd");
        String ds = Format.format(nd);
        startTime = ds+startTime;
        nd1.setDate(nd1.getDate()+1);
        ds = Format.format(nd1);
        endTime   = ds+endTime;
    }
    ArrayList<HashMap> list = (ArrayList<HashMap>)j.getInfoOfTime(startTime, endTime).clone();
    ArrayList<HashMap> resList = (ArrayList<HashMap>)new ResourceAPI().getAll().clone();
%>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
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
                        имя ресурса: <select name="">
                            <% for(HashMap res:resList){ %>
                                <option value="<%=res.get("ID")%>"><%=res.get("TITLE")%></option>
                            <% } %>
                        </select><br>
                        c: <input name="date_from" id="datepicker1">
                        <input name="time_from" id="timepicker.1">
                        по: <input name="date_to" id="datepicker2">
                        <input name="time_to" id="timepicker.2"><br>
                        <input type="submit" value="Внести бронь">
                    </fieldset>
                </form><br>

                <form>
                    дата:<input name="this_date" id="datepicker3"> <input id="sent_to_date" type="button" value="Перейти к дате" />
                </form>
                <%for(HashMap jit:list){ %>
                <p><b><%=jit.get("RES").toString()%></b>,
                    забронировал <%=jit.get("NAME").toString()%>
                    c <%=jit.get("START_TIME").toString()%>
                    по <%=jit.get("END_TIME").toString()%>
                    <!--a href="/NetCracker/admin/journal.jsp?del=<%=jit.get("ID").toString()%>">удалить</a></p-->
                <% } %>

            </div>
        </div>
<%@include file="footer.jsp" %>