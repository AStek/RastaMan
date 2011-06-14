<%--
    Document   : footer
    Created on : 29 трав 2011, 19:24:37
    Author     : andrey
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    if ((session.getAttribute("login")==null)||
            (session.getAttribute("isAdmin")==null)){
                response.sendRedirect("/NetCracker/");
    }
    
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/style.css" />
        <title>Young team\Админка</title>
        <link type="text/css" href="../css/south-street/jquery-ui-1.8.13.custom.css" rel="stylesheet" />
        <link rel="stylesheet" href="../css/core.css" type="text/css" />
	<!--link rel="stylesheet" href="../css/cupertino/jquery-ui.cupertino.css" type="text/css" /-->

        <script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="../js/jquery-ui-1.8.13.custom.min.js"></script>
        <script type="text/javascript" src="../js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="../js/jquery.ui.timepicker.js?v=0.2"></script>
        <script type="text/javascript" src="../js/jMonthCalendar.js"></script>
        <script type="text/javascript" src="../js/jquery.quicksearch.js"></script>
    </head>
    <body>
        <div class="block" id="header">
            <img src="../img/logo.png"><br>
            <b>Вы вошли как: </b>Админ
        </div>
        <div class="block" id="menu">
                <a href="/NetCracker/admin/main.jsp">Главная</a> ::
                <a href="/NetCracker/admin/accounts.jsp">Аккаунты</a> ::
                <a href="/NetCracker/admin/roles.jsp">Роли</a> ::
                <a href="/NetCracker/admin/resourses.jsp">Ресурсы</a> ::
                <a href="/NetCracker/admin/calendar.jsp">Бронирование</a> ::
                <a href="/NetCracker/out.jsp">Выход</a>
        </div>
