
package logic.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import logic.IModel.IJournal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import logic.DB;
import logic.DBcontroll;
import logic.ErrMgr.LogErrorManager;
import logic.SendMail;

/**
 *Класс управления рассписанием
 * 
 * @author Александр
 */
public class Journal implements IJournal {

    private DB db = null;

    public Journal() {
        db = DBcontroll.getInstance();
    }

    /**
     * Возвращает все существующие записи
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> getAll() {
        if (!db.query("select * from journal")) {
            LogErrorManager.getInstance().addError(2, "Journal.getAll", "select all from table journal failed");
            return null;
        }

        return db.getResultList();
    }

    /**
     * Возвращает все строки по указанному диапазону дат
     *
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     * Добавил Александр
     */
    public ArrayList<HashMap> getInfoOfTime(String startTime, String endTime) {
        String out =
                "j.id,to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "account_id,resource_id,a.*,r.*,j.loop, r.title as res, a.id as ac_id ";
        return getInfoE(out, startTime, endTime, "");
    }

    /**
     * Возвращает строки по указанному диапазону дат для пользователя и для указанного ресурса
     * @param accId
     * @param resId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     * Добавил Александр
     */
    public ArrayList<HashMap> getInfoOfTime(int accId, int resId, String startTime, String endTime) {
        // simple insertion
        String out;
        out = "j.id,"
                + "to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "account_id,resource_id,a.*,r.*,j.loop, r.title as res, a.id as ac_id ";
        return getInfoE(out, startTime, endTime, " and (a.id=" + accId + " and r.id=" + resId + " )");
    }

    /**
     * Возвращает информацию по указанному диапазону дат для указанного пользователя
     * @param accId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     * Добавил Александр
     */
    public ArrayList<HashMap> getInfoOfTime(int accId, String startTime, String endTime) {
        // simple insertion
        String out;
        out = "j.id,"
                + "to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "account_id,resource_id,a.*,r.*,j.loop, r.title as res, a.id as ac_id ";
        return getInfoE(out, startTime, endTime, "and a.id=" + accId);
    }

    /**
     * Возвращает информацию по указанному ресурсу и диапазону дат
     * @param resId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     *
     * Добавил Александр
     */
    public ArrayList<HashMap> getInfoOfTimeByRes(int resId, String startTime, String endTime) {
        // simple insertion
        String out;
        out = "j.id,"
                + "to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "account_id,resource_id,a.*,r.*,j.loop, r.title as res, a.id as ac_id ";
        return getInfoE(out, startTime, endTime, "and r.id=" + resId);

    }

    /**
     * По ИД журнала возвращает диапазон дат, название ресурса имя пользователя и его ИД
     * @param id
     * @return HashMap {
     * NAME=Виктор Побережный,
     * AC_ID=6, START_TIME=12/13/2003 17:00,
     * END_TIME=12/13/2003 19:00,
     * TITLE=Аудитория №1}
     */
    public HashMap getInfo(int id) {
        String q = "select to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "title,name,a.id,j.loop,r.*,a.*, r.title as res, a.id as ac_id "
                + "from account a,journal j,resources r "
                + "where (a.id=j.account_id and r.id=j.resource_id) and j.id=" + id;
        db.query(q);
        try {
            return db.getResultList().get(0);
        } catch (NullPointerException ex) {
            LogErrorManager.getInstance().addError(0, "Journal.getInfo(int " + id + ")",
                    "WARNING: no data found");

        } catch (Exception ex) {
            LogErrorManager.getInstance().addError(2, "Journal.getInfo(int " + id + ")",
                    "other error (" + ex.toString() + ")");

        }
        return null;
    }

