/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.SQLException;
import java.util.HashMap;

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
        CfgMgr.loadConfig();
        String s;
        if ((s = RMCore.getInstance().checkAccount("bereg", "yDybebf73")) != null)
        {
            System.out.println(s);
            System.out.println(RMCore.getInstance().getPriority());

        } else
        {
            System.out.println("invalid login/password");
        }

        //e.g. of printing Res table content
        System.out.println("---- Resources -----");
        for (HashMap t:RMCore.getInstance().getResources())
        {
            System.out.println(t.get("TITLE"));
        }

        System.out.println("---- Roles -----");
        for (HashMap t:RMCore.getInstance().getRoles())
        {
            System.out.println(t.get("TITLE"));
        }

    }
}
