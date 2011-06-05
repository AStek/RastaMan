/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author andrey
 */
public class utilits {
    public static Date getDateFromString(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try{
            return sdf.parse(s);
        } catch(ParseException e) {

            return new Date();
        }
    }

    public static String convertDataToDBFormat(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try{
            Date tmp = sdf.parse(s);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yy/MM/dd HH:mm");
            return sdf2.format(tmp)+":00";
        } catch(ParseException e) {
            return null;
        }
    }

    public static int getPercentDateInterval(Date from, Date to){
        long interval = (to.getTime() - from.getTime())*100;
        return Math.round(interval/86400000);
    }

    public static String getTimeFromDate(Date d){
        SimpleDateFormat Format = new SimpleDateFormat("HH:mm");
        return Format.format(d);
    }

    public static String getInfo(String name, Date from, Date to){
        return "Забронировал(а) "+name+", с "+getTimeFromDate(from)+
               " по "+getTimeFromDate(to);
    }

    public static void getSeparator(JspWriter out, int width){
        try {
            out.print("<div class=\"timeline-space\" style=\"width: "+Integer.toString(width)+"%\"></div>");
        } catch (IOException ex) {
            Logger.getLogger(utilits.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void getMenuItem(JspWriter out, String title, int width, String id, String info, String url){
        try {
            String rTitle = null;
            String[] arr = title.split(" +");
            rTitle = arr[0];
            out.print("<a href=\"/NetCracker/"+url+id+"\">"+
                "<div class=\"timeline-block\" style=\"width: "+Integer.toString(width)+
                "%\" title=\""+info+"\">"+rTitle+"</div></a>");
        } catch (IOException ex) {
            Logger.getLogger(utilits.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getTimelineMenu(JspWriter out, ArrayList<HashMap> list, String url){
        try{
            Boolean first = true;
            String title  = null;
            Date   dat    = new Date();

            String name   = null;
            String info   = null;
            int    width  = 0;

            Date   startTime = null;
            Date   endTime   = null;

            if (!list.isEmpty()){
                title = list.get(0).get("RES").toString();
                out.print("<b>"+title+"</b> : <div class=\"timeline\">");
            }

            for(HashMap item:list){
                if (!item.get("RES").toString().equals(title)){
                    out.print("</div><br>"+
                        "<table border=\"0\" cellspacing=\"0\" class=\"timtline-table\" width=\"100%\">"+
                        "<tr><td>0</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td>"+
                        "<td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td>"+
                        "<td>12</td><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td>"+
                        "<td>18</td><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td>"+
                        "</tr></table><br>");
                    title = item.get("RES").toString();
                    out.print("<b>"+title+"</b> : <div class=\"timeline\">");
                    first = true;
                }
                    if (!first){
                       getSeparator(out, getPercentDateInterval(
                                dat, getDateFromString(item.get("START_TIME").toString()))
                       );
                    } else {
                        first = false;
                        Date d1 = getDateFromString(item.get("START_TIME").toString());
                        d1.setHours(0);
                        d1.setMinutes(0);
                        getSeparator(out, getPercentDateInterval(
                                d1, getDateFromString(item.get("START_TIME").toString()))
                        );
                    }
                    name = item.get("NAME").toString();
                    startTime = getDateFromString(item.get("START_TIME").toString());
                    endTime = getDateFromString(item.get("END_TIME").toString());
                    info = getInfo( name, startTime, endTime);
                    width = getPercentDateInterval(startTime, endTime);

                    getMenuItem(out, name, width, item.get("ID").toString(), info, url);
                    dat = getDateFromString(item.get("END_TIME").toString());
            }
            if (!list.isEmpty()){
                out.print("</div><br>"+
                    "<table border=\"0\" cellspacing=\"0\" class=\"timtline-table\" width=\"100%\">"+
                    "<tr><td>0</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td>"+
                    "<td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td>"+
                    "<td>12</td><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td>"+
                    "<td>18</td><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td>"+
                    "</tr></table><br>");
            }
        } catch (IOException ex){

        }
    }
}
