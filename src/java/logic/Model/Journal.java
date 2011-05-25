/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.Model;

import java.util.ArrayList;
import java.util.HashMap;
import logic.DB;

/**
 *
 * @author adm
 */
public class Journal {
   private DB db = null;

    public Journal()
    {
        db = DBcontroll.getInstance();
    }

    public  boolean getInfoOfTime(String startTime, String endTime)
    {
        // simple insertion
        String q;
        q = "select begin_time, end_time,a.name as name,r.title as res from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or begin_time<=to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS'))";
        return db.query(q);
    }

    public  boolean getInfoOfTime(int accId, int resId, String startTime, String endTime)
    {
        // simple insertion
        String q;
        q = "select * from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + "and (a.id=" + accId + " and r.id=" + resId + ")";
        return db.query(q);
    }

    public  boolean getInfoOfTime(int accId, String startTime, String endTime)
    {
        // simple insertion
        String q;
        q = "select * from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + "and (a.id=" + accId + " )";
        return db.query(q);
    }

    public  boolean getInfoOfTimeByRes(int resId, String startTime, String endTime)
    {
        // simple insertion
        String q;
        q = "select * from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + "and (r.id=" + resId + " )";
        return db.query(q);
    }

    public  boolean remove(int accId, int resID, boolean notify)
    {
        if (notify)
        {
            // !get info about time for 'accId' and 'resId'
            //send mail to user with 'accId'
            System.out.println("user " + accId + " has notified and removed from journal");
        }
        return db.query("delete from journal where ACCOUNT_ID=" + accId + " and RESOURCE_ID=" + resID);
    }

    private boolean add(int accId, int resId, String startTime, String endTime)
    {
        String q = "insert into journal values("
                + "null,"
                + "to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'),"
                + "to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS')"
                + "," + accId + "," + resId + ")";

        return db.query(q);
    }

    public  boolean reserveRes(int accId, int resId, String startTime, String endTime)
    {
        boolean f = true;
        boolean isLowPriority = true;
        f = f && getInfoOfTimeByRes(resId, startTime, endTime);
        ArrayList<HashMap> users = (ArrayList<HashMap>) db.getResultList().clone();
        Account acc=new Account();
        for (HashMap u : users)
        {
            System.out.println(acc.getPriority(accId) + "<=" + acc.getPriority(u.get("LOGIN").toString()));
            if (acc.getPriority(accId) <= acc.getPriority(u.get("LOGIN").toString()))
            {
                isLowPriority = false;
            }
        }
        if (!isLowPriority)
        {
            //WARNING not so high priority, you hasn't reserved
            System.out.println("sorry, you havn't enough rights to do this");
            return false;
        } else
        {
            for (HashMap u : users)
            {
//                removeFromJournal(Integer.parseInt(u.get("ACCOUNT_ID").toString()),
//                        Integer.parseInt(u.get("RESOURCE_ID").toString()),true);
                System.out.println("user " + u.get("ACCOUNT_ID") + " has notified and removed from journal");

            }
            //addRecord2Journal(accId, resId, startTime, endTime);
            System.out.println("record ADDED");
            return true;
        }
    }

}
