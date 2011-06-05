/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.Model;

import logic.IModel.IJournal;
import java.util.ArrayList;
import java.util.HashMap;
import logic.DB;
import logic.DBcontroll;

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
        // simple insertion
        String q;
        q = "select to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "a.name as name,r.title as res, j.id as id "
                + "from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or (begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS') and"
                + "     end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')))"
                + "order by r.title, begin_time";
        if (!db.query(q)) {
            return null;
        }
        return db.getResultList();
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
        String q;
        q = "select j.id,"
                + "to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "account_id,resource_id,a.*,r.*"
                + " from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or (begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS') and"
                + "     end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')))"
                + "and (a.id=" + accId + " and r.id=" + resId + ")"
                + "order by r.title, begin_time";
        if (!db.query(q)) {
            return null;
        }
        return db.getResultList();
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
        String q;
        q = "select j.id,"
                + "to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "account_id,resource_id,a.*,r.*"
                + " from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or (begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS') and"
                + "     end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')))"
                + "and (a.id=" + accId + " )"
                + "order by r.title, begin_time";
        if (!db.query(q)) {
            return null;
        }
        return db.getResultList();
    }

    /**
     * Возвращает информацию по указанному ресурсу и диапазону дат
     * @param resId
     * @param startTime
     * @param endTime
     * @return ArrayList<HashMap>
     * Добавил Александр
     */
    public ArrayList<HashMap> getInfoOfTimeByRes(int resId, String startTime, String endTime) {
        // simple insertion
        String q;
        q = "select j.id,"
                + "to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "account_id,resource_id,a.*,r.*"
                + "from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or (begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS') and"
                + "     end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')))"
                + "and (r.id=" + resId + " )"
                + "order by r.title, begin_time";
        if (!db.query(q)) {
            return null;
        }
        return db.getResultList();

    }

    /**
     * Удаляет строку с оповещением пользователя об удалении из расписания
     * @param accId
     * @param resID
     * @param uName
     * @param time
     * @param res
     * @param mail
     * @return boolean
     * Добавил Александр
     */
    public boolean remove(int accId, int resID, String uName, String time, String res, String mail) {

        if (db.query("delete from journal where ACCOUNT_ID=" + accId + " and RESOURCE_ID=" + resID)) {
            // !get info about time for 'accId' and 'resId'
            //send mail to user with 'accId'
            System.out.println("user " + uName + "( " + time + " from " + res + " )" + " has notified and removed from journal");
            return true;
        }
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

    private boolean add(int accId, int resId, String startTime, String endTime,String loop) {
        String q = "insert into journal values("
                + "null,"
                + "to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'),"
                + "to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS')"
                + "," + accId + "," + resId + ",'"+loop+"')";

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
            boolean f = true;
            boolean isLowPriority = true;
            ArrayList<HashMap> users = (ArrayList<HashMap>) getInfoOfTimeByRes(resId, startTime, endTime).clone();
            Account acc = new Account();
            for (HashMap u : users) {
                System.out.println(acc.getPriority(accId) + "<=" + acc.getPriority(u.get("LOGIN").toString()));
                if (acc.getPriority(accId) <= acc.getPriority(u.get("LOGIN").toString())) {
                    isLowPriority = false;
                }
            }
            if (!isLowPriority) {
                //WARNING not so high priority, you hasn't reserved
                System.out.println("sorry, you hasn't enough rights to do this");
                return 0;
            } else {
                for (HashMap u : users) {
                    remove(Integer.parseInt(u.get("ACCOUNT_ID").toString()),
                            Integer.parseInt(u.get("RESOURCE_ID").toString()), u.get("NAME").toString(),
                            "from " + u.get("BEGIN_TIME") + " to " + u.get("END_TIME"),
                            u.get("TITLE").toString(), u.get("MAIL").toString());
                }
                //add(accId, resId, startTime, endTime);
                //System.out.println("record ADDED");
                //return 1;
                if (add(accId, resId, startTime, endTime)){
                    return 1;
                } else {
                    return -1;
                }
            }
        } catch (NullPointerException ex) {
            // null error is here
            return -1;
        } catch (Exception e) {
            //error is here
            return -1;
        }
    }

    public int reserveRes(int accId, int resId, String startTime, String endTime, String loop) {
        add(accId, resId, startTime, endTime, loop);
        return 1;
    }

    /**
     * Удаляет запись из журнала по ИД
     * @param Id
     * @return
     * Добавил Александр
     */

    public boolean remove(int Id) {
        return db.query("delete from journal where id="+Id);
    }

    /**
     * По ИД журнала возвращает диапазон дат, название ресурса имя пользователя и его ИД
     * @param id
     * @return HashMap {
     * NAME=Виктор Побережный,
     * ID=6, START_TIME=12/13/2003 17:00,
     * END_TIME=12/13/2003 19:00,
     * TITLE=Аудитория №1}
     */
    public HashMap getInfo(int id) {
        String q = "select to_char(begin_time,'MM/DD/YYYY HH24:MI') as start_time,"
                + "to_char(end_time,'MM/DD/YYYY HH24:MI') as end_time,"
                + "title,name,a.id as ac_id,j.loop "
                + "from account a,journal j,resources r "
                + "where (a.id=j.account_id and r.id=j.resource_id) and j.id=" + id;
        db.query(q);
        try {
            return db.getResultList().get(0);
        } catch (NullPointerException ex) {
        } catch (Exception ex) {
        }
        return null;
    }

}
