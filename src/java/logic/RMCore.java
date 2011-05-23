/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Администратор
 */
public class RMCore
{

    private static RMCore instance = null;

    private RMCore() //private constructor for singleton pattern
    {
    }
    //call private constructor if instance don't exists and return it

    public static RMCore getInstance()
    {
        if (instance == null)
        {
            instance = new RMCore();
            try
            {
                DB.getInstance().openConnection();
            }
            catch (SQLException ex)
            {
                return null;
            }
        }
        return instance;
    }

    //check for existing account with that log and pass
    public String checkAccount(String login, String paswd)
    {
        String q = null;
        q = "select * from Account where login='" + login + "' and password='" + paswd + "'";
        try
        {
            DB.getInstance().query(q);
            if (DB.getInstance().getRowcount() == 0)
            {
                return null;//if don't found any then returns null
            }
        } catch (SQLException e)
        {
            return null;
        }
        return DB.getInstance().getResultList().get(0).get("NAME").toString(); //if all OK returns name of user
    }

//returns priority of user of last calling checkAccount function
    public int getPriority()
    {
        try
        {
            return Integer.valueOf(DB.getInstance().getResultList().get(0).get("PRIORITY").toString());
        } catch (Exception e)
        {
            return -1;
        }
    }

//returns priority of user by his login
    public int getPriority(String login)
    {
        try
        {
            DB.getInstance().query("select priority from Account where login='" + login + "'");
            if (DB.getInstance().getRowcount() == 0)
            {
                return -1;
            }
        } catch (SQLException e)
        {
            return -1;
        }
        return Integer.valueOf(DB.getInstance().getResultList().get(0).get("PRIORITY").toString());
    }

//returns all resources in the system
    public ArrayList<HashMap> getResources()
    {
        try
        {
            if (!DB.getInstance().query("Select title from resources"))
            {
                return null;
            }
        } catch (SQLException ex)
        {
            return null;
        }
        return DB.getInstance().getResultList();
    }

    //returns all available roles in the system
    public ArrayList<HashMap> getRoles()
    {
        try
        {
            if (!DB.getInstance().query("Select title from role"))
            {
                return null;
            }
        } catch (SQLException ex)
        {
            return null;
        }
        return DB.getInstance().getResultList();
    }

    public void reReserve(String accID, String start, String end)
    {
//        SimpleDateFormat sd=new SimpleDateFormat("MM-dd:HH:mm");
//        System.out.println(sd.format(d));
        // DB.query("select login, start,end from Account a,Journal j where a.acc_id=j.acc_id and start<=todate("+start+") "+
        //         "or start<todate("+end+")");
    }

    /**
     * Создание учетной записи
     * @param login String Строка с логином, не более 20 символ
     * @param password String Строка с паролем, не более 20 символ
     * @param name String Строка с именем, не более 50 символ
     * @param roleId Integer Идентификатор роли
     * @param priority Integer Приоритет
     * @param email String E-Mail адрес, не более 32 символ
     * @return Boolean
     * @throws SQLException
     *
     * Добавлен Андреем 21.05.2011 19:52
     */
    public boolean addAccount(String login, String password, String name,
            int roleId, int priority, String email) throws SQLException
    {
        return DB.getInstance().query("INSERT INTO account (NAME, LOGIN, PASSWORD, ROLE_ID, PRIORITY, EMAIL) "+
                " VALUES ('"+name+"', '"+login+"', '"+password+"', "+Integer.toString(roleId)+
                ", "+Integer.toString(priority)+", '"+email+"');");
    }


    /**
     * Редактирование учетной записи
     * @param id Integer Идентификатор записи
     * @param login String Строка с логином, не более 20 символ
     * @param password String Строка с паролем, не более 20 символ
     * @param name String Строка с именем, не более 50 символ
     * @param roleId Integer Идентификатор роли
     * @param priority Integer Приоритет
     * @param email String E-Mail адрес, не более 32 символ
     * @return Boolean
     * @throws SQLException
     *
     *  Добавлен Андреем 21.05.2011 19:52
     */
    public boolean updateAccountById(int id, String login, String password, String name,
            int roleId, int priority, String email) throws SQLException
    {
        return DB.getInstance().query("UPDATE account SET NAME='"+name+"', "
                + "LOGIN='"+login+"', PASSWORD='"+password+"', ROLE_ID="+Integer.toString(roleId)+
                ", PRIORITY="+Integer.toString(priority)+", "
                + "EMAIL='"+email+"'  WHERE ID="+Integer.toString(id)+";");
    }


    /**
     * Удаление учетной записи
     * @param id Integer  Идентификатор записи 
     * @return Boolean
     * @throws SQLException
     *
     * Добавлен Андреем 21.05.2011 19:52
     */
    public boolean deleteAccountById(int id) throws SQLException
    {
        return DB.getInstance().query("DELETE FROM account WHERE ID="+Integer.toString(id)+";");
    }

    /**
     * Возвращает список ролей
     * @return ArrayList
     *
     * Добавлен Андреем 22.05.2011 12:05
     */
    public ArrayList<HashMap> getRoleList()
    {
        try
        {
            if (!DB.getInstance().query("SELECT * FROM role;"))
            {
                return null;
            }
        } catch (SQLException ex)
        {
            return null;
        }
        return DB.getInstance().getResultList();
    }

    /**
     * Возвращает учетную запись пользователя по имени и роли
     * @param name String Строка с именем, не более 50 символ
     * @param role String Строка с названием роли, не более 20 символ
     * @return ArrayList
     *
     * Добавлен Андреем 22.05.2011 12:05
     */
    public ArrayList<HashMap> getAccountByNameAndRole(String name, String role)
    {
                try
        {
            if (!DB.getInstance()
                    .query("SELECT COUNT(a.id) FROM account a, role r WHERE "
                    + "a.name='"+name+"' AND a.role_id=r.id AND r.title='"+role+"';"))
            {
                return null;
            }
        } catch (SQLException ex)
        {
            return null;
        }
        return DB.getInstance().getResultList();
    }
}
