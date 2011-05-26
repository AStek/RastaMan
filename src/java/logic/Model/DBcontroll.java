/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.Model;

import logic.DB;

/**
 *
 * @author Александр
 */
public class DBcontroll {
   private static DB instance = null;

    public static DB getInstance()
    {
        if (instance == null)
        {
            instance = new DB();
            instance.openConnection();
        }
        return instance;
    }


}
