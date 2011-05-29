<%-- 
    Document   : design
    Created on : 24 трав 2011, 15:56:22
    Author     : andrey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <title>Young team\Вход</title>
    </head>
    <body>
        <div class="block" id="header">
            <img src="../img/logo.png">
            <b>Вы не авторизованы.</b>
        </div>
        <div class="block" id="menu">
        </div>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>Меню</b><br>
                <a href="#">Вход</a><br>
                <a href="#">Восстановить пароль</a>
            </div>
            <div class="block" id="content">
                <h1>Вход</h1>

                <form method="POST">
                        <label>Логин:</label><input type="text" name="login"><br>
                        <label>Пароль:</label><input type="password" name="pass"><br>
                        <input type="submit" id="smb" value=" Вход ">
                </form>

            </div>
        </div>
        <div class="block" id="footer">

        </div>
    </body>
</html>
