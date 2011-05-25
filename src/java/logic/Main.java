/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.SQLException;
import java.util.HashMap;
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
        System.out.println(role.add("testRole2"));

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
        System.out.println(role.setById(85, "Перзедент"));

        System.out.println("---- add res 2 role -----");
        System.out.println(role.setResRole(1, 85));

        System.out.println("---- remove remove res from role -----");
        System.out.println(res.rmvResRole(1, 85));

        System.out.println("---- get resources from role -----");
        System.out.println(role.getResOfRole(3));

        System.out.println("---- get roles of resource  -----");
        System.out.println(res.getRoleOfRes(1));

        System.out.println("---- journal  -----");
//        RMCore.getInstance().getInfoOfTime(1,1,"2003/12/13 15:00:00","2003/12/13 18:10:00");
        jrn.reserveRes(1, 4, "2003/12/13 16:30:00", "2003/12/13 17:00:00");
        for (HashMap t : jrn.getInfoOfTime(1, "2003/12/13 16:30:00", "2003/12/13 17:00:00"))
        {
            System.out.println(t);
        }

    }
}
