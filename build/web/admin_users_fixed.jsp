<%@page pageEncoding="UTF-8"%>
<%@page import="layers.APILayer, java.util.HashMap, java.util.ArrayList"%>

<%
//Выбираем пользователя
String chFIO = request.getParameter("chFIO");
String chPosition = request.getParameter("chPosition");
//Параметры. Изменение
String login = request.getParameter("login");
String pwd= request.getParameter("pwd");
String pwdRepeat= request.getParameter("pwdRepeat");
String email= request.getParameter("email");
String FIO= request.getParameter("FIO");

if(chFIO!=null)
    {
    ArrayList<HashMap> parameters = APILayer.getInstance().getAccountByNameAndRole(chFIO, chPosition);
    parameters.get(0).get("TITLE");
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <LINK href="style.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <div id="header">
            <H1>Header</H1>
        </div>
        <div id="mainMenu">
            <ul valign="center">
                <li>Главная</li>
                <li>Уголок пользователя</li>
                <li>Ресурсы</li>
                <li>История бронирования</li>
                <li>Админ-панель</li>
            </ul>
        </div>
        <div id="content">
            <table border="0" width="100%" height="100%">
                <tr valign="top">
                    <td bgcolor="#808080" width="20%">
                        <ul style="list-style: none">
                            <li>Операции с пользователями :
                            <ol id="myLists"><br>
                                <li><a href="admin_users_fixed.jsp">Определенный пользователь</a></li><br>
                                <li><a href="admin_users_search.jsp">Поиск пользователя</a></li><br>
                            </ol>
                            </li>

                            <li>Операции с ресурсами :
                            <ol id="myLists"><br>
                                <li>Определенный ресурс</li><br>
                                <li>Поиск ресурса</li><br>
                            </ol>
                            </li>

                            <li>Операции с журналом<li>
                        </ul>
                    </td>
                    <td>
                        <!-- MAIN PART!-->
                        <% if(chFIO == null){%>
                        <form  action ="admin_users_fixed.jsp" method="GET">
                            Введите ФИО пользователя<br>
                            <input type="text" name="chFIO"><br>
                            Введите должность пользователя<br>
                                <%for (HashMap t:APILayer.getInstance().getRoleList())
                                {
                                %>
                                <input type="radio" name="role" value=<%=t.get("TITLE")%>>
                                <%=out.println(t.get("TITLE"))%>
                                <% } %>
                            <input type="submit">
                        </form>
                        <%} else{ %>
                        <form action="admin_users_fixed.jsp" method ="GET">
                            Логин пользователя:<br>
                            <textarea rows="1" cols="25" name="login"></textarea><br>
                            Пароль:<br>
                            <textarea rows="1" cols="25" name="pwd"></textarea><br>
                            Повторите пароль:<br>
                            <textarea rows="1" cols="25" name="pwdRepeat"></textarea><br>
                            Электронная почта<br>
                            <textarea rows="1" cols="25"  name="email"></textarea><br>
                            ФИО<br>
                            <textarea rows="1" cols="25" name="FIO"><%= chFIO %></textarea><br>
                            <input type="submit">
                        </form>
                            <% } %>
                    </td>

                </tr>
            </table>
        </div>

        <div id="footer">
            Footer
        </div>

    </body>
</html>