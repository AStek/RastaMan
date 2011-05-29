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
        <title>Young team\Бронирование ресурсов</title>
        <link type="text/css" href="../css/south-street/jquery-ui-1.8.13.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="../js/jquery-ui-1.8.13.custom.min.js"></script>
        <script type="text/javascript" src="../js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="../js/jquery.ui.timepicker.js?v=0.2"></script>
    </head>
    <body>
        <div class="block" id="header">
            <img src="../img/logo.png">
            <b>Вы не авторизованы.</b>
        </div>
        <div class="block" id="menu">
            <a href="#">link</a> ::
            <a href="#">link</a> ::
            <a href="#">link</a> ::
            <a href="#">link</a> ::
            <a href="#">link</a>
        </div>
        <div class="block" id="main">
            <div class="block" id="sidebar">
                <b>User menu</b><br>
                <a href="#">link</a><br>
                <a href="#">link</a><br>
                <a href="#">link</a><br>
                <a href="#">link</a><br>
                <a href="#">link</a><br>
                <a href="#">link</a><br>
                <a href="#">link</a><br>
            </div>
            <div class="block" id="content">
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
                <h1>Бронирование ресурсов</h1>

                <form method="POST">
                    <fieldset title="Забронировать ресурс">
                        <legend>Забронировать ресурс</legend>
                        имя ресурса: <select name="">
                            <option>Кухня</option>
                            <option>Кабинет</option>
                        </select><br>
                        c: <input name="date_from" id="datepicker1">
                        <input name="time_from" id="timepicker.1">
                        по: <input name="date_to" id="datepicker2">
                        <input name="time_to" id="timepicker.2"><br>
                        <input type="submit" value="Внести бронь">
                    </fieldset>
                </form><br>

                <form>
                    дата:<input name="this_date" id="datepicker3">
                </form>

                Кухня:
                <div class="timeline">
                    <a href="#">
                    <div class="timeline-block" style="width: 20%" title="c 00:00 по 7:30">Алексей</div>
                    </a>
                    <div class="timeline-space" style="width: 12%"></div>
                    <a href="#">
                    <div class="timeline-block" style="width: 14%">Василий</div>
                    </a>
                </div><br>
                <table border="0" cellspacing="0" class="timtline-table" width="100%">
                        <tr>
                            <td>0</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td>
                            <td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td>
                            <td>12</td><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td>
                            <td>18</td><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td>
                        </tr>
                </table><br>

                Кабинет:
                <div class="timeline">
                    <a href="#">
                    <div class="timeline-block" style="width: 25%">Семен</div>
                    </a>
                    <div class="timeline-space" style="width: 12%"></div>
                    <a href="#">
                    <div class="timeline-block" style="width: 17%">Петр</div>
                    </a>
                    <div class="timeline-space" style="width: 15%"></div>
                    <a href="#">
                    <div class="timeline-block" style="width: 12%">Василий</div>
                    </a>
                </div><br>
                <table border="0" cellspacing="0" class="timtline-table" width="100%">
                        <tr>
                            <td>0</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td>
                            <td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td>
                            <td>12</td><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td>
                            <td>18</td><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td>
                        </tr>
                </table><br>

            </div>
        </div>
        <div class="block" id="footer">

        </div>
    </body>
</html>