    /**
     * Удаляет строку с оповещением пользователя об удалении из расписания
     * @param ID - id of record in journal table
     * @param resID - reserved (not used)
     * @param uName
     * @param time
     * @param res
     * @param mail
     * @return boolean
     * Добавил Александр
     */
    public boolean remove(int ID, int resID, String uName, String time, String res, String mail) {

        if (db.query("delete from journal where ID=" + ID)) {
            String text = "<b><font color=red> Вы были удалены с ресурса:"+res+" </b><b>"+time+"</b>";
//            text+="";
            SendMail ml=new  SendMail(mail, "Изменение расписания", text);
            ml.sendSSLEmail();
            System.out.println("user " + uName + "( " + time + " from " + res + " )" + " has notified and removed from journal");
            return true;
        }
        LogErrorManager.getInstance().addError(2, "Journal.remove(int " + ID + ", int resID, String " + uName + ", String " + time + ", String " + res + ", String " + mail + ")",
                "delete record from table journal failed");
        return false;
    }

    /**
     * Удаляет запись из журнала
     * @param accId
     * @param resID
     * @return
     * Добавил Александр
     */
    public boolean remove(int accId, int resID) {
        return db.query("delete from journal where ACCOUNT_ID=" + accId + " and RESOURCE_ID=" + resID);
    }

    /**
     * Удаляет запись из журнала по ИД
     * @param Id
     * @return
     * Добавил Александр
     */
    public boolean remove(int Id) {
        if (db.query("delete from journal where ID=" + Id)) {
            return true;
        }
        LogErrorManager.getInstance().addError(2, "Journal.remove(int " + Id + ")",
                "delete record from table journal failed");
        return false;
    }

    /**
     * не безопасное добавлние, для внутренних нужд.
     *
     * @param accId
     * @param resId
     * @param startTime
     * @param endTime
     * @return boolean
     * Добавил Александр
     */
    private boolean add(int accId, int resId, String startTime, String endTime) {
        String q = "insert into journal values("
                + "null,"
                + "to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'),"
                + "to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS')"
                + "," + accId + "," + resId + ",null)";

        return db.query(q);
    }

    private boolean add(int accId, int resId, String startTime, String endTime, String loop) {
        String q = "insert into journal values("
                + "null,"
                + "to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'),"
                + "to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS')"
                + "," + accId + "," + resId + ",'" + loop + "')";

        return db.query(q);
    }

    /**
     * Перерезервирует занятость для указанного ресурса указанным пользователем на указанный диапазон
     * если ресурс небыл занят добавляет новю запись.
     * 
     * @param accId
     * @param resId
     * @param startTime
     * @param endTime
     * @return int если успешно =1 если не возможно перерегистрировать =0 если ошибка =-1
     * Добавил Александр
     */
    public int reserveRes(int accId, int resId, String startTime, String endTime) {
        try {
            boolean isLowPriority = true;
            ArrayList<HashMap> users = null;
            try {
                users = (ArrayList<HashMap>) getInfoOfTimeByRes(resId, startTime, endTime).clone();
            } catch (NullPointerException ex) {
                add(accId, resId, startTime, endTime);
                System.out.println("record ADDED check function");
                return 1;
            }
            Account acc = new Account();
            for (HashMap u : users) {
                System.out.println(acc.getPriority(accId) + "<=" + acc.getPriority(u.get("LOGIN").toString()));
                if (acc.getPriority(accId) <= acc.getPriority(u.get("LOGIN").toString())
                        || u.get("LOOP") != null) {
                    isLowPriority = false;
                }
            }
            //System.out.println(users);
            if (!isLowPriority) {
                LogErrorManager.getInstance().addError(0, "Journal.reserveRes(int " + accId + ", int " + resId + ", String " + startTime + ", String " + endTime + ")",
                        "WARNING time is busy, record did not add");
                System.out.println("sorry, you haven't enough rights to do this");
                return 0;
            } else {
                for (HashMap u : users) {

                    remove(Integer.parseInt(u.get("ID").toString()),
                            Integer.parseInt(u.get("RESOURCE_ID").toString()), u.get("NAME").toString(),
                            "from " + u.get("START_TIME") + " to " + u.get("END_TIME"),
                            u.get("TITLE").toString(), u.get("MAIL").toString());
                }
                add(accId, resId, startTime, endTime);
                System.out.println("record ADDED check function");
                return 1;
            }
        } catch (NullPointerException ex) {
            LogErrorManager.getInstance().addError(2, "Journal.reserveRes(int " + accId + ", int " + resId + ", String " + startTime + ", String " + endTime + ")",
                    "Null Pointer Exception (" + ex + ")");
            return -1;
        } catch (Exception e) {
            LogErrorManager.getInstance().addError(3, "Journal.reserveRes(int " + accId + ", int " + resId + ", String " + startTime + ", String " + endTime + ")",
                    "FATAL other error (" + e + ")");
            System.out.println("AAaa [" + e.toString());
            return -1;
        }
    }

