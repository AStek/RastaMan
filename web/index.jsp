<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 25 трав 2011, 21:17:01
    Author     : andrey
--%>
<%
    Boolean flag = (
            (request.getAttribute("t")!=null)
          &&
            (!request.getAttribute("t").toString().equals("null"))
        );
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    <c:choose>
        <c:when test="${flag}">
            true
        </c:when>
        <c:otherwise>
            false
        </c:otherwise>
    </c:choose>

    </body>
</html>
