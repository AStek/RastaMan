<%-- 
    Document   : index
    Created on : 16.05.2011, 22:45:11
    Author     : Elynor
--%>

<%

/*String returned="";
if(session.getAttribute("errReturn").equals(1))
    {
    returned="Some of the information you entered is wrong";
    }*/
%>

<html>


    <head>
        <link rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>


    <body bgcolor="#c9c9c9">

        <table border="1" bgcolor="#e6e6e6" align="center" width="40%" height="80%">
            <!---Beginning of the main table--->
            <tr><td>

                    <font align="center" color="#dd0000">
                    </font>
                    
                    <form align="center" action="authorised.jsp" method="get">
                        Username:<br>
                        <input type="text" name="username"><br>
                        Password:<br>
                        <input type="password" name="pwd"><br>
                        <input type="submit" value="Send">
                    </form>

                </td></tr>

        </table>

    </body>
</html>