    public int reserveRes(int accId, int resId, String startTime, String endTime, String loop) {
        //ArrayList<HashMap> users = (ArrayList<HashMap>) getInfoOfTimeByRes(resId, startTime, endTime).clone();
        ArrayList<HashMap<String, String>> users = (ArrayList<HashMap<String, String>>) getBusyDays(loop, resId).clone();
        String s = "";
        String e = "";
        //System.out.println(users);
        int j = 0;
        while (j < users.size() - 1) {
            if (users.get(j).get("START_TIME").substring(0, 10).compareTo(
                    users.get(j + 1).get("START_TIME").substring(0, 10)) == 0) {
                users.remove(j);
            } else {
//                System.out.println(users.get(j) + "<<<<<");
                j++;
            }
        }
//        System.out.println(users.get(j) + "<<<<<");
        ArrayList<HashMap> tmp = null;
        ArrayList<HashMap> mailList = new ArrayList();
        for (int i = 0; i < users.size(); i++) {
            s = users.get(i).get("START_TIME").substring(0, 10) + startTime.substring(10);
            e = users.get(i).get("START_TIME").substring(0, 10) + endTime.substring(10);
            //System.out.println(s.substring(0, 10));
            if (users.get(i).get("LOOP") != null) {
                if ((compareTime(users.get(i).get("START_TIME"), s) >= 0
                        && compareTime(users.get(i).get("START_TIME"), e) < 0)
                        || (compareTime(users.get(i).get("END_TIME"), s) > 0
                        && compareTime(users.get(i).get("END_TIME"), e) <= 0)) {
                    LogErrorManager.getInstance().addError(0, "Journal.reserveRes(int " + accId + ", int " + resId + ", String " + startTime + ", String " + endTime + ",String " + loop + ")",
                            "WARNING time is busy, record did not add");
                    System.out.println("sorry, you haven't enough rights to do this. Enother weakly task is here");
                    return 0;
                }
            }
            tmp = getInfoOfTimeByRes(resId, s, e);
            try {
                //System.out.println(tmp);
                if (tmp == null) {
                    continue;
                }
                for (HashMap t : tmp) {
                    if (t.get("LOOP") != null) {
                        //haven't enough rights
                        LogErrorManager.getInstance().addError(0, "Journal.reserveRes(int " + accId + ", int " + resId + ", String " + startTime + ", String " + endTime + ",String " + loop + ")",
                                "WARNING time is busy, record did not add, enother weakly task is here");
                        return 0;
                    } else {
                        if (compareDate(t.get("START_TIME").toString(), startTime) >= 0
                                && compareDate(t.get("START_TIME").toString(), endTime) <= 0) {
                            System.out.println(t.get("NAME").toString() + " you are deleted!");
                            mailList.add(t);
                        }
                    }
                }
            } catch (NullPointerException ex) {
                LogErrorManager.getInstance().addError(2, "Journal.reserveRes(int " + accId + ", int " + resId + ", String " + startTime + ", String " + endTime + ",String " + loop + ")",
                        "NullPointerException (" + ex + ")");
                System.out.println("ErrOr");
                return -1;
            }
        }
        for (HashMap user : mailList) {
            remove(Integer.parseInt(user.get("ID").toString()),
                    Integer.parseInt(user.get("RESOURCE_ID").toString()), user.get("NAME").toString(),
                    "from " + user.get("START_TIME") + " to " + user.get("END_TIME"),
                    user.get("TITLE").toString(), user.get("MAIL").toString());

        }
        add(accId, resId, startTime, endTime, loop);
        System.out.println("LOOP record ADDED check function");

        return 1;
    }

