/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.Model;

import logic.IModel.IJournal;
import java.util.ArrayList;
import java.util.HashMap;
import logic.DB;

/**
 *
 * @author adm
 */
public class Journal implements IJournal
{

    private DB db = null;

    public Journal()
    {
        db = DBcontroll.getInstance();
    }

    public ArrayList<HashMap> getAll()
    {
        if (!db.query("select * from journal"))
        {
            return null;
        }

        return db.getResultList();
    }

    public ArrayList<HashMap> getInfoOfTime(String startTime, String endTime)
    {
        // simple insertion
        String q;
        q = "select begin_time, end_time,a.name as name,r.title as res from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or begin_time<=to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS'))";
        if (!db.query(q))
        {
            return null;
        }
        return db.getResultList();
    }

    public ArrayList<HashMap> getInfoOfTime(int accId, int resId, String startTime, String endTime)
    {
        // simple insertion
        String q;
        q = "select * from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + "and (a.id=" + accId + " and r.id=" + resId + ")";
        if (!db.query(q))
        {
            return null;
        }
        return db.getResultList();
    }

    public ArrayList<HashMap> getInfoOfTime(int accId, String startTime, String endTime)
    {
        // simple insertion
        String q;
        q = "select * from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + "and (a.id=" + accId + " )";
        if (!db.query(q))
        {
            return null;
        }
        return db.getResultList();
    }

    public ArrayList<HashMap> getInfoOfTimeByRes(int resId, String startTime, String endTime)
    {
        // simple insertion
        String q;
        q = "select * from journal j,account a,resources r where "
                + "(a.id=account_id and r.id=RESOURCE_ID) and "
                + "((begin_time<=to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS')"
                + " and end_time>to_timestamp('" + startTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + " or begin_time<to_timestamp('" + endTime + "', 'YY/MM/DD HH24:MI:SS'))"
                + "and (r.id=" + resId + " )";
        if (!db.query(q))
        {
            return null;
        }
        return db.getResultList();

    }

    public boolean remove(int accId, int resID, String uName, String time, String res)
    {

        if (db.query("delete from journal where ACCOUNT_ID=" + accId + " and RESOURCE_ID=" + resID))
        {
            // !get info about time for 'accId' and 'resId'
            //send mail to user with 'accId'
            System.out.println("user " + uName + "( " + time + " from " + res + " )" + " has notified and removed from journal");
            return true;
        }
        return false;
    }

    public boolean remove(int accId, int resID)
    {
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

    public int reserveRes(int accId, int resId, String startTime, String endTime)
    {
        try
        {
        boolean f = true;
        boolean isLowPriority = true;
        ArrayList<HashMap> users = (ArrayList<HashMap>) getInfoOfTimeByRes(resId, startTime, endTime).clone();
        Account acc = new Account();
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
            return 0;
        } else
        {
            for (HashMap u : users)
            {
                remove(Integer.parseInt(u.get("ACCOUNT_ID").toString()),
                        Integer.parseInt(u.get("RESOURCE_ID").toString()), u.get("NAME").toString(),
                        "from " + u.get("BEGIN_TIME") + " to " + u.get("END_TIME"), u.get("TITLE").toString());
            }
            add(accId, resId, startTime, endTime);
            System.out.println("record ADDED");
            return 1;
        }
        }catch(NullPointerException ex)
        {
            // null error is here
            return -1;
        }
        catch (Exception e)
        {
            //error is here
            return -1;
        }
    }
}
