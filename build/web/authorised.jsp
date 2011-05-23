<%@page import="java.util.HashMap"%>
<%@page import="logic.RMCore"%>
<%@page import="logic.APILayer;"%>
<%@page pageEncoding="UTF-8" %>
<%
            String uName = request.getParameter("username");
            String uPwd = request.getParameter("pwd");

            String uFIO = APILayer.getInstance().checkLogin(uName, uPwd);

            if(uFIO==null)
                {
                session.setAttribute("errReturn", 1);
                response.sendRedirect("index.jsp");
                }
            else
                {
                session.setAttribute("userName", request.getParameter("username"));
                session.setAttribute("userPwd", request.getParameter("pwd"));
                session.setAttribute("userFIO", uFIO);
                session.setAttribute("errReturn", 0);

                }
%>

<%
    session.setAttribute("userName", request.getParameter("username"));
    session.setAttribute("userPwd", request.getParameter("pwd"));
    session.setAttribute("errReturn", 0);
%>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <LINK href="style.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <div id="header">
            <font size="15"> Здравствуйте, <%=session.getAttribute("userFIO")%></font>
        </div>

        <div id="mainMenu">
            <ul valign="center">
                <li>First</li>
                <li>Second</li>
                <li>Third</li>
                <li>Forth</li>
            </ul>
        </div>
        <div id="content">
            <table border="0" width="100%" height="100%">
                <tr valign="top">
                    <td width="20%">
                        <ul>
                            <li>SubItem1<li>
                            <li>SubItem2<li>
                            <li>SubItem3<li>
                            <li>SubItem4<li>
                            <li>SubItem5<li>
                        </ul>
                        <%=session.getAttribute("userName")%>
                        <%=session.getAttribute("userPwd")%>
                    </td>

                    <td>
                            <%
                            for (HashMap t:RMCore.getInstance().getResources())
                                {
                                out.println(t.get("TITLE"));
                                out.println("<br>");
                                }
                            %>
                    </td>
                    
                </tr>
            </table>
        </div>

        <div id="footer">
            Footer
        </div>

    </body>
</html>
