/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.SQLException;
import java.util.HashMap;
import logic.ErrMgr.ErrorItem;
import logic.ErrMgr.LogErrorManager;
import logic.Model.*;

/**
 *
 * @author Администратор
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException
    {
        /* e.g. Authentication. Function checkAccount(login, pass") recieves log and pass
         *  and return user name if successfully complete else return NULL.
         *  If using getPriority() without params, it try to return user priority
         *  that have recieved in last calling of checkAccount function
         *  return -1 if faild
         */
        String s;
        Account acc = new Account();
        Resource res = new Resource();
        Role role = new Role();
        Journal jrn = new Journal();
        if ((s = acc.autorize("bereg", "yDybebf73")) != null)
        {
            System.out.println(s);
            System.out.println(acc.getPriority());

        } else
        {
            System.out.println("invalid login/password");
        }

        //e.g. of printing Res table content
        System.out.println("---- Resources -----");
        for (HashMap t : res.getAll())
        {
            System.out.println(t.get("TITLE"));
        }

        System.out.println("---- Roles -----");
        for (HashMap t : role.getAll())
        {
            System.out.println(t.get("TITLE"));
        }

        System.out.println("---- Role by id -----");
        System.out.println(role.getById(5));

        System.out.println("---- add Role with title -----");
//        System.out.println(role.add("testRole2"));

        System.out.println("---- add Role with title -----");
        //boolean f = RMCore.getInstance().addRoleRes("testrole6", 2);

        System.out.println("---- remove role by id -----");
        boolean f = role.removeById(43);
        System.out.println(f);

        System.out.println("---- res by id -----");
        System.out.println(res.getById(5));

        System.out.println("---- is unique -----");
        System.out.println(acc.isUnique("moroz2"));

        System.out.println("---- accounts -----");
        for (HashMap t : acc.getAll())
        {
            System.out.println(t);
        }
        System.out.println("---- set role by id -----");
//        System.out.println(role.setById(85, "Перзедент"));

//        System.out.println("---- add res 2 role -----");
//        System.out.println(role.setResRole(1, 21));

//        System.out.println("---- remove remove res from role -----");
//        System.out.println(res.rmvResRole(1, 85));

        System.out.println("---- get resources from role -----");
        System.out.println(role.getResOfRole(3));

        System.out.println("---- get roles of resource  -----");
        System.out.println(res.getRoleOfRes(1));

        System.out.println("---- get acc by login  -----");
//        System.out.println(acc.getInfo("mororz"));


//        System.out.println("---- journal  reserve -----");
//        jrn.reserveRes(1, 4, "2003/12/13 16:00:00", "2003/12/13 17:00:00");
//        jrn.reserveRes(2, 4, "2003/12/13 17:00:00", "2003/12/13 18:00:00");
//        jrn.reserveRes(3, 4, "2003/12/13 18:00:00", "2003/12/13 19:00:00");
//        jrn.reserveRes(1, 4, "2003/12/13 19:00:00", "2003/12/13 20:00:00");
//
//        jrn.reserveRes(1, 1, "2003/12/13 17:00:00", "2003/12/13 19:00:00");
//        jrn.reserveRes(3, 1, "2003/12/13 19:00:00", "2003/12/13 20:00:00");
//
//        jrn.reserveRes(4, 2, "2003/12/13 16:00:00", "2003/12/13 20:00:00");
//
//        jrn.reserveRes(5, 3, "2003/12/13 16:00:00", "2003/12/13 17:00:00");
//        jrn.reserveRes(6, 3, "2003/12/13 17:00:00", "2003/12/13 19:00:00");
//
//        jrn.reserveRes(1, 4, "2003/03/02 16:00:00", "2003/03/02 17:00:00");
//        jrn.reserveRes(2, 4, "2003/03/03 17:00:00", "2003/03/03 18:00:00");
//        jrn.reserveRes(3, 4, "2003/03/04 18:00:00", "2003/03/04 19:00:00");
//        jrn.reserveRes(1, 4, "2003/03/05 19:00:00", "2003/03/05 20:00:00");
//
//
//        jrn.reserveRes(1, 5, "2011/05/30 19:00:00", "2017/03/05 20:00:00","Mon Wed");
//        jrn.reserveRes(1, 5, "2003/12/13 16:00:00", "2005/12/13 17:00:00","Mon Wed");
//        jrn.reserveRes(1, 4, "2000/12/13 15:00:00", "2010/12/13 16:00:00","Mon Wed");
//        jrn.reserveRes(3, 4, "2000/12/13 14:00:00", "2010/12/13 14:30:00","Mon Wed");
//        jrn.reserveRes(1, 4, "2003/12/13 12:00:00", "2003/12/13 13:00:00");

        System.out.println("---- journal  info of acc -----");
        int i=0;
        for (HashMap t : jrn.getInfoOfTime("2000/12/13 12:00:00", "2003/12/15 20:00:00"))
        {
            i++;
//            System.out.println(t.get("START_TIME")+"     "+
//                    t.get("END_TIME")+"  =  "+
//                    t.get("NAME")+"   "+
//                    t.get("TITLE")+" "+
//                    t.get("LOOP")+" "+
//                    t.get("RES"));
        }
        System.out.println("--------------"+i+"--------------");

        LogErrorManager.getInstance().addError(0,"rrr","eeee");
for (i=0; i<LogErrorManager.getInstance().getErrorList().size();i++)
{
        System.out.print(LogErrorManager.getInstance().getErrorList().get(i).getCode()+" ");
        System.out.print(LogErrorManager.getInstance().getErrorList().get(i).getSource()+" ");
        System.out.println(LogErrorManager.getInstance().getErrorList().get(i).getMessage());
        }
//
//        System.out.println("------------- reserve loop --------------");
//        jrn.reserveRes(1, 4, "2003/12/13 17:00:00", "2009/12/13 19:00:00","Wed Mon Sat");
//        jrn.reserveRes(1, 4, "2003/12/13 17:00:00", "2003/12/13 19:00:00");
//
//        for (int i=0;i<7;i++)
//            System.out.print(jrn.getDayOf("2003/12/"+(15+i)+" 16:00:00")+" ");
//        System.out.println(jrn.getInterval("2003/3/2 16:00:00", "2003/3/2 17:00:00"));
//        jrn.reserveRes(1, 4, "2011/05/20 12:00:00", "2011/05/20 13:00:00");
//        jrn.reserveRes(1, 5, "2011/05/30 10:00:00", "2017/03/05 13:00:00","Mon Wed");

//        System.out.println(jrn.getInfo(230));
    }
}