    private String getDayOf(String start, String end) {
        Date sd = new Date();
        Date ed = new Date();
        SimpleDateFormat sdin = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        if (start.length() < 18) {
            SimpleDateFormat sds = new SimpleDateFormat("yy/MM/dd HH:mm");
            try {
                sd = sds.parse(start);
                ed = sds.parse(end);
            } catch (ParseException ex) {
                LogErrorManager.getInstance().addError(3, "Journal.getDayOf(String " + start + ", String " + end + ")",
                        "FATAL: date parse exception 1 (" + ex + ")");
            }
            start = sdin.format(sd);
            end = sdin.format(ed);
        }

        SimpleDateFormat sdout = new SimpleDateFormat("E");
        String day = null;
        int in = Integer.parseInt(start.substring(8, 10));
        String ind = "";

        String Start = start.substring(0, 8) + ind + start.substring(10);

        //System.out.println(Start.substring(0, 10) + " " + end.substring(0, 10));
        try {
            Date d2 = null;
            for (int i = 0; i < 7; i++) {
                if (in + i < 10) {
                    ind = "0" + (in + i);
                } else {
                    ind = "" + (in + i);
                }
                Start = start.substring(0, 8) + ind + start.substring(10);
                d2 = sdin.parse(Start);
                day += sdout.format(d2) + "|";
                if (Start.substring(0, 10).compareTo(end.substring(0, 10)) == 0) {
                    break;
                }
            }
        } catch (ParseException ex) {
            LogErrorManager.getInstance().addError(3, "Journal.getDayOf(String " + start + ", String " + end + ")",
                    "FATAL: date parse exception 2 (" + ex + ")");
        }
        if (in <= 9) {
            return day.substring(4, 7);
        }
        return day.substring(4, day.length() - 1);
    }

    private int compareDate(String d1, String d2) {
        {
            SimpleDateFormat sdin = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat edin = new SimpleDateFormat("yyyy/MM/dd");
            Date D1 = null;
            Date D2 = null;
            try {
                D1 = sdin.parse(d1);
                D2 = edin.parse(d2);
            } catch (ParseException ex) {
                LogErrorManager.getInstance().addError(3, "compareDate(String " + d1 + ", String " + d2 + ")",
                        "FATAL: date parse exception (" + ex + ")");
            }
//            System.out.println(sdin.format(D1)+" "+sdin.format(D2)+" "+D1.compareTo(D2));
            return D1.compareTo(D2);
        }

    }

    private int compareTime(String d1, String d2) {
        SimpleDateFormat sdin = new SimpleDateFormat("HH:mm");
        SimpleDateFormat edin = new SimpleDateFormat("HH:mm");
        try {
            Date D1 = sdin.parse(d1.substring(10));
            Date D2 = edin.parse(d2.substring(10));
            System.out.println("D1=" + edin.format(D1) + " D2=" + edin.format(D2));
            return D1.compareTo(D2);
        } catch (ParseException ex) {
            LogErrorManager.getInstance().addError(3, "Journal.compareTime(String " + d1 + ", String " + d2 + ")",
                    "FATAL: date parse exception (" + ex + ")");

        }

        return -2;
    }

