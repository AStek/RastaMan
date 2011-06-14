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
        <div class="block" id="main">
        <style type="text/css" media="screen">
		#jMonthCalendar .Meeting { background-color: #DDFFFF;}
		#jMonthCalendar .Birthday { background-color: #DD00FF;}
		#jMonthCalendar #Event_3 { background-color:#0000FF; }
                #content{border-style: hidden}
	</style>
            <div class="block" id="content">
                <h1>Календарь</h1>
                <p>Выберите интересующую вас дату</p>



    <script type="text/javascript">
        $().ready(function() {
			var options = {
				onMonthChanging: function(dateIn) {
					//this could be an Ajax call to the backend to get this months events
					//var events = [ 	{ "EventID": 7, "StartDate": new Date(2009, 1, 1), "Title": "10:00 pm - EventTitle1", "URL": "#", "Description": "This is a sample event description", "CssClass": "Birthday" },
					//				{ "EventID": 8, "StartDate": new Date(2009, 1, 2), "Title": "9:30 pm - this is a much longer title", "URL": "#", "Description": "This is a sample event description", "CssClass": "Meeting" }
					//];
					//$.jMonthCalendar.ReplaceEventCollection(events);
					//return true;
                                        return false;
				},
				onEventLinkClick: function(event) {
					//alert("event link click");
					return true;
				},
				onEventBlockClick: function(event) {
					//alert("block clicked");
					return true;
				},
				onEventBlockOver: function(event) {
					//alert(event.Title + " - " + event.Description);
					return true;
				},
				onEventBlockOut: function(event) {
					return true;
				},
				onDayLinkClick: function(date) {
                                    
                                        //alert(date.data.Date.toString("MM/dd/yyyy"));
                                        window.location = "/NetCracker/home/journal.jsp?this_date=" + date.data.Date.toString("MM/dd/yyyy");
					return true;
				},
				onDayCellClick: function(date) {
                                        //alert(date.data.Date);
                                        window.location = "/NetCracker/home/journal.jsp?this_date=" + date.data.Date.toString("MM/dd/yyyy");
					return true;
				}
			};


			var events = [];


			$.jMonthCalendar.Initialize(options, events);




			var extraEvents = [	{ "EventID": 8, "StartDateTime": new Date(2009, 3, 11), "Title": "10:00 pm - EventTitle1", "URL": "#", "Description": "This is a sample event description", "CssClass": "Birthday" },
								{ "EventID": 9, "StartDateTime": new Date(2009, 3, 20), "Title": "9:30 pm - this is a much longer title", "URL": "#", "Description": "This is a sample event description", "CssClass": "Meeting" }
			];

			//$("#Button").click(function() {
			//	$.jMonthCalendar.AddEvents(extraEvents);
			//});

			//$("#ChangeMonth").click(function() {
			//	$.jMonthCalendar.ChangeMonth(new Date(2009, 7, 7));
			//});
        });
    </script>
                <div id="jMonthCalendar"></div>

            </div>
        </div>
<%@include file="footer.jsp" %>