    private ArrayList<HashMap> getInfoE(String outParam, String startTime, String endTime, String Ex) {
        String q;
        String day = getDayOf(startTime, endTime);
//        day="Mon";
//        System.out.println(day + " day");
//        System.out.println(compareTime("12/13/2003 19:00", "2003/12/13 18:00:00")+"<");

        q = "select "
                + outParam + ",to_char(begin_time,'HH24:MI') as stime from "
                + "journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "(  (    begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS') and  "
                + "end_time>to_timestamp(   '" + startTime + "', 'YY/MM/DD HH24:MI:SS')  ) or  "
                + "(    begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS') and "
                + "end_time>to_timestamp(  '" + startTime + "', 'YY/MM/DD HH24:MI:SS')  ))and"
                + "(  regexp_like(NVL(loop,'Mon Tue Wed Thu Fri Sat Sun'),'(" + day + ")') and"
                + "( (    begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS') and"
                + "    end_time>to_timestamp(   '" + startTime + "', 'YY/MM/DD HH24:MI:SS')  ) or  "
                + "(    begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS') and   "
                + " end_time>to_timestamp(  '" + startTime + "', 'YY/MM/DD HH24:MI:SS')  ) )) "
                + Ex
                + " order by r.title, stime";

        if (!db.query(q)) {
            LogErrorManager.getInstance().addError(2, "Journal.getInfoE(String " + outParam + ", String " + startTime + ", String " + endTime + ", String " + Ex + ")",
                    "select data from table journal failed (" + q + ")");

            return null;
        }

        ArrayList<HashMap> temp = (ArrayList<HashMap>) db.getResultList().clone();
        if (temp.isEmpty()) {
            return null;
        }
        SimpleDateFormat out = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        SimpleDateFormat in = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        SimpleDateFormat out2 = new SimpleDateFormat("20yy/MM/dd HH:mm");
        Date ds = null;
        Date de = null;
        try {
            ds = in.parse(startTime);
            de = in.parse(endTime);
        } catch (ParseException ex) {
            LogErrorManager.getInstance().addError(3, "Journal.getInfoE(String " + outParam + ", String " + startTime + ", String " + endTime + ", String " + Ex + ")",
                    "FATAL: date parse failed (" + ex.toString() + ")");
        }
        String startT = out.format(ds);
        startTime = out2.format(ds);
        endTime = out2.format(de);
        String s;
        int i = 0;
        while (i < temp.size()) {
            if (temp.get(i).get("LOOP") != null) {
                if (((compareTime(temp.get(i).get("START_TIME").toString(), startTime) < 0
                        && compareTime(temp.get(i).get("END_TIME").toString(), startTime) <= 0))
                        || (compareTime(temp.get(i).get("START_TIME").toString(), endTime) >= 0)) {
//                    System.out.println("-------" + temp.get(i));
                    temp.remove(i);
                    if (i > 0) {
                        i--;
                    }
                } else {
                    i++;
                }
            } else {
                i++;
            }
//            System.out.println("------->" + temp.get(i));
            if (temp.isEmpty()) {
                break;
            }
            if (i == 0) {
                temp.get(i).put("START_TIME", startT.substring(0, 10) + temp.get(i).get("START_TIME").toString().substring(10));
                s = startT.substring(0, 10) + temp.get(i).get("END_TIME").toString().substring(10);
                temp.get(i).put("END_TIME", s);
            } else {
                temp.get(i - 1).put("START_TIME", startT.substring(0, 10) + temp.get(i - 1).get("START_TIME").toString().substring(10));
                s = startT.substring(0, 10) + temp.get(i - 1).get("END_TIME").toString().substring(10);
                temp.get(i - 1).put("END_TIME", s);
            }
        }
        i = 0;
        return temp;
    }

    private ArrayList<HashMap> getBusyDays(String days, int res) {
        String q = "Sun Mon Tue Wed Thu Fri Sat";
        String[] tmp = q.split(" ");
        String t = "";
        HashMap<String, String> d = new HashMap();
        int a = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (days.indexOf(tmp[i]) != -1) {
                d.put(tmp[i], "" + (i + 1));
                t += "," + (i + 1);
            }
        }
        t = "(" + t.substring(1) + ")";
        days = days.replace(' ', '|');
//        System.out.println(days+"="+t);
        q = "select to_char(begin_time,'YYYY/MM/DD HH24:MI:SS') as start_time,"
                + "to_char(end_time,'YYYY/MM/DD HH24:MI:SS') as end_time,account_id,resource_id,loop from journal "
                + "where (to_char(begin_time,'D') in " + t + " or regexp_like(loop,'" + days + "')) and resource_id=" + res
                + " order by begin_time desc";

        if (!db.query(q)) {
            LogErrorManager.getInstance().addError(2, "Journal.getBusyDays(String " + days + ", int " + res + ")",
                    "query failed (" + q + ")");

            return null;
        }
        return db.getResultList();
    }
}